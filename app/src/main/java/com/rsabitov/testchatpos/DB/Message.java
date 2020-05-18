package com.rsabitov.testchatpos.DB;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {

    public Message(String message, int contactId) {
        this.message = message;
        this.contactId = contactId;
    }

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String message;

    //@ColumnInfo(name = "contactId")
    public int contactId;
}
