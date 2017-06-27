package propertyguru.hackernews.feature.comments;

import propertyguru.hackernews.base.ui.BaseView;
import propertyguru.hackernews.model.Comment;

/**
 * Created by hitesh on 6/23/17.
 */

public interface CommentsView extends BaseView {
    void onDataSuccess(Comment model);
}
