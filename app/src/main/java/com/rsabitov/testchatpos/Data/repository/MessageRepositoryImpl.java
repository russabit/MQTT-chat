package com.rsabitov.testchatpos.Data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.rsabitov.testchatpos.Data.ChatDatabase;
import com.rsabitov.testchatpos.Data.RoomDao.MessageDao;
import com.rsabitov.testchatpos.Domain.model.Message;
import com.rsabitov.testchatpos.Domain.repository.MessageRepository;

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

    @Override
    public void insert(Message message) {
        ChatDatabase.databaseWriteExecutor.execute(() -> mMessageDao.insert(message));
    }
}
