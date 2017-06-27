package propertyguru.hackernews.feature.comments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import propertyguru.hackernews.R;
import propertyguru.hackernews.adapter.CommentsAdapter;
import propertyguru.hackernews.base.mvp.MVPActivity;
import propertyguru.hackernews.model.Comment;
import propertyguru.hackernews.model.Story;
import propertyguru.hackernews.network.NetworkClient;
import propertyguru.hackernews.network.NetworkStores;

/**
 * Created by hitesh on 6/23/17.
 */

public class CommentsActivity extends MVPActivity<CommentsPresenter> implements CommentsView {

    @BindView(R.id.layout_comments)
    RelativeLayout             mCommentsLayout;

    @BindView(R.id.progress_indicator)
    LinearLayout               mProgressBar;

    @BindView(R.id.recycler_comments)
    RecyclerView               mCommentsRecycler;

    @BindView(R.id.text_no_comments)
    TextView                   mNoCommentsText;

    private ArrayList<Comment> comments;
    private CommentsAdapter    commentsAdapter;
    private Story              story;

    public static final String EXTRA_STORY = "EXTRA_STORY";

    public static Intent getStartIntent(Context context, Story story) {
        Intent intent = new Intent(context, CommentsActivity.class);
        intent.putExtra(EXTRA_STORY, story);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        ButterKnife.bind(this);
        story = getIntent().getParcelableExtra(EXTRA_STORY);
        comments = new ArrayList<>();
        mCommentsRecycler.setLayoutManager(new LinearLayoutManager(this));
        commentsAdapter = new CommentsAdapter(comments);
        mCommentsRecycler.setAdapter(commentsAdapter);
        presenter.loadComments(story.getKids());
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);

    }

    @Override
    public void getDataFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void moveToDetail(Intent intent) {

    }

    @Override
    public void getListNews(List<Long> listIds) {

    }

    @Override
    public void onDataCompleted() {

    }

    @Override
    public void onDataSuccess(Comment comment) {
        addCommentViews(comment);
    }

    @Override
    protected CommentsPresenter createPresenter() {
        NetworkStores stores = NetworkClient.getRetrofit().create(NetworkStores.class);
        return new CommentsPresenter(this, stores);
    }

    private void addCommentViews(Comment comment) {
        comments.add(comment);
        commentsAdapter.notifyDataSetChanged();
    }
}
