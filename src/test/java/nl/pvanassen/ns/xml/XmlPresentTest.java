package nl.pvanassen.ns.xml;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class XmlPresentTest {

    private XmlPresent present;

    @Before
    public void setUp() {
        present = (XmlPresent) Xml.getXml(getClass().getResourceAsStream("/test.xml"), "test");
    }

    @Test
    public void testName() {
        Assert.assertEquals("test", present.name());
    }

    @Test
    public void testContent() {
        Assert.assertEquals("Test", present.child("content").content());
    }

    @Test
    public void testChild() {
        Assert.assertNotNull(present.child("subelement"));
        Assert.assertTrue(present.child("subelement") instanceof XmlPresent);
        Assert.assertNotNull(present.child("doesNotExist"));
        Assert.assertTrue(present.child("doesNotExist") instanceof XmlAbsent);
    }

    @Test
    public void testChildren() {
        Xml another = present.child("another");
        List<Xml> children = another.children("nice");
        Assert.assertNotNull(children);
        Assert.assertEquals(5, children.size());
    }

    @Test
    public void testAttr() {
        Xml subelement = present.child("subelement");
        Assert.assertEquals("nice-and-pretty", subelement.attr("attribute"));
        Assert.assertNull(subelement.attr("doesnotexist"));
    }

    @Test
    public void testIsPresent() {
        Assert.assertFalse(present.isPresent("doesnotexist"));
        Assert.assertTrue(present.isPresent("subelement"));
    }

}
