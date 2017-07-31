package propertyguru.hackernews;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import propertyguru.hackernews.model.Comment;

/**
 * Created by hitesh on 7/31/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class CommentTest {

    private Comment comment;
    @Before
    public void setUp() {
        comment = new Comment();
    }

    @Test
    public void testText() {
        comment.setText("Text");
        Assert.assertEquals("Text", comment.getText());
    }
    @Test
    public void testId() {
        comment.setId(1l);
        Assert.assertEquals(1l, comment.getId());
    }
    @Test
    public void testTime() {
        comment.setTime(1l);
        Assert.assertEquals(1l, comment.getTime());
    }

    @Test
    public void testKids() {
        ArrayList<Long> arrayList=new ArrayList<>();
        arrayList.add(1l);
        comment.setKids(arrayList);
        Assert.assertEquals(1l, comment.getKids().get(0).longValue());

    }
    @Test
    public void testType() {
        comment.setType("type");
        Assert.assertEquals("type", comment.getType());
    }
    @Test
    public void testBY() {
        comment.setBy("by");
        Assert.assertEquals("by", comment.getBy());
    }
    @Test
    public void testDepth() {
        comment.setDepth(1);
        Assert.assertEquals(1, comment.getDepth());
    }


}
