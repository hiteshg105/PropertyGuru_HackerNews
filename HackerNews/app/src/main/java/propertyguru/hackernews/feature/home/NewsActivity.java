package propertyguru.hackernews.feature.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import java.util.ArrayList;

import butterknife.BindView;
import propertyguru.hackernews.R;
import propertyguru.hackernews.adapter.NewsAdapter;
import propertyguru.hackernews.base.ui.BaseActivity;
import propertyguru.hackernews.model.Story;

/**
 * Created by hitesh on 6/21/17.
 */

public class NewsActivity extends BaseActivity {

    public static final String TOPSTORIES = "topstories";
    NewsAdapter                newsAdapter;
    ArrayList<Story>           storyList;

    @BindView(R.id.root_layout)
    FrameLayout                rootLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (savedInstanceState == null) {
            NewsFragment hello = new NewsFragment();
            fragmentTransaction.add(R.id.root_layout, hello, "root");
            fragmentTransaction.commit();
        }

    }


}
