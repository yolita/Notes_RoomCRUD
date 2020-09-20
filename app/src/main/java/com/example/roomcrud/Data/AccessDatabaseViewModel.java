package com.example.roomcrud.Data;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class AccessDatabaseViewModel extends AndroidViewModel {
    final static String TAG="AccessDatabaseViewModel";
    EventDao mDao;
//    final EventsRepository mEventsRepository;
    LiveData<List<EventModel>> mAllEvents ;
    LiveData<EventModel> mEvent ;
    int EventID =-1;


    public AccessDatabaseViewModel(@NonNull Application application) {
        super(application);
//        mEventsRepository = new EventsRepository(application);
        mDao= InjectorUtil.provideEventDao(application);
        mAllEvents =mDao.getAllEvents();
        Log.d("Tag", "ViewModelConstructor");
    }

//    public LiveData<List<EventModel>> getEventsList(){
//        return mEventsRepository.getEventsList();
//    }
   public LiveData<List<EventModel>> getAllEvents() {
        return mAllEvents;
    }


    public LiveData<EventModel> getOneEvent(int id){
        if(id != -1)
            return mDao.getEvent(id);
        else{
            Log.d( TAG, "ID not set");
        }
        return null;
    }

    public void insertEvent(EventModel event){
        IOExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                mDao.insertEvent(event);
            }
        });

    }

    public void updateEvent(EventModel event){
        IOExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                mDao.updateEvent(event);

            }
        });
    }

    public void deleteEvent(EventModel event){
        IOExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                mDao.deleteEvent(event);
            }
        });
    }
}
