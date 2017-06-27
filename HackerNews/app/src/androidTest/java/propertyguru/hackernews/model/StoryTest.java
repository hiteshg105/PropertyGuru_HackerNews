package propertyguru.hackernews.model;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * Created by hitesh on 6/24/17.
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class StoryTest {

    public static final String TEST_STRING = "This is a string";
    public static final long TEST_LONG = 12345678L;
    private Story story;

    @Before
    public void createStoryClass(){
        story=new Story();
    }
}
