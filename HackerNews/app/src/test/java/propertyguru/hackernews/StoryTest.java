package propertyguru.hackernews;

import android.os.Parcel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import propertyguru.hackernews.model.Story;

/**
 * Created by hitesh on 7/27/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class StoryTest {
    private Story story;

    @Mock
    Parcel parcel;
    @Before
    public void setUp() {
        story = new Story();
    }

    @Test
    public void testTitle() {
        story.setTitle("title");
        Assert.assertEquals("title", story.getTitle());
    }
    @Test
    public void testId() {
        story.setId(1l);
        Assert.assertEquals(1l, story.getId());
    }
    @Test
    public void testTime() {
        story.setTime(1l);
        Assert.assertEquals(1l, story.getTime());
    }
    @Test
    public void testScore() {
        story.setScore(1l);
        Assert.assertEquals(1l, story.getScore());
    }
    @Test
    public void testKids() {
        ArrayList<Long> arrayList=new ArrayList<>();
        arrayList.add(1l);
        story.setKids(arrayList);
        Assert.assertEquals(1l, story.getKids().get(0).longValue());

    }
    @Test
    public void testType() {
        story.setType("type");
        Assert.assertEquals("type", story.getType());
    }
    @Test
    public void testBY() {
        story.setBy("by");
        Assert.assertEquals("by", story.getBy());
    }
    @Test
    public void testUrl() {
        story.setUrl("url");
        Assert.assertEquals("url", story.getUrl());
    }
    @Test
    public void testParent() {
        story.setParent("parent");
        Assert.assertEquals("parent", story.getParent());
    }
    @Test
    public void testParts() {
        String[] strings= new String[]{"test"};
        story.setParts(strings);
        Assert.assertEquals("test", story.getParts()[0]);
    }

    @Test
    public void testDescendants() {
        story.setDescendants("descendants");
        Assert.assertEquals("descendants", story.getDescendants());
    }

    @Test
    public void testIsDead() {

        Assert.assertFalse(story.isDead());
        story.setDead(true);
        Assert.assertTrue(story.isDead());
    }



}
