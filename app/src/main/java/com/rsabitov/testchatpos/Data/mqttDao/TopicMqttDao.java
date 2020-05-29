package com.rsabitov.testchatpos.Data.mqttDao;

import androidx.lifecycle.LiveData;

import com.rsabitov.testchatpos.Domain.model.Topic;

public interface TopicMqttDao {
    LiveData<Topic> getIncomingTopic();
}
