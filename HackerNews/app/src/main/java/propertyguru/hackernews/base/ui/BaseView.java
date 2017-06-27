package propertyguru.hackernews.base.ui;

import android.content.Intent;

import java.util.List;

/**
 * Created by hitesh on 6/21/17.
 */

public interface BaseView {
    void showLoading();

    void hideLoading();

    void getDataFail(String message);

    void moveToDetail(Intent intent);

    void getListNews(List<Long> listIds);

    void onDataCompleted();
}
