package com.rsabitov.testchatpos.Domain.repository;

import androidx.lifecycle.LiveData;

import com.rsabitov.testchatpos.Domain.model.Message;
import com.rsabitov.testchatpos.Domain.model.Topic;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.List;

public interface TopicRepository {
    LiveData<List<Topic>> getAllTopics();
    void insert(Topic topic);
    Topic getTopicByName(String name);
    LiveData<Message> getIncomingMessageFromThatTopic();
    void delete(Topic topic);
    /*void update(Topic topic);
    void deleteAll();*/
}
