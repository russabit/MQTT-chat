package com.rsabitov.testchatpos.Data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.rsabitov.testchatpos.Domain.model.Message;

import java.util.List;

@Dao
public interface MessageDao {

    @Query("SELECT * FROM message")
    LiveData<List<Message>> getAll();

    @Query("SELECT * FROM message WHERE contactId = :id")
    LiveData<List<Message>> getById(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Message message);
}
