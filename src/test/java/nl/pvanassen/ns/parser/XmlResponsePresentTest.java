package nl.pvanassen.ns.parser;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class XmlResponsePresentTest {

    private XmlResponsePresent present;

    @Before
    public void setUp() {
        present = (XmlResponsePresent) Response.getXml(getClass().getResourceAsStream("/test.xml"), "test");
    }

    @Test
    public void testName() {
        assertEquals("test", present.name());
    }

    @Test
    public void testContent() {
        assertEquals("Test", present.child("content").content());
    }

    @Test
    public void testChild() {
        assertNotNull(present.child("subelement"));
        assertTrue(present.child("subelement") instanceof XmlResponsePresent);
        assertNotNull(present.child("doesNotExist"));
        assertTrue(present.child("doesNotExist") instanceof XmlResponseAbsent);
    }

    @Test
    public void testChildren() {
        Response<XmlResponse> another = present.child("another");
        List<XmlResponse> children = another.children("nice");
        assertNotNull(children);
        assertEquals(5, children.size());
    }

    @Test
    public void testAttr() {
        XmlResponse subelement = present.child("subelement");
        assertEquals("nice-and-pretty", subelement.attr("attribute"));
        assertNull(subelement.attr("doesnotexist"));
    }

    @Test
    public void testIsPresent() {
        assertFalse(present.isPresent("doesnotexist"));
        assertTrue(present.isPresent("subelement"));
    }

}
