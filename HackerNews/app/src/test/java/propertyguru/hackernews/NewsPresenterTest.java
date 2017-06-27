package propertyguru.hackernews;

import org.junit.After;
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

import propertyguru.hackernews.feature.home.NewsActivity;
import propertyguru.hackernews.feature.home.NewsPresenter;
import propertyguru.hackernews.feature.home.NewsView;
import propertyguru.hackernews.network.NetworkStores;
import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.functions.Func1;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Created by hitesh on 6/24/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class NewsPresenterTest {

    @Mock
    private NetworkStores apiStores;

    @Mock
    private NewsView      newsView;

    private NewsPresenter newsPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        newsPresenter = new NewsPresenter(newsView, apiStores);
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

        List<Long> storyIds = new ArrayList<>();

        when(apiStores.getStory(NewsActivity.TOPSTORIES)).thenReturn(Observable.just(storyIds));
        newsPresenter.loadNews(NewsActivity.TOPSTORIES);
        InOrder inOrder = Mockito.inOrder(newsView);
        inOrder.verify(newsView, times(1)).showLoading();
        inOrder.verify(newsView, times(1)).hideLoading();
    }

    @Test
    public void fetchInValidDataShouldLoadIntoView() {

        when(apiStores.getStory(NewsActivity.TOPSTORIES)).thenReturn(Observable.error(new Exception()));
        newsPresenter.loadNews(NewsActivity.TOPSTORIES);
        InOrder inOrder = Mockito.inOrder(newsView);
        inOrder.verify(newsView, times(1)).showLoading();
        inOrder.verify(newsView, times(1)).getDataFail(any());

    }
}
