package com.example.roomcrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.roomcrud.Data.AccessDatabaseViewModel;
import com.example.roomcrud.Data.EventModel;

public class EditEventsActivity extends AppCompatActivity {
    private static final String TAG = "EditEventsActivity";
    private AccessDatabaseViewModel mViewModel;
    private EventModel mEvent;
    private TextView mEventName;
    private TextView mEventContent;
    private int mEventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_events);
        mEventName = findViewById(R.id.txt_name);
        mEventContent = findViewById(R.id.txt_content);


        mViewModel = new ViewModelProvider(this).get(AccessDatabaseViewModel.class);

        Intent intent = getIntent();
        mEventId = intent.getIntExtra(Keys.EVENT_ID.toString(),-1);

        if(mEventId >=0){
            mViewModel.getOneEvent(mEventId).observe(this, new Observer<EventModel>() {
                @Override
                public void onChanged(EventModel event) {
                    mEvent=event;
                    mEventName.setText(event.getTitle());
                    mEventContent.setText(event.getContent());
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.event_menu, menu);
        if(menu != null)
            menu.setGroupVisible(R.id.eventDetailsMenuItems, false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId  = item.getItemId();
        if(itemId==R.id.save_menu_item){
            saveEvent();
            navigateToMainActivity();
            finish();
        }
        return true;
    }

    private void saveEvent() {
        if(mEventName != null ){
            mEvent.setTitle(mEventName.getText().toString());
            mEvent.setContent(mEventContent.getText().toString());
            mViewModel.updateEvent(mEvent);
        }
    }

    private void navigateToMainActivity() {
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        //viewEventDetailsIntent.putExtra(Keys.EVENT_ID.toString(), eventId);
        this.startActivity(mainActivityIntent);
    }
}