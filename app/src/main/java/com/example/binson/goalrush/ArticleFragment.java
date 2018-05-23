package com.example.binson.goalrush;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.binson.goalrush.db.Article;
import com.example.binson.goalrush.utils.AppExecutors;
import com.example.binson.goalrush.vm.ArticleViewModel;

public class ArticleFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private ArticleViewModel articleViewModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ArticleFragment() {
    }

    public static ArticleFragment newInstance(int columnCount) {
        ArticleFragment fragment = new ArticleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_list, container, false);

        /* Set the adapter */
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            final ArticleListAdapter adapter = new ArticleListAdapter(context);
            recyclerView.setAdapter(adapter);
            setupViewModel(adapter);
        }
        return view;
    }

    private void setupViewModel(ArticleListAdapter adapter) {
        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);

        AppExecutors.getInstance().getDiskIO().execute(() -> {
            articleViewModel.addArticle(new Article("Hello", "This is hello from DC Universe"));
            articleViewModel.addArticle(new Article("Bye", "This is bye-bye from Marvel Universe"));
        });
        articleViewModel.getListOfArticles().observe(this, articleList -> adapter.setArticles(articleList));
    }


//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
////        if (context instanceof OnListFragmentInteractionListener) {
////            mListener = (OnListFragmentInteractionListener) context;
////        } else {
////            throw new RuntimeException(context.toString()
////                    + " must implement OnListFragmentInteractionListener");
////        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnListFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onListFragmentInteraction(DummyItem item);
//    }
}
