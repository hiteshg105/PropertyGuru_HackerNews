package propertyguru.hackernews.feature.comments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import propertyguru.hackernews.R;
import propertyguru.hackernews.adapter.CommentsAdapter;
import propertyguru.hackernews.base.mvp.MvpFragment;
import propertyguru.hackernews.model.Comment;
import propertyguru.hackernews.model.Story;
import propertyguru.hackernews.network.NetworkClient;
import propertyguru.hackernews.network.NetworkStores;

/**
 * Created by hitesh on 6/23/17.
 */

public class CommentsFragment extends MvpFragment<CommentsPresenter> implements CommentsView {



    @BindView(R.id.progress_indicator)
    LinearLayout               mProgressBar;

    @BindView(R.id.recycler_comments)
    RecyclerView               mCommentsRecycler;


    private ArrayList<Comment> comments;
    private CommentsAdapter    commentsAdapter;
    private Story              story;

    public static final String EXTRA_STORY  = "EXTRA_STORY";

    boolean                    isReattached = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        story = new Story();
        comments = new ArrayList<>();
        commentsAdapter = new CommentsAdapter(comments);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_comments, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        story = getArguments().getParcelable(EXTRA_STORY);
        mCommentsRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mCommentsRecycler.setAdapter(commentsAdapter);
        if (!isReattached) {
            isReattached = true;
            presenter.loadComments(story.getKids());
        }
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
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
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
    public CommentsPresenter createPresenter() {
        NetworkStores stores = NetworkClient.getRetrofit().create(NetworkStores.class);
        return new CommentsPresenter(this, stores);
    }

    private void addCommentViews(Comment comment) {
        comments.add(comment);
        commentsAdapter.notifyDataSetChanged();
    }
}
