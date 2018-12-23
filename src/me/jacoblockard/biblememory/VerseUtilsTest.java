package me.jacoblockard.biblememory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for the VerseUtils class.
 */
public class VerseUtilsTest {
    Verse verse, verse2;

    @Test
    void testDistanceBetween() {
        // Different books
        verse = new Verse("Genesis", 10, 2);
        verse2 = new Verse("Revelation", 11, 9);
        assertThrows(IllegalArgumentException.class, () -> VerseUtils.distanceBetween(verse, verse2));

        // Invalid verse
        verse = new Verse("Invalid Book", 1, 1);
        verse2 = new Verse("Genesis", 1, 1);
        assertThrows(IllegalArgumentException.class, () -> VerseUtils.distanceBetween(verse, verse2));

        // Same chapter
        verse = new Verse("Genesis", 10, 2);
        verse2 = new Verse("Genesis", 10, 5);
        assertEquals(3, VerseUtils.distanceBetween(verse, verse2));

        // One chapter difference
        verse = new Verse("Genesis", 10, 2);
        verse2 = new Verse("Genesis", 11, 5);
        assertEquals(35, VerseUtils.distanceBetween(verse, verse2));

        // Multiple chapter difference
        verse = new Verse("Genesis", 10, 2);
        verse2 = new Verse("Genesis", 14, 5);
        assertEquals(105, VerseUtils.distanceBetween(verse, verse2));

        // Negative //

        // Same chapter
        verse2 = new Verse("Genesis", 10, 2);
        verse = new Verse("Genesis", 10, 5);
        assertEquals(-3, VerseUtils.distanceBetween(verse, verse2));

        // One chapter difference
        verse2 = new Verse("Genesis", 10, 2);
        verse = new Verse("Genesis", 11, 5);
        assertEquals(-35, VerseUtils.distanceBetween(verse, verse2));

        // Multiple chapter difference
        verse2 = new Verse("Genesis", 10, 2);
        verse = new Verse("Genesis", 14, 5);
        assertEquals(-105, VerseUtils.distanceBetween(verse, verse2));
    }

    @Test
    void testIsVerseValid() {
        Verse verse;

        // Invalid book
        verse = new Verse("Invalid Book", 1, 1);
        assertFalse(VerseUtils.isVerseValid(verse));

        // Invalid chapter number
        verse = new Verse("Genesis", 51, 1);
        assertFalse(VerseUtils.isVerseValid(verse));

        // Invalid verse number
        verse = new Verse("Genesis", 1, 32);
        assertFalse(VerseUtils.isVerseValid(verse));

        // All invalid
        verse = new Verse("Invalid Book", 51, 32);
        assertFalse(VerseUtils.isVerseValid(verse));

        // Negative values
        verse = new Verse("Genesis", -1, -100);
        assertFalse(VerseUtils.isVerseValid(verse));

        // Valid verse
        verse = new Verse("Genesis", 1, 1);
        assertTrue(VerseUtils.isVerseValid(verse));
    }
}
