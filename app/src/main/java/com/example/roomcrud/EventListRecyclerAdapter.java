package com.example.roomcrud;

import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomcrud.Data.EventModel;
import com.google.android.material.snackbar.Snackbar;
import com.example.roomcrud.Keys;

import java.util.List;

public class EventListRecyclerAdapter extends RecyclerView.Adapter<EventListRecyclerAdapter.EventViewHolder> {
    private List<EventModel> mEventModels;
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private View mItemView;
    Keys key;



    public EventListRecyclerAdapter(Context context){
       mContext=context;
       mLayoutInflater= LayoutInflater.from(mContext);

    }

    public void setEventModels(List<EventModel> eventModel){
            this.mEventModels = eventModel;
            notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mItemView = mLayoutInflater.inflate(R.layout.events_list, parent, false);
        return new EventViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, final int position) {
        Log.i("DEBUG MENU", "onBindViewHolder");

        EventModel eventModel = mEventModels.get(position);
        String title = eventModel.getTitle();
        String content = eventModel.getContent();
        holder.eventTitleTextView.setText(title);
        holder.eventContentOverviewTextView.setText(content);
        int eventId = eventModel.getId();
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
//                Intent intent =new Intent(mContext, EventsDetailsActivity.class);
//                intent.putExtra(key.EVENT_ID.toString(), eventId) ;
//                mContext.startActivity(intent);
                navigateToDetailsActivity(eventId);
            }
        });
    }

    private void navigateToDetailsActivity(int eventId) {
        Intent viewEventDetailsIntent = new Intent(mContext, EventsDetailsActivity.class);
        viewEventDetailsIntent.putExtra(Keys.EVENT_ID.toString(), eventId);
        mContext.startActivity(viewEventDetailsIntent);
    }

    @Override
    public int getItemCount() {
        if(mEventModels != null)
          return mEventModels.size();
        else return 0;
    }


    public class EventViewHolder extends RecyclerView.ViewHolder{
        private TextView eventTitleTextView, eventContentOverviewTextView;
//        ConstraintLayout eventsLayout;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventTitleTextView = itemView.findViewById(R.id.event_title_text_view);
            eventContentOverviewTextView = itemView.findViewById(R.id.event_content_overview_text_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
    }
}
