package com.rsabitov.testchatpos.DB;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MessageDao {

    @Query("SELECT * FROM message")
    List<Message> getAll();

    @Query("SELECT * FROM message WHERE id = :id")
    Message getById(int id);
}
