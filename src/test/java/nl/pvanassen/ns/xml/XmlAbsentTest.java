package nl.pvanassen.ns.xml;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
        assertEquals("notexisting", absent.name());
    }

    @Test
    public void testContent() {
        assertNull(absent.child("content").content());
    }

    @Test
    public void testChild() {
        assertNotNull(absent.child("subelement"));
        assertTrue(absent.child("subelement") instanceof XmlAbsent);
        assertNotNull(absent.child("doesNotExist"));
        assertTrue(absent.child("doesNotExist") instanceof XmlAbsent);
    }

    @Test
    public void testChildren() {
        Xml another = absent.child("another");
        List<Xml> children = another.children("nice");
        assertNotNull(children);
        assertEquals(0, children.size());
    }

    @Test
    public void testAttr() {
        Xml subelement = absent.child("subelement");
        assertNull("nice-and-pretty", subelement.attr("attribute"));
        assertNull(subelement.attr("doesnotexist"));
    }

    @Test
    public void testIsPresent() {
        assertFalse(absent.isPresent("doesnotexist"));
        assertFalse(absent.isPresent("subelement"));
    }

}
