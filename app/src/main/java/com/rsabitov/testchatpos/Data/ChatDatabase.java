package com.rsabitov.testchatpos.Data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.rsabitov.testchatpos.Data.roomDao.TopicDao;
import com.rsabitov.testchatpos.Data.roomDao.MessageDao;
import com.rsabitov.testchatpos.Domain.model.Topic;
import com.rsabitov.testchatpos.Domain.model.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Topic.class, Message.class}, version = 1, exportSchema = false)
public abstract class ChatDatabase extends RoomDatabase {
    public abstract TopicDao getTopicDao();

    public abstract MessageDao getMessageDao();

    private static final String DATABASE_NAME = "chats_db";

    private static volatile ChatDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ChatDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ChatDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ChatDatabase.class,
                            DATABASE_NAME
                    )//.addCallback(sRoomDatabaseCallback)
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
                TopicDao topicDao = INSTANCE.getTopicDao();
                topicDao.insert(new Topic("roosy/new"));
                topicDao.insert(new Topic("roosy/anotherlayer"));
                topicDao.insert(new Topic("roosy/1"));
                topicDao.insert(new Topic("roosy/okay"));


                MessageDao messageDao = INSTANCE.getMessageDao();
                messageDao.insert(new Message("Hi", "roosy/new"));
                messageDao.insert(new Message("Hello", "roosy/anotherlayer"));
                messageDao.insert(new Message("Hey", "roosy/1"));
                messageDao.insert(new Message("How are you?", "roosy/okay"));
            });
        }
    };
}