package propertyguru.hackernews.feature.comments;

import java.util.List;

import propertyguru.hackernews.base.ui.BasePresenter;
import propertyguru.hackernews.model.Comment;
import propertyguru.hackernews.network.NetworkCallback;
import propertyguru.hackernews.network.NetworkStores;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by hitesh on 6/23/17.
 */

public class CommentsPresenter extends BasePresenter<CommentsView> {

    public CommentsPresenter(CommentsView baseView, NetworkStores apiStores) {
        super.attachView(baseView);
        this.apiStores=apiStores;
    }

    public void loadComments(final List<Long> commentIds) {
        view.showLoading();
        addSubscribe(getPostComments(commentIds, 0), new NetworkCallback<Comment>() {

            @Override
            public void onSuccess(Comment model) {
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

    public Observable<Comment> getPostComments(final List<Long> commentIds, final int depth) {
        return Observable.from(commentIds).concatMap(new Func1<Long, Observable<Comment>>() {
            @Override
            public Observable<Comment> call(Long aLong) {
                return apiStores.getCommentItem(aLong);
            }
        }).concatMap(new Func1<Comment, Observable<Comment>>() {
            @Override
            public Observable<Comment> call(Comment comment) {
                comment.setDepth(depth);
                if (comment.getKids() == null || comment.getKids().isEmpty()) {
                    return Observable.just(comment);
                } else {
                    return Observable.just(comment).mergeWith(getPostComments(comment.getKids(), depth + 1));
                }
            }
        }).filter(new Func1<Comment, Boolean>() {
            @Override
            public Boolean call(Comment comment) {
                return (comment.getBy() != null && !comment.getBy().trim().isEmpty() && comment.getText() != null && !comment.getText().trim().isEmpty());
            }
        });
    }
}
