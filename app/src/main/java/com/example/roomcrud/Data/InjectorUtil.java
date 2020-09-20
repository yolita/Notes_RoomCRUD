package com.example.roomcrud.Data;

import android.content.Context;

import androidx.room.Room;

import com.example.roomcrud.Data.EventDao;
import com.example.roomcrud.Data.EventDatabase;

final class InjectorUtil {

    private InjectorUtil() {
    }

    /**
     * Builds an instance of the database for the application.
     */
    private static EventDatabase provideEventDatabase(Context context) {
        String DIARY_DATABASE_NAME = "event_db";
        return Room
                .databaseBuilder(
                        context,
                        EventDatabase.class,
                        DIARY_DATABASE_NAME
                )
                .build();
    }

    /**
     *Convenience method for getting an instance of the dao from the activity.
     */
    public static EventDao provideEventDao(Context context) {
        //TODO 7.Create Database Instance
        EventDatabase db = provideEventDatabase(context);
        return db.provideEventDao();
    }
}
