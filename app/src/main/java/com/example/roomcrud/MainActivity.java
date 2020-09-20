package com.example.roomcrud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.roomcrud.Data.AccessDatabaseViewModel;
import com.example.roomcrud.Data.EventModel;
import com.example.roomcrud.Data.EventDao;
import com.example.roomcrud.Data.IOExecutor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public final class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EventDao mDao;
    private FloatingActionButton mFab;
    private EventListRecyclerAdapter mEventRecyclerAdapter;
    private TextView mNoEventsTextView;
    private MutableLiveData<List<EventModel>> mEventModels= new MutableLiveData<>();
    private RecyclerView mEventsRecyclerView;
    Activity context;
    private AccessDatabaseViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_main);
        mNoEventsTextView = findViewById(R.id.text_no_events);

        mViewModel = new ViewModelProvider(this).get(AccessDatabaseViewModel.class);
        mEventsRecyclerView = (RecyclerView) findViewById(R.id.events_list_recycler_view);
        mEventsRecyclerView.setVisibility(View.VISIBLE);
        mEventRecyclerAdapter = new EventListRecyclerAdapter(context);
        mEventsRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mEventsRecyclerView.setAdapter(mEventRecyclerAdapter);


        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(),   AddEventActivity.class);
                startActivity(myIntent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

       mViewModel.getAllEvents().observe(this, new Observer<List<EventModel>>()
       {
           @Override
           public void onChanged(List<EventModel> eventModels) {
               if (eventModels.size() > 0) {
                   Log.d(TAG,"observing data changed");
                   mEventRecyclerAdapter.setEventModels(eventModels);
                   //mEventRecyclerAdapter.notifyDataSetChanged();
               }
               else{
                   mNoEventsTextView.setVisibility(View.VISIBLE);
               }
           }
       });

        Log.i("DEBUG MAIN", "onResume");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
