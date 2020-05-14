package com.rsabitov.testchatpos.repository;

import androidx.lifecycle.LiveData;

import com.rsabitov.testchatpos.DB.Message;

import java.util.List;

public interface MessageRepository {
    LiveData<List<Message>> getAllMessages();
    /*Message getMessageById(int id);*/
}
