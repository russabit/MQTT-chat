package com.rsabitov.mqtt_chat.Domain.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {

    public Message(String message, String topic) {
        this.message = message;
        this.topic = topic;
    }

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String message;

    public String topic;
}
