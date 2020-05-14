package com.rsabitov.testchatpos.DB;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {

    @PrimaryKey
    @NonNull
    public String name;
}
