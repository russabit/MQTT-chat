package com.rsabitov.mqtt_chat.Data.mqttDao;

import androidx.lifecycle.LiveData;
import com.rsabitov.mqtt_chat.Domain.model.Message;

public interface MessageMqttDao {
    LiveData<Message> getIncomingMessage();
    void subscribeToTopic(String topic);
    void publishToTopic(String topic, String message);
}
