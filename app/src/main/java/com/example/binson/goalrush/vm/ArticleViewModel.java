package com.example.binson.goalrush.vm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.binson.goalrush.ArticleRepository;
import com.example.binson.goalrush.db.Article;

import java.util.List;

public class ArticleViewModel extends AndroidViewModel {
    private ArticleRepository articleRepository;
    private LiveData<List<Article>> listOfArticles;

    public ArticleViewModel(@NonNull Application application) {
        super(application);
        articleRepository = new ArticleRepository(application);
        listOfArticles = articleRepository.getListOfArticles();
    }

    public LiveData<List<Article>> getListOfArticles() {
        return listOfArticles;
    }

    public void addArticle(Article article) {
        articleRepository.addArticle(article);
    }
}
