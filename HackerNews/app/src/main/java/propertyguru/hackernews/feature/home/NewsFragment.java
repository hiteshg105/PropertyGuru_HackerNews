package propertyguru.hackernews.feature.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import propertyguru.hackernews.R;
import propertyguru.hackernews.adapter.NewsAdapter;
import propertyguru.hackernews.base.mvp.MvpFragment;
import propertyguru.hackernews.model.Story;
import propertyguru.hackernews.network.NetworkClient;
import propertyguru.hackernews.network.NetworkStores;

/**
 * Created by hitesh on 7/24/17.
 */

public class NewsFragment extends MvpFragment<NewsPresenter> implements NewsView, SwipeRefreshLayout.OnRefreshListener {
    public static final String TOPSTORIES = "topstories";
    NewsAdapter                newsAdapter;
    ArrayList<Story>           storyList;

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout         swipeRefreshLayout;

    @BindView(R.id.recyclerView)
    RecyclerView               recyclerView;

    @BindView(R.id.progress)
    ProgressBar                progressBar;

    boolean isReattached=false;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        storyList = new ArrayList<>();
        newsAdapter = new NewsAdapter(storyList, R.layout.news_row, this);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setAdapter(newsAdapter);
        if(!isReattached){
            isReattached=true;
            presenter.loadNews(TOPSTORIES);
        }
    }

    @Override
    public NewsPresenter createPresenter() {
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
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
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
