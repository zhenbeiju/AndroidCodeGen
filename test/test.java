import org.junit.Assert;
import org.junit.Test;
import template.util.StringCaseUtil;

public class test {
    @Test
    public void testXMLCase() {
        String result = StringCaseUtil.XMLCase("HelloWorldGe");
        Assert.assertTrue(result.equals("hello_world_ge"));
    }

    @Test
    public void testUpCase() {
        Assert.assertTrue("Hello".equals(StringCaseUtil.UpCase("hello")));
        Assert.assertTrue("Hello".equals(StringCaseUtil.UpCase("Hello")));
    }

    @Test
    public void testLowerCase() {
        Assert.assertTrue("hello".equals(StringCaseUtil.LowCase("hello")));
        Assert.assertTrue("hello".equals(StringCaseUtil.LowCase("Hello")));
    }
}