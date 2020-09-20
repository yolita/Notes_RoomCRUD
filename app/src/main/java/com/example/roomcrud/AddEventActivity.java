package com.example.roomcrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.roomcrud.Data.AccessDatabaseViewModel;
import com.example.roomcrud.Data.EventModel;
import com.example.roomcrud.Data.EventDao;
import com.example.roomcrud.Data.IOExecutor;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;

public class AddEventActivity extends AppCompatActivity {


    private EditText mEventTitle;
    private EditText mEventContent;
    private AccessDatabaseViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        mEventTitle = findViewById(R.id.add_event_title_editText);
        mEventContent = findViewById(R.id.add_event_content_editText);
        mViewModel = new ViewModelProvider(this).get(AccessDatabaseViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.event_menu, menu);
        if(menu != null){
            menu.setGroupVisible(R.id.eventDetailsMenuItems,false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.save_menu_item){
            saveEvent();
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public boolean saveEvent(){
        String title = mEventTitle.getText().toString();
        String content = mEventContent.getText().toString();

        if (title.isEmpty() || content.isEmpty()) {
            Snackbar.make(findViewById(R.id.add_event_layout),getString(R.string.validation_error), Snackbar.LENGTH_SHORT).show();
        }
        else
            {
            //TODO 11. Save EventModel
            final EventModel event = new EventModel(title, content, new Date().toString());
            mViewModel.insertEvent(event);
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        return true;
    }
}