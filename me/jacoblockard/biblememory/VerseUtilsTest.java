package me.jacoblockard.biblememory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * JUnit tests for the VerseUtils class.
 */
public class VerseUtilsTest {
    @Test
    void testDistanceBetween() {
        Verse verse, verse2;
        final Verse finalVerse, finalVerse2;

        // Different books
        finalVerse = new Verse("Genesis", 10, 2);
        finalVerse2 = new Verse("Revelation", 11, 9);
        assertThrows(IllegalArgumentException.class, () -> VerseUtils.distanceBetween(finalVerse, finalVerse2));

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

        // Negative

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
}
