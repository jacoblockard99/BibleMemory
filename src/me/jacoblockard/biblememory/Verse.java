package me.jacoblockard.biblememory;

import java.io.IOException;

/**
 * Represents a single bible verse by book, chapter, and verse number.
 */
public class Verse {
    /**
     * This Verse's book of the bible.
     */
    private String book;

    /**
     * This Verse's chapter number.
     */
    private int chapterNumber;

    /**
     * This Verse's verse number.
     */
    private int verseNumber;

    /**
     * The BibleInfo object.
     */
    private BibleInfo bibleInfo;

    /**
     * Creates a new instance of Verse with the specified book, chapter, and verse number.
     *
     * @param book          The book.
     * @param chapterNumber The chapter number.
     * @param verseNumber   The verse number.
     */
    public Verse(String book, int chapterNumber, int verseNumber) {
        try {
            bibleInfo = new BibleInfo(VerseUtils.BIBLE_INFO_FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setBook(book);
        setChapterNumber(chapterNumber);
        setVerseNumber(verseNumber);
    }

    /**
     * Gets this Verse's book of the bible.
     *
     * @return A String representing this Verse's book of the bible.
     */
    public String getBook() {
        return book;
    }

    /**
     * Sets this Verse's book of the bible.
     *
     * @param book The book of the bible.
     */
    public void setBook(String book) {
        if (!bibleInfo.bookExists(book)) {
            throw new IllegalArgumentException("The given book does not exists in the bible!");
        }
        this.book = book;
    }

    /**
     * Gets this Verse's chapter number.
     *
     * @return An int representing this Verse's chapter number;
     */
    public int getChapterNumber() {
        return chapterNumber;
    }

    /**
     * Sets this Verse's chapter number.
     *
     * @param chapterNumber The chapter number.
     */
    public void setChapterNumber(int chapterNumber) {
        if (chapterNumber > bibleInfo.getBookLength(getBook())) {
            throw new IllegalArgumentException("The chapter number cannot be greater than the length of the book!");
        }
        this.chapterNumber = chapterNumber;
    }

    /**
     * Gets this Verse's verse number.
     *
     * @return An int representing this Verse's verse number;
     */
    public int getVerseNumber() {
        return verseNumber;
    }

    /**
     * Sets this Verse's verse number.
     *
     * @param verseNumber The verse number.
     */
    public void setVerseNumber(int verseNumber) {
        if (verseNumber > bibleInfo.getChapterLength(getBook(), getChapterNumber())) {
            throw new IllegalArgumentException("The verse number cannot be greater than the length of the chapter!");
        }
        this.verseNumber = verseNumber;
    }

    /**
     * Returns true if this Verse is greater than (i.e. comes farther along in the bible than) another Verse.
     *
     * @param other The Verse to compare with.
     * @return True if this Verse is greater than the given Verse.
     */
    public boolean isGreaterThan(Verse other) {
        return VerseUtils.distanceBetween(other, this) > 0;
    }

    /**
     * Returns true if this Verse is less than (i.e. comes sooner in the bible than) another Verse.
     *
     * @param other The Verse to compare with.
     * @return True if this Verse is less than the given Verse.
     */
    public boolean isLessThan(Verse other) {
        return other.isGreaterThan(this);
    }

    /**
     * Returns true if this Verse is exactly the same as another Verse.
     *
     * @param other The verse to compare with.
     * @return True if this Verse is exactly the same as another Verse.
     */
    public boolean equals(Verse other) {
        return getBook().equals(other.getBook()) &&
                getChapterNumber() == other.getChapterNumber() &&
                getVerseNumber() == other.getVerseNumber();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Verse)) {
            return super.equals(other);
        } else {
            return equals((Verse) other);
        }
    }
}
