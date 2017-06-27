package propertyguru.hackernews.base.ui;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import propertyguru.hackernews.network.NetworkStores;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hitesh on 6/21/17.
 */

public class BasePresenter<V> {
    public V                      view;
    private List<Subscription> compositeSubscription;
    private Subscriber            subscriber;
    public NetworkStores apiStores;

    public void attachView(V view) {
        this.view = view;

    }

    public void detachView() {
        this.view = null;
        onUnsubscribe();
    }

    public void onUnsubscribe() {
        if (compositeSubscription != null ) {
            for(Subscription subscription:compositeSubscription)
                subscription.unsubscribe();
            Log.e("TAG", "onUnsubscribe: ");
        }
    }

    protected void addSubscribe(Observable observable, Subscriber subscriber) {
        if (compositeSubscription == null) {
            compositeSubscription = new ArrayList<>();
        }
        compositeSubscription.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }


}
