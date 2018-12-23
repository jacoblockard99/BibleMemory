package me.jacoblockard.biblememory;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for the BibleInfo class.
 */
public class BibleInfoTest {
    private static final String FILE_NAME = "bible-chapter-info.txt";

    @Test
    void testConstructors() throws IOException {
        BibleInfo chapterInfo;

        chapterInfo = new BibleInfo();
        assertFalse(chapterInfo.isLoaded());
        chapterInfo.loadData(FILE_NAME);
        assertTrue(chapterInfo.isLoaded());

        chapterInfo = new BibleInfo(FILE_NAME);
        assertTrue(chapterInfo.isLoaded());
    }

    @Test
    void testGetChapterLength() throws IOException {
        BibleInfo bibleInfo;
        String book;

        bibleInfo = new BibleInfo(FILE_NAME);

        book = "Genesis"; // first book
        assertEquals(31, bibleInfo.getChapterLength(book, 1));
        assertEquals(18, bibleInfo.getChapterLength(book, 20));
        assertEquals(26, bibleInfo.getChapterLength(book, 50));

        book = "Malachi"; // in between
        assertEquals(14, bibleInfo.getChapterLength(book, 1));
        assertEquals(18, bibleInfo.getChapterLength(book, 3));
        assertEquals(6, bibleInfo.getChapterLength(book, 4));

        book = "Revelation"; // last book
        assertEquals(20, bibleInfo.getChapterLength(book, 1));
        assertEquals(22, bibleInfo.getChapterLength(book, 3));
        assertEquals(21, bibleInfo.getChapterLength(book, 22));

        book = "2 John"; // one chapter
        assertEquals(13, bibleInfo.getChapterLength(book, 1));
    }

    @Test
    void testGetBookLength() throws IOException {
        BibleInfo bibleInfo;
        String book;

        bibleInfo = new BibleInfo(FILE_NAME);

        book = "Genesis"; // first book
        assertEquals(50, bibleInfo.getBookLength(book));

        book = "Malachi"; // in between
        assertEquals(4, bibleInfo.getBookLength(book));

        book = "Revelation"; // last book
        assertEquals(22, bibleInfo.getBookLength(book));

        book = "2 John"; // one chapter
        assertEquals(1, bibleInfo.getBookLength(book));
    }

    @Test
    void testBookExists() throws IOException {
        BibleInfo bibleInfo;
        String book;

        bibleInfo = new BibleInfo(FILE_NAME);

        book = "Genesis";
        assertTrue(bibleInfo.bookExists(book));

        book = "Revelation";
        assertTrue(bibleInfo.bookExists(book));

        book = "genesis"; // case sensitive
        assertFalse(bibleInfo.bookExists(book));

        book = "2John"; // respects spaces
        assertFalse(bibleInfo.bookExists(book));

        book = " Revelation"; // respects whitespace
        assertFalse(bibleInfo.bookExists(book));
    }
}
