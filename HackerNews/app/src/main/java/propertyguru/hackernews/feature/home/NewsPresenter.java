package propertyguru.hackernews.feature.home;

import java.util.List;

import propertyguru.hackernews.base.ui.BasePresenter;
import propertyguru.hackernews.model.Story;
import propertyguru.hackernews.network.NetworkCallback;
import propertyguru.hackernews.network.NetworkStores;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by hitesh on 6/22/17.
 */

public class NewsPresenter extends BasePresenter<NewsView> {


    public NewsPresenter(NewsView baseView, NetworkStores networkStores) {
        super.attachView(baseView);
        this.apiStores = networkStores;
    }

    public void loadNews(final String type) {
        view.showLoading();
        addSubscribe(getTopStories(), new NetworkCallback<Story>() {

            @Override
            public void onSuccess(Story model) {
                view.onDataSuccess(model);
            }

            @Override
            public void onFailure(String message) {
                view.getDataFail(message);
            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }

    public void refresh() {
        onUnsubscribe();
        loadNews("");
    }

    public Observable<Story> getTopStories() {
        return apiStores.getStory(NewsActivity.TOPSTORIES).concatMap(new Func1<List<Long>, Observable<? extends Story>>() {
            @Override
            public Observable<? extends Story> call(List<Long> longs) {
                return getPostsFromIds(longs);
            }
        });
    }

    public Observable<Story> getPostsFromIds(List<Long> storyIds) {
        return Observable.from(storyIds).concatMap(new Func1<Long, Observable<Story>>() {
            @Override
            public Observable<Story> call(Long aLong) {
                return apiStores.getNews(aLong);
            }
        }).concatMap(new Func1<Story, Observable<Story>>() {
            @Override
            public Observable<Story> call(Story post) {
                return post.getTitle() != null ? Observable.just(post) : Observable.<Story> empty();
            }
        });
    }


}
