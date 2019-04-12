package nl.pvanassen.ns.parser;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class JsonResponsePresentTest {

    private JsonResponsePresent present;

    @Before
    public void setUp() {
        present = Response.getJson(getClass().getResourceAsStream("/test.json"));
    }


    @Test
    public void testName() {
        assertEquals("root", present.name());
    }

    @Test
    public void testContent() {
        assertEquals("Test", present.child("content").content());
    }

    @Test
    public void testChild() {
        assertNotNull(present.child("test"));
        assertTrue(present.child("test") instanceof JsonResponsePresent);
        assertNotNull(present.child("doesNotExist"));
        assertTrue(present.child("doesNotExist") instanceof JsonResponseAbsent);
    }

    @Test
    public void testChildren() {
        final List<JsonResponse> children = present.children("array");
        assertNotNull(children);
        assertEquals(5, children.size());
    }

    @Test
    public void testIsPresent() {
        assertFalse(present.isPresent("doesnotexist"));
        assertTrue(present.isPresent("object-array"));
    }

}
