package com.rsabitov.mqtt_chat.Data.roomDao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.rsabitov.mqtt_chat.Domain.model.Topic;

import java.util.List;

@Dao
public interface TopicDao {

    @Query("SELECT * FROM Topic")
    LiveData<List<Topic>> getAll();

    @Query("SELECT * FROM Topic WHERE name = :name")
    Topic getByName(String name);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Topic topic);

    @Update
    void update(Topic topic);

    @Delete
    void delete(Topic topic);

    @Query("DELETE FROM Topic")
    void deleteAll();
}
