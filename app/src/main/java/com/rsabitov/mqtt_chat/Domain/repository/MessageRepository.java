package com.rsabitov.mqtt_chat.Domain.repository;

import androidx.lifecycle.LiveData;

import com.rsabitov.mqtt_chat.Domain.model.Message;

import java.util.List;

public interface MessageRepository {
    LiveData<List<Message>> getMessageByTopic(String topic);
    void insert(Message message);
    LiveData<Message> getIncomingMessage();
    void deleteTopicMessages(String topic);
    void publishMessage(String topic, String message);
}
