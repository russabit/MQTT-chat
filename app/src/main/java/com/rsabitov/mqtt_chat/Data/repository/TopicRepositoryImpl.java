package com.rsabitov.mqtt_chat.Data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.rsabitov.mqtt_chat.Data.ChatDatabase;
import com.rsabitov.mqtt_chat.Data.MqttClient;
import com.rsabitov.mqtt_chat.Data.mqttDao.MessageMqttDao;
import com.rsabitov.mqtt_chat.Data.roomDao.TopicDao;
import com.rsabitov.mqtt_chat.Domain.model.Message;
import com.rsabitov.mqtt_chat.Domain.model.Topic;
import com.rsabitov.mqtt_chat.Domain.repository.TopicRepository;

import java.util.List;

public class TopicRepositoryImpl implements TopicRepository {
    private TopicDao mTopicDao;
    private LiveData<List<Topic>> mAllTopics;
    private MessageMqttDao mMessageMqttDao;

    public TopicRepositoryImpl(Application application) {
        ChatDatabase db = ChatDatabase.getDatabase(application);
        mTopicDao = db.getTopicDao();
        mAllTopics = mTopicDao.getAll();

        MqttClient mqttClient = MqttClient.getInstance(application);
        mMessageMqttDao = mqttClient.getMessageMqttDao();
    }

    @Override
    public LiveData<List<Topic>> getAllTopics() {
        return mAllTopics;
    }

    @Override
    public void insert(Topic topic) {
        ChatDatabase.databaseWriteExecutor.execute(() -> mTopicDao.insert(topic));
    }

    @Override
    public Topic getTopicByName(String name) {
        return mTopicDao.getByName(name);
    }

    @Override
    public LiveData<Message> getIncomingMessageFromThatTopic() {
        return mMessageMqttDao.getIncomingMessage();
    }

    @Override
    public void delete(Topic topic) {
        ChatDatabase.databaseWriteExecutor.execute(() -> mTopicDao.delete(topic));
    }

    @Override
    public void subscribeToTopic(String topic) {
        mMessageMqttDao.subscribeToTopic(topic);
    }
}
