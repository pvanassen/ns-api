package nl.pvanassen.ns.xml;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Almost the same test as XmlPresent, just to demonstrate, things won't break
 *
 * @author Paul van Assen
 */
public class XmlAbsentTest {

    private XmlAbsent absent;

    @BeforeEach
    void setUp() {
        absent = (XmlAbsent) Xml.getXml(getClass().getResourceAsStream("/test.xml"), "test").child("notexisting");
    }

    @Test
    void testName() {
        assertEquals("notexisting", absent.name());
    }

    @Test
    void testContent() {
        assertNull(absent.child("content").content());
    }

    @Test
    void testChild() {
        assertNotNull(absent.child("subelement"));
        assertTrue(absent.child("subelement") instanceof XmlAbsent);
        assertNotNull(absent.child("doesNotExist"));
        assertTrue(absent.child("doesNotExist") instanceof XmlAbsent);
    }

    @Test
    void testChildren() {
        Xml another = absent.child("another");
        List<Xml> children = another.children("nice");
        assertNotNull(children);
        assertEquals(0, children.size());
    }

    @Test
    void testAttr() {
        Xml subelement = absent.child("subelement");
        assertNull(subelement.attr("attribute"));
        assertNull(subelement.attr("doesnotexist"));
    }

    @Test
    void testIsPresent() {
        assertFalse(absent.isPresent("doesnotexist"));
        assertFalse(absent.isPresent("subelement"));
    }

}
