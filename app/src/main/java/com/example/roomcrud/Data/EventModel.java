package com.example.roomcrud.Data;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class EventModel {
    private String title;
    private String content;
    private String timestamp;
    @PrimaryKey(autoGenerate = true)
    private int id;

    @Ignore
     EventModel(){
        title =null;
        content=null;
        timestamp=null;
    }

    public EventModel(String title, String content, String timestamp){
        this.title=title;
        this.content=content;
        this.timestamp=timestamp;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public void setContent(String content){
        this.content=content;
    }
    public void setTimestamp(String timestamp){
        this.content= timestamp;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle(){
        return this.title;
    }
    public String getContent(){
        return this.content;
    }
    public String getTimestamp(){
        return this.timestamp;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "EventModel{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(@Nullable Object that) {
        if (this == that) return true;

        if (that == null || getClass() != that.getClass()) return false;

        EventModel vThatEventModel = (EventModel) that;

        return this.getId() == vThatEventModel.getId() &&
                this.getTitle().equals(vThatEventModel.getTitle()) &&
                Objects.equals(this.getContent(), vThatEventModel.getContent()) &&
                this.getTimestamp().equals(vThatEventModel.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getContent(), getTimestamp(), getId());
    }
}
