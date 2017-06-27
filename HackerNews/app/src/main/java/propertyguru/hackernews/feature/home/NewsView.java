package propertyguru.hackernews.feature.home;

import propertyguru.hackernews.base.ui.BaseView;
import propertyguru.hackernews.model.Story;

/**
 * Created by hitesh on 6/22/17.
 */

public interface NewsView extends BaseView {
    void onDataSuccess(Story model);
}
