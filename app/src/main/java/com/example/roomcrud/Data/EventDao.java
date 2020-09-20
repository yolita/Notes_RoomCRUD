package com.example.roomcrud.Data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EventDao {

    @Insert
    void insertEvent(EventModel eventModel);

    @Update
    void updateEvent(EventModel eventModel);

    @Delete
    void deleteEvent(EventModel eventModel);

    @Query("SELECT * FROM EventModel")
    LiveData<List<EventModel>> getAllEvents();

    @Query("SELECT * FROM EventModel WHERE id=:id")
    LiveData<EventModel> getEvent(int id);
}
