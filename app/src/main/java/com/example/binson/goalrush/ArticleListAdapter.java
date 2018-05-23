package com.example.binson.goalrush;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.binson.goalrush.db.Article;

import java.util.List;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder> {
    private final Context context;
    private List<Article> articleList;

    ArticleListAdapter(Context context) {
        this.context = context;
    }

    public void setArticles(List<Article> articleList) {
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_article, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.titleView.setText(article.getTitle());
        holder.contentView.setText(article.getContent());
    }

    @Override
    public int getItemCount() {
        if (articleList == null) {
            return 0;
        }
        return articleList.size();
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleView;
        private final TextView contentView;

        ArticleViewHolder(View view) {
            super(view);

            titleView = view.findViewById(R.id.tv_title);
            contentView = view.findViewById(R.id.tv_content);
        }
    }


}
