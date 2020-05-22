package com.rsabitov.testchatpos.Data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.rsabitov.testchatpos.Domain.Contact;
import com.rsabitov.testchatpos.Domain.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Contact.class, Message.class}, version = 1, exportSchema = false)
public abstract class ChatDatabase extends RoomDatabase {
    public abstract ContactDao getContactDao();

    public abstract MessageDao getMessageDao();

    private static final String DATABASE_NAME = "chats_db";

    private static volatile ChatDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ChatDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ChatDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ChatDatabase.class,
                            DATABASE_NAME
                    ).addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                ContactDao contactDao = INSTANCE.getContactDao();
                contactDao.insert(new Contact("Ruslan S"));
                contactDao.insert(new Contact("Sergei B"));
                contactDao.insert(new Contact("Aleksandr K"));
                contactDao.insert(new Contact("Mikhail Ch"));


                MessageDao messageDao = INSTANCE.getMessageDao();
                messageDao.insert(new Message("Hi", 1));
                messageDao.insert(new Message("Hello", 2));
                messageDao.insert(new Message("Hey", 3));
                messageDao.insert(new Message("How are you?", 4));
            });
        }
    };
}