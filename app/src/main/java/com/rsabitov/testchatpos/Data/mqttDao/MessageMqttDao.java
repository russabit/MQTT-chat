package com.rsabitov.testchatpos.Data.mqttDao;

import androidx.lifecycle.LiveData;
import com.rsabitov.testchatpos.Domain.model.Message;

public interface MessageMqttDao {
    LiveData<Message> getIncomingMessage();
}
