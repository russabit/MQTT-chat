package com.rsabitov.testchatpos.Data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.rsabitov.testchatpos.Data.ChatDatabase;
import com.rsabitov.testchatpos.Data.MqttClient;
import com.rsabitov.testchatpos.Data.mqttDao.MessageMqttDao;
import com.rsabitov.testchatpos.Data.roomDao.MessageDao;
import com.rsabitov.testchatpos.Domain.model.Message;
import com.rsabitov.testchatpos.Domain.repository.MessageRepository;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.List;

public class MessageRepositoryImpl implements MessageRepository {
    private MessageDao mMessageDao;
    private LiveData<List<Message>> mAllMessages;
    private MessageMqttDao mMessageMqttDao;

    public MessageRepositoryImpl(Application application, String topic) {
        ChatDatabase db = ChatDatabase.getDatabase(application);
        mMessageDao = db.getMessageDao();
        mAllMessages = mMessageDao.getById(topic);

        MqttClient mqttClient = MqttClient.getInstance(application);
        mMessageMqttDao = mqttClient.getMessageMqttDao();
    }

    @Override
    public LiveData<List<Message>> getMessageByTopic(String topic) {
        return mAllMessages;
    }

    @Override
    public void insert(Message message) {
        ChatDatabase.databaseWriteExecutor.execute(() -> mMessageDao.insert(message));
    }

    @Override
    public LiveData<Message> getIncomingMessage() {
        return mMessageMqttDao.getIncomingMessage();
    }

}
