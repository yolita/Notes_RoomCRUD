package com.example.roomcrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomcrud.Data.AccessDatabaseViewModel;
import com.example.roomcrud.Data.EventModel;
import com.example.roomcrud.Data.EventDao;
import com.example.roomcrud.Data.IOExecutor;
import com.google.android.material.snackbar.Snackbar;

public class EventsDetailsActivity extends AppCompatActivity {

    private EventDao mDao;
    private TextView mEventName;
    private TextView mEventContent;
    private Button mSaveEdit;
    private EventModel mEventModel;
    private boolean mDeleteFlag=false;
    private AccessDatabaseViewModel mViewModel;
    private ConstraintLayout viewLayout;

    private  Menu MainMenu;
    private int mEventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        mViewModel = new ViewModelProvider(this).get(AccessDatabaseViewModel.class);
        viewLayout =findViewById(R.id.constraints_event_lists);

        Intent intent = getIntent();
        mEventId = intent.getIntExtra(Keys.EVENT_ID.toString(),-1);

        mEventName = findViewById(R.id.text_event_name);
        mEventContent = findViewById(R.id.text_event_content);


        getEvent(mEventId);
    }

    public void getEvent(int id){
        //mViewModel.setID(id);
        mViewModel.getOneEvent(id).observe(this, new Observer<EventModel>() {
            @Override
            public void onChanged(EventModel eventModel) {
                mEventName.setText(eventModel.getTitle());
                mEventContent.setText(eventModel.getContent());
                mEventModel=eventModel;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.event_menu, menu);
        MainMenu =menu;
        MenuItem item = MainMenu.findItem(R.id.save_menu_item);
        if(item != null){
            item.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.edit_menu_item) {
            editEvents();
            return true;
        }
        if(id== R.id.delete_menu_item){
            deleteEvent();
            return true;
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    private void deleteEvent() {
        mViewModel.deleteEvent(mEventModel);
        runOnUiThread(()-> {
                Toast.makeText(this, "Event Deleted", Toast.LENGTH_SHORT).show();
        });
    }


    private void editEvents() {
        Intent intent = new Intent(this,EditEventsActivity.class);
        intent.putExtra(Keys.EVENT_ID.toString(),mEventId);
        startActivity(intent);
    }

}