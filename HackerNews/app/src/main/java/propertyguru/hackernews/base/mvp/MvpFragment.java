package propertyguru.hackernews.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import propertyguru.hackernews.base.ui.BaseFragment;
import propertyguru.hackernews.base.ui.BasePresenter;

/**
 * Created by hitesh on 6/21/17.
 */

public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P presenter;

    public abstract P createPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }
}
