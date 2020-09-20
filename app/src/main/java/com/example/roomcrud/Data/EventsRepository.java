package com.example.roomcrud.Data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventsRepository {
    private static final String TAG = "EVENTS REPOSITORY";
    EventDao mDao;
    private LiveData<List<EventModel>> mAllEvents;
    private LiveData<EventModel> mSingleEvent ;
//    private IOExecutor threadExecutor= IOExecutor.getInstance();

    EventsRepository(Application application){
        mDao =InjectorUtil.provideEventDao(application.getApplicationContext());
        mAllEvents = mDao.getAllEvents();
    }



    LiveData<List<EventModel>> getAllEvents() {
        IOExecutor.getInstance().execute((new Runnable() {
                    @Override
                    public void run() {
                        mDao.getAllEvents();
                    }
                })
        );
        return mAllEvents; }

    public LiveData<EventModel> getOneEvent(int id){
        mSingleEvent=mDao.getEvent(id);
        return mSingleEvent;
    }

//    public void getEvent(int id){
//        IOExecutor.getInstance().execute(new Runnable() {
//            @Override
//            public void run() {
//                mSingleEvent = mDao.getEvent(id);
//            }
//        }
//        );
//        return mSingleEvent;
//    }

    public void updateEvent(EventModel event){
        IOExecutor.getInstance().execute((new Runnable() {
            @Override
            public void run() {
                mDao.updateEvent(event);
            }
        })
        );
    }

    public void insertNewEvent(EventModel newEvent){
        IOExecutor.getInstance().execute(
                new Runnable() {
                    @Override
                    public void run() {
                        mDao.insertEvent(newEvent);
                    }
                }
        );

    }

    public void deleteEvent(EventModel event){
        IOExecutor.getInstance().execute(
                new Runnable() {
                    @Override
                    public void run() {
                        mDao.deleteEvent(event);
                    }
                }
        );
    }

//    public void run(){
//        runOnUiThread(() -> {
//
//            if (mEventModels.size() > 0) {
//                                 mFab.setVisibility(View.GONE);
//
//                mEventsRecyclerView = (RecyclerView) findViewById(R.id.events_list_recycler_view);
//                mEventsRecyclerView.setVisibility(View.VISIBLE);
//
//                mEventRecyclerAdapter = new EventListRecyclerAdapter(this, mEventModels);
//                mEventsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//                mEventsRecyclerView.setAdapter(mEventRecyclerAdapter);
//            }
//            else{
//                mNoEventsTextView.setVisibility(View.VISIBLE);
//            }
//        });
//    }

}
