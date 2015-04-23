package sk.vracon.sqlcomments.core;

import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {

    @Test
    public void testTransformToJavaIdentifier() {
        Assert.assertEquals("textId", Utils.transformToJavaIdentifier("text_id", false));
        Assert.assertEquals("TextId", Utils.transformToJavaIdentifier("text_id", true));
        
        Assert.assertEquals("textId", Utils.transformToJavaIdentifier("textId", false));
        Assert.assertEquals("TextId", Utils.transformToJavaIdentifier("textId", true));
    }
}
