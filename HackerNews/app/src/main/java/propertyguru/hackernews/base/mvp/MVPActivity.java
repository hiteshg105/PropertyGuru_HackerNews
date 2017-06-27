package propertyguru.hackernews.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import propertyguru.hackernews.base.ui.BaseActivity;
import propertyguru.hackernews.base.ui.BasePresenter;

/**
 * Created by hitesh on 6/21/17.
 */

public abstract class MVPActivity<P extends BasePresenter> extends BaseActivity {
    protected P presenter;

    protected abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }
}
