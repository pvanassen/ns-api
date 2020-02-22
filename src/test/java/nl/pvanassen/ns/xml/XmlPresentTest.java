package nl.pvanassen.ns.xml;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class XmlPresentTest {

    private XmlPresent present;

    @BeforeEach
    void setUp() {
        present = (XmlPresent) Xml.getXml(getClass().getResourceAsStream("/test.xml"), "test");
    }

    @Test
    void testName() {
        assertEquals("test", present.name());
    }

    @Test
    void testContent() {
        assertEquals("Test", present.child("content").content());
    }

    @Test
    void testChild() {
        assertNotNull(present.child("subelement"));
        assertTrue(present.child("subelement") instanceof XmlPresent);
        assertNotNull(present.child("doesNotExist"));
        assertTrue(present.child("doesNotExist") instanceof XmlAbsent);
    }

    @Test
    void testChildren() {
        Xml another = present.child("another");
        List<Xml> children = another.children("nice");
        assertNotNull(children);
        assertEquals(5, children.size());
    }

    @Test
    void testAttr() {
        Xml subelement = present.child("subelement");
        assertEquals("nice-and-pretty", subelement.attr("attribute"));
        assertNull(subelement.attr("doesnotexist"));
    }

    @Test
    void testIsPresent() {
        assertFalse(present.isPresent("doesnotexist"));
        assertTrue(present.isPresent("subelement"));
    }

}
