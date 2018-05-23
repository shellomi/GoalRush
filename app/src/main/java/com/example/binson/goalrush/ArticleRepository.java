package com.example.binson.goalrush;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.example.binson.goalrush.db.AppDatabase;
import com.example.binson.goalrush.db.Article;
import com.example.binson.goalrush.db.ArticleDao;

import java.util.List;

public class ArticleRepository {
    private ArticleDao dao;
    private LiveData<List<Article>> listOfArticles;

    public ArticleRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        dao = db.articleDao();
        listOfArticles = dao.getAllArticles();
    }

    public LiveData<List<Article>> getListOfArticles() {
        return listOfArticles;
    }

    public void addArticle(Article article) {
        dao.addArticle(article);
    }
}
