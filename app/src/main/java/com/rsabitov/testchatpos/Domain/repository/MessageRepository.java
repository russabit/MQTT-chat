package com.rsabitov.testchatpos.Domain.repository;

import androidx.lifecycle.LiveData;

import com.rsabitov.testchatpos.Domain.model.Message;

import java.util.List;

public interface MessageRepository {
    LiveData<List<Message>> getMessageById(int id);
    void insert(Message message);
}
