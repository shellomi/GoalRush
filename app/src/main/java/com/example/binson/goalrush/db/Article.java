package com.example.binson.goalrush.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "article")
public class Article {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String title;
    @NonNull
    private String content;

    @Ignore
    public Article(@NonNull String title, @NonNull String content) {
        this.title = title;
        this.content = content;
    }

    Article(int id, @NonNull String title, @NonNull String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getContent() {
        return content;
    }
}
