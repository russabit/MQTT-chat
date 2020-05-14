package com.rsabitov.testchatpos.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class, Message.class}, version = 1)
public abstract class ChatDatabase extends RoomDatabase {
    public abstract ContactDao getContactDao();

    public abstract MessageDao getMessageDao();

    private static final String DATABASE_NAME = "chats_db";

    private static ChatDatabase instance;

    static ChatDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ChatDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return instance;
    }
}
