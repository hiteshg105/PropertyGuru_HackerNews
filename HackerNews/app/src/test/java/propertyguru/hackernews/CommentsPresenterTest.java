package propertyguru.hackernews;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import propertyguru.hackernews.feature.comments.CommentsPresenter;
import propertyguru.hackernews.feature.comments.CommentsView;
import propertyguru.hackernews.network.NetworkStores;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.functions.Func1;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.times;

/**
 * Created by hitesh on 6/25/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommentsPresenterTest {

    @Mock
    private NetworkStores     apiStores;

    @Mock
    private CommentsView      commentsView;

    private CommentsPresenter commentsPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        commentsPresenter = new CommentsPresenter(commentsView, apiStores);
    }

    @Before
    public void setUp() throws Exception {
        // Override RxJava schedulers
        RxJavaHooks.setOnIOScheduler(new Func1<Scheduler, Scheduler>() {
            @Override
            public Scheduler call(Scheduler scheduler) {
                return Schedulers.immediate();
            }
        });

        RxJavaHooks.setOnComputationScheduler(new Func1<Scheduler, Scheduler>() {
            @Override
            public Scheduler call(Scheduler scheduler) {
                return Schedulers.immediate();
            }
        });

        RxJavaHooks.setOnNewThreadScheduler(new Func1<Scheduler, Scheduler>() {
            @Override
            public Scheduler call(Scheduler scheduler) {
                return Schedulers.immediate();
            }
        });

        // Override RxAndroid schedulers
        final RxAndroidPlugins rxAndroidPlugins = RxAndroidPlugins.getInstance();
        rxAndroidPlugins.registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    @After
    public void tearDown() throws Exception {
        RxJavaHooks.reset();
        RxAndroidPlugins.getInstance().reset();
    }

    @Test
    public void fetchValidDataShouldLoadIntoView() {

        List<Long> commentIds = new ArrayList<>();
        commentsPresenter.loadComments(commentIds);
        InOrder inOrder = Mockito.inOrder(commentsView);
        inOrder.verify(commentsView, times(1)).showLoading();
        inOrder.verify(commentsView, times(1)).hideLoading();
    }

    @Test
    public void fetchInValidDataShouldLoadIntoView() {

        List<Long> commentIds = new ArrayList<>();
        commentsPresenter.loadComments(commentIds);
        InOrder inOrder = Mockito.inOrder(commentsView);
        inOrder.verify(commentsView, times(1)).showLoading();
        inOrder.verify(commentsView, times(1)).hideLoading();

    }

    @Test
    public void detachViewTest() {
        commentsPresenter.detachView();
        Assert.assertNull(commentsPresenter.view);
    }


}
