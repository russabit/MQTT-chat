package com.rsabitov.testchatpos.Domain.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Topic {

    @PrimaryKey @NonNull
    public String name;

    public Topic(@NonNull String name) {
        this.name = name;
    }
}