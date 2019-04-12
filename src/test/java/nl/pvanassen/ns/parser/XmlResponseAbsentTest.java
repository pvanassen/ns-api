package nl.pvanassen.ns.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Almost the same test as XmlPresent, just to demonstrate, things won't break
 * 
 * @author Paul van Assen
 * 
 */
public class XmlResponseAbsentTest {

    private XmlResponseAbsent absent;

    @Before
    public void setUp() {
        absent = (XmlResponseAbsent) Response.getXml(getClass().getResourceAsStream("/test.xml"), "test").child("notexisting");
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
        assertTrue(absent.child("subelement") instanceof XmlResponseAbsent);
        assertNotNull(absent.child("doesNotExist"));
        assertTrue(absent.child("doesNotExist") instanceof XmlResponseAbsent);
    }

    @Test
    public void testChildren() {
        Response another = absent.child("another");
        List<Response> children = another.children("nice");
        assertNotNull(children);
        assertEquals(0, children.size());
    }

    @Test
    public void testAttr() {
        XmlResponse subelement = absent.child("subelement");
        assertNull("nice-and-pretty", subelement.attr("attribute"));
        assertNull(subelement.attr("doesnotexist"));
    }

    @Test
    public void testIsPresent() {
        assertFalse(absent.isPresent("doesnotexist"));
        assertFalse(absent.isPresent("subelement"));
    }

}
