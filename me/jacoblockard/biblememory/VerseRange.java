package me.jacoblockard.biblememory;

import java.io.IOException;

/**
 * Represents a range of verses within the same book of the bible.
 */
public class VerseRange {
    /**
     * The Verse that this range starts with.
     */
    private Verse startVerse;

    /**
     * The Verse that this range ends with.
     */
    private Verse endVerse;

    /**
     * Creates a new instance of VerseRange with the given beginning and ending Verses.
     *
     * @param startVerse The Verse to start the range with.
     * @param endVerse   The Verse to end the range with.
     * @throws IOException if an IO error occurs.
     */
    public VerseRange(Verse startVerse, Verse endVerse) throws IOException {
        setStartVerse(startVerse);
        setEndVerse(endVerse);
    }

    /**
     * Creates a new instance of VerseRange with the given book, beginning and ending chapter numbers, and beginning and
     * end verse numbers.
     *
     * @param book         The book of the bible.
     * @param startChapter The chapter number of the verse that this range starts with.
     * @param startVerse   The verse number of the verse that this range starts with.
     * @param endChapter   The chapter number of the verse that this range ends with.
     * @param endVerse     The verse  number of the verse that this range ends with.
     * @throws IOException if an IO error occurs.
     */
    public VerseRange(String book, int startChapter, int startVerse, int endChapter, int endVerse) throws IOException {
        this(new Verse(book, startChapter, startVerse), new Verse(book, endChapter, endVerse));
    }

    /**
     * Gets the Verse that this range starts with.
     *
     * @return A Verse object representing the verse that this range starts with.
     */
    public Verse getStartVerse() {
        return startVerse;
    }

    /**
     * Sets the Verse that this range starts with.
     *
     * @param startVerse The Verse to start the range with.
     * @throws IllegalArgumentException if the beginning Verse is greater than the ending Verse.
     */
    public void setStartVerse(Verse startVerse) {
        if (startVerse.isGreaterThan(endVerse)) {
            throw new IllegalArgumentException("The beginning Verse cannot be greater than the ending Verse.");
        }
        this.startVerse = startVerse;
    }

    /**
     * Gets the Verse that this range ends with.
     *
     * @return A Verse object representing the verse that this range ends with.
     */
    public Verse getEndVerse() {
        return endVerse;
    }

    /**
     * Sets the Verse that this range ends with.
     *
     * @param endVerse The Verse to end the range with.
     * @throws IllegalArgumentException if the beginning Verse is greater than the ending Verse.
     */
    public void setEndVerse(Verse endVerse) {
        if (endVerse.isLessThan(startVerse)) {
            throw new IllegalArgumentException("The ending Verse cannot be less than the beginning Verse.");
        }
        this.endVerse = endVerse;
    }
}
