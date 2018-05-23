package com.example.binson.goalrush.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ArticleDao {
    @Query("select * from article")
    LiveData<List<Article>> getAllArticles();

    @Insert
    void addArticle(Article article);
}
