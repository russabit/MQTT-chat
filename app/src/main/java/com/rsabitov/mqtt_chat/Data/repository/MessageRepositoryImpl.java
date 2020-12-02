package com.rsabitov.mqtt_chat.Data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.rsabitov.mqtt_chat.Data.ChatDatabase;
import com.rsabitov.mqtt_chat.Data.MqttClient;
import com.rsabitov.mqtt_chat.Data.mqttDao.MessageMqttDao;
import com.rsabitov.mqtt_chat.Data.roomDao.MessageDao;
import com.rsabitov.mqtt_chat.Domain.model.Message;
import com.rsabitov.mqtt_chat.Domain.repository.MessageRepository;

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

    @Override
    public void deleteTopicMessages(String topic) {
        ChatDatabase.databaseWriteExecutor.execute(() -> mMessageDao.delete(topic));
    }

    @Override
    public void publishMessage(String topic, String message) {
        mMessageMqttDao.publishToTopic(topic, message);
    }

}
