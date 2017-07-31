package propertyguru.hackernews.feature.comments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import propertyguru.hackernews.R;
import propertyguru.hackernews.base.ui.BaseActivity;
import propertyguru.hackernews.model.Story;

import static propertyguru.hackernews.feature.comments.CommentsFragment.EXTRA_STORY;

/**
 * Created by hitesh on 6/23/17.
 */

public class CommentsActivity extends BaseActivity {

    private Story story;

    public static Intent getStartIntent(Context context, Story story) {
        Intent intent = new Intent(context, CommentsActivity.class);
        intent.putExtra(EXTRA_STORY, story);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (savedInstanceState == null) {
            story = getIntent().getParcelableExtra(EXTRA_STORY);
            Bundle bundle = new Bundle();
            bundle.putParcelable(EXTRA_STORY, story);
            CommentsFragment commentsFragment = new CommentsFragment();
            commentsFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.layout_comments, commentsFragment, "root");
            fragmentTransaction.commit();
        }

    }

}
