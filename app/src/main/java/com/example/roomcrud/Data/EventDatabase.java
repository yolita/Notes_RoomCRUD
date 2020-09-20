package com.example.roomcrud.Data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {EventModel.class}, version = 1)
public abstract class EventDatabase extends RoomDatabase {
    public abstract EventDao provideEventDao();

}

