package com.rsabitov.testchatpos.Domain;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface MessageRepository {
    LiveData<List<Message>> getMessageById(int id);
    void insert(Message message);
}
