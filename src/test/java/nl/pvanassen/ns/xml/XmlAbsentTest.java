package nl.pvanassen.ns.xml;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Almost the same test as XmlPresent, just to demonstrate, things won't break
 * 
 * @author Paul van Assen
 * 
 */
public class XmlAbsentTest {

    private XmlAbsent absent;

    @Before
    public void setUp() {
        absent = (XmlAbsent) Xml.getXml(getClass().getResourceAsStream("/test.xml"), "test").child("notexisting");
    }

    @Test
    public void testName() {
        Assert.assertEquals("notexisting", absent.name());
    }

    @Test
    public void testContent() {
        Assert.assertNull(absent.child("content").content());
    }

    @Test
    public void testChild() {
        Assert.assertNotNull(absent.child("subelement"));
        Assert.assertTrue(absent.child("subelement") instanceof XmlAbsent);
        Assert.assertNotNull(absent.child("doesNotExist"));
        Assert.assertTrue(absent.child("doesNotExist") instanceof XmlAbsent);
    }

    @Test
    public void testChildren() {
        Xml another = absent.child("another");
        List<Xml> children = another.children("nice");
        Assert.assertNotNull(children);
        Assert.assertEquals(0, children.size());
    }

    @Test
    public void testAttr() {
        Xml subelement = absent.child("subelement");
        Assert.assertNull("nice-and-pretty", subelement.attr("attribute"));
        Assert.assertNull(subelement.attr("doesnotexist"));
    }

    @Test
    public void testIsPresent() {
        Assert.assertFalse(absent.isPresent("doesnotexist"));
        Assert.assertFalse(absent.isPresent("subelement"));
    }

}
