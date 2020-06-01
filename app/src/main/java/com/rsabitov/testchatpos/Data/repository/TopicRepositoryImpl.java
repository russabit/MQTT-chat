package com.rsabitov.testchatpos.Data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.rsabitov.testchatpos.Data.ChatDatabase;
import com.rsabitov.testchatpos.Data.MqttClient;
import com.rsabitov.testchatpos.Data.mqttDao.MessageMqttDao;
import com.rsabitov.testchatpos.Data.roomDao.TopicDao;
import com.rsabitov.testchatpos.Domain.model.Message;
import com.rsabitov.testchatpos.Domain.model.Topic;
import com.rsabitov.testchatpos.Domain.repository.TopicRepository;

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
}
