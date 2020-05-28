package com.rsabitov.testchatpos.Data;

import androidx.lifecycle.LiveData;

public interface ContactMqttDao {
    LiveData<String> getIncomingContact();
}
