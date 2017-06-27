package propertyguru.hackernews.network;

import java.util.List;

import propertyguru.hackernews.model.Comment;
import propertyguru.hackernews.model.Story;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by hitesh on 6/21/17.
 */

public interface NetworkStores {
    @GET("{story}.json")
    Observable<List<Long>> getStory(@Path("story") String key);

    @GET("item/{story}.json")
    Observable<Story> getNews(@Path("story") long key);

    @GET("item/{itemId}.json")
    Observable<Comment> getCommentItem(@Path("itemId") long itemId);
}
