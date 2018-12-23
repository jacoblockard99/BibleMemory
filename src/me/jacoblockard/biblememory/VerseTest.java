package me.jacoblockard.biblememory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for the Verse class.
 */
public class VerseTest {
    @Test
    void testConstructor() {
        // Invalid book
        assertThrows(IllegalArgumentException.class, () -> new Verse("A Random Book", 6, 7));

        // Invalid chapter
        assertThrows(IllegalArgumentException.class, () -> new Verse("Genesis", 51, 7));

        // Invalid verse
        assertThrows(IllegalArgumentException.class, () -> new Verse("Genesis", 6, 23));

        // Valid verse
        new Verse("Genesis", 2, 4);
    }

    @Test
    void testAccessors() {
        Verse verse;

        verse = new Verse("Genesis", 1, 1);

        // Invalid book
        assertThrows(IllegalArgumentException.class, () -> verse.setBook("A Random Book"));
        assertEquals("Genesis", verse.getBook());

        // Invalid chapter
        assertThrows(IllegalArgumentException.class, () -> verse.setChapterNumber(51));
        assertEquals(1, verse.getChapterNumber());

        // Invalid verse
        assertThrows(IllegalArgumentException.class, () -> verse.setVerseNumber(32));
        assertEquals(1, verse.getVerseNumber());
    }

    @Test
    void testEqualsTo() {
        Verse verse1, verse2;

        // Different books
        verse1 = new Verse("Proverbs", 1, 4);
        verse2 = new Verse("Job", 1, 4);
        assertFalse(verse1.equals(verse2));
        assertFalse(verse2.equals(verse1));

        // Different chapters
        verse1 = new Verse("Proverbs", 2, 4);
        verse2 = new Verse("Proverbs", 1, 4);
        assertFalse(verse1.equals(verse2));
        assertFalse(verse2.equals(verse1));

        // Different verses
        verse1 = new Verse("Proverbs", 1, 7);
        verse2 = new Verse("Proverbs", 1, 4);
        assertFalse(verse1.equals(verse2));
        assertFalse(verse2.equals(verse1));

        // Same verse
        verse1 = new Verse("Proverbs", 1, 4);
        verse2 = new Verse("Proverbs", 1, 4);
        assertTrue(verse1.equals(verse2));
        assertTrue(verse2.equals(verse1));
    }

    @Test
    void testIsGreaterThan() {
        Verse verse1, verse2;

        // Same book, same chapter
        verse1 = new Verse("Genesis", 5, 3);
        verse2 = new Verse("Genesis", 5, 7);
        assertTrue(verse2.isGreaterThan(verse1));
        assertFalse(verse1.isGreaterThan(verse2));

        // Same book, different chapters, same verse number
        verse1 = new Verse("Genesis", 5, 3);
        verse2 = new Verse("Genesis", 50, 3);
        assertTrue(verse2.isGreaterThan(verse1));
        assertFalse(verse1.isGreaterThan(verse2));

        // Different books
        final Verse v1 = new Verse("Genesis", 5, 3);
        final Verse v2 = new Verse("Exodus", 25, 3);
        assertThrows(IllegalArgumentException.class, () -> v1.isGreaterThan(v2));
        assertThrows(IllegalArgumentException.class, () -> v2.isGreaterThan(v1));
    }

    @Test
    void testIsLessThan() {
        Verse verse1, verse2;

        // Same book, same chapter
        verse1 = new Verse("Genesis", 5, 3);
        verse2 = new Verse("Genesis", 5, 7);
        assertFalse(verse2.isLessThan(verse1));
        assertTrue(verse1.isLessThan(verse2));

        // Same book, different chapters, same verse number
        verse1 = new Verse("Genesis", 5, 3);
        verse2 = new Verse("Genesis", 50, 3);
        assertFalse(verse2.isLessThan(verse1));
        assertTrue(verse1.isLessThan(verse2));

        // Different books
        final Verse v1 = new Verse("Genesis", 5, 3);
        final Verse v2 = new Verse("Exodus", 25, 3);
        assertThrows(IllegalArgumentException.class, () -> v1.isLessThan(v2));
        assertThrows(IllegalArgumentException.class, () -> v2.isLessThan(v1));
    }

}
