package com.rsabitov.testchatpos.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.rsabitov.testchatpos.DB.ChatDatabase;
import com.rsabitov.testchatpos.DB.Contact;
import com.rsabitov.testchatpos.DB.ContactDao;
import com.rsabitov.testchatpos.DB.Message;
import com.rsabitov.testchatpos.DB.MessageDao;

import java.util.List;

public class MessageRepositoryImpl implements MessageRepository {
    private MessageDao mMessageDao;
    private LiveData<List<Message>> mAllMessages;

    public MessageRepositoryImpl(Application application, int contactId) {
        ChatDatabase db = ChatDatabase.getDatabase(application);
        mMessageDao = db.getMessageDao();
        mAllMessages = mMessageDao.getById(contactId);
    }

    @Override
    public LiveData<List<Message>> getMessageById(int id) {
        return mAllMessages;
    }
}
