package com.example.roomcrud.Data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


public class DbAsyncTasks extends AsyncTask<Integer, Void, LiveData<EventModel>> {
    private static final String TAG = "DbAsyncTasks";
    private  LiveData<EventModel> mOneEvent;
    EventDao mDao;

    public DbAsyncTasks(Context context) {
        mDao =InjectorUtil.provideEventDao(context.getApplicationContext());
    }

    @Override
    protected LiveData<EventModel> doInBackground(Integer... id) {
        mOneEvent = mDao.getEvent(id[0]);
        return mOneEvent;
    }


}
