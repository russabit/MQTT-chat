package com.rsabitov.mqtt_chat.Domain.repository;

import androidx.lifecycle.LiveData;

import com.rsabitov.mqtt_chat.Domain.model.Message;
import com.rsabitov.mqtt_chat.Domain.model.Topic;

import java.util.List;

public interface TopicRepository {
    LiveData<List<Topic>> getAllTopics();
    void insert(Topic topic);
    Topic getTopicByName(String name);
    LiveData<Message> getIncomingMessageFromThatTopic();
    void delete(Topic topic);
    void subscribeToTopic(String topic);
    /*void update(Topic topic);
    void deleteAll();*/
}
