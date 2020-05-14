package com.rsabitov.testchatpos.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String message;
}
