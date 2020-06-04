package com.rsabitov.testchatpos.Domain.repository;

import androidx.lifecycle.LiveData;

import com.rsabitov.testchatpos.Domain.model.Message;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.List;

public interface MessageRepository {
    LiveData<List<Message>> getMessageByTopic(String topic);
    void insert(Message message);
    LiveData<Message> getIncomingMessage();
    void deleteTopicMessages(String topic);
    void publishMessage(String topic, String message);
}
