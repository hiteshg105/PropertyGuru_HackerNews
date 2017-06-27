package propertyguru.hackernews.feature.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import propertyguru.hackernews.R;
import propertyguru.hackernews.adapter.NewsAdapter;
import propertyguru.hackernews.base.mvp.MVPActivity;
import propertyguru.hackernews.model.Story;
import propertyguru.hackernews.network.NetworkClient;
import propertyguru.hackernews.network.NetworkStores;

/**
 * Created by hitesh on 6/21/17.
 */

public class NewsActivity extends MVPActivity<NewsPresenter> implements NewsView, SwipeRefreshLayout.OnRefreshListener {

    public static final String TOPSTORIES = "topstories";
    NewsAdapter                newsAdapter;
    List<Story>                storyList;

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout         swipeRefreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView               recyclerView;

    @BindView(R.id.progress)
    ProgressBar                progressBar;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeRefreshLayout.setOnRefreshListener(this);
        storyList = new ArrayList<>();
        newsAdapter = new NewsAdapter(storyList, R.layout.news_row, this);
        recyclerView.setAdapter(newsAdapter);
        progressBar.setVisibility(View.VISIBLE);
        presenter.loadNews(TOPSTORIES);
    }

    @Override
    protected NewsPresenter createPresenter() {
        NetworkStores stores = NetworkClient.getRetrofit().create(NetworkStores.class);
        return new NewsPresenter(this, stores);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void getDataFail(String message) {
        hideLoadingViews();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void moveToDetail(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void getListNews(List<Long> listIds) {

    }

    @Override
    public void onDataCompleted() {

    }

    public void onDataSuccess(Story story) {
        hideLoadingViews();
        storyList.add(story);
        newsAdapter.notifyItemInserted(storyList.indexOf(story));
    }

    private void hideLoadingViews() {
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        storyList.clear();
        newsAdapter.notifyDataSetChanged();
        presenter.refresh();
    }
}
