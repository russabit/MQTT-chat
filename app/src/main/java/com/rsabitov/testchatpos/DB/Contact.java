package com.rsabitov.testchatpos.DB;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name;

    public Contact(@NonNull String name) {
        this.name = name;
    }
}
