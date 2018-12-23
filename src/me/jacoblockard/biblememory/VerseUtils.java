package me.jacoblockard.biblememory;

import java.io.IOException;

/**
 * A utility class that contains methods for working with Verse and VerseRanges.
 */
public class VerseUtils {
    public static final String BIBLE_INFO_FILE_NAME = "bible-chapter-info.txt";

    /**
     * The BibleInfo object.
     */
    private static final BibleInfo bibleInfo;

    static {
        try {
            bibleInfo = new BibleInfo(BIBLE_INFO_FILE_NAME);
        } catch (IOException e) {
            throw new Error("An IO error occurred!");
        }
    }

    /**
     * Calculates the distance (in verses) between two Verses.
     *
     * @param verse1 The first Verse.
     * @param verse2 The second Verse.
     * @return An int representing the distance (in verses) between the two given Verses.
     * @throws IllegalArgumentException if the two verses are in different books of the bible.
     */
    public static int distanceBetween(Verse verse1, Verse verse2) {
        Verse minVerse, maxVerse;
        int distance, sign, diff, chapterLength;

        if (!(isVerseValid(verse1) && isVerseValid(verse2))) {
            throw new IllegalArgumentException("Both verses must be valid!");
        }

        if (!verse1.getBook().equals(verse2.getBook())) {
            throw new IllegalArgumentException("The two verses are not in the same book!");
        }

        distance = 0;

        diff = verse2.getChapterNumber() - verse1.getChapterNumber();
        // Get the eventual sign of the result value.
        sign = Integer.signum(diff);
        if (sign == 0) {
            sign = 1;
        }
        // Make diff positive.
        diff = Math.abs(diff);

        // Calculate the minimum and maximum verses.
        // Note that if the verses are in the same chapter then verse1 will be the min and verse2 will be the max.
        if (verse1.getChapterNumber() <= verse2.getChapterNumber()) {
            minVerse = verse1;
            maxVerse = verse2;
        } else {
            minVerse = verse2;
            maxVerse = verse1;
        }

        // Add the appropriate amount for the first and last chapters.
        if (diff == 0) {
            // If the verses are in the same chapter:
            // Add the difference between the two verses.
            distance += maxVerse.getVerseNumber() - minVerse.getVerseNumber();
        } else {
            // Otherwise:
            // Add the amount of verses it takes to get to the next chapter.
            chapterLength = bibleInfo.getChapterLength(minVerse.getBook(), minVerse.getChapterNumber());
            distance += chapterLength - minVerse.getVerseNumber();
            // Add the second verse's verse number
            distance += maxVerse.getVerseNumber();
        }

        for (int i = 1; i < diff; i++) {
            // For each "extra" chapter (i.e. not the first or last chapter), add the length of the chapter.
            chapterLength = bibleInfo.getChapterLength(minVerse.getBook(), minVerse.getChapterNumber() + i);
            distance += chapterLength;
        }

        return distance * sign;
    }

    /**
     * Returns true if the given verse is "valid". A valid verse is a verse whose book exists in the bible, whose
     * chapter number is not greater than the length of the verse's book, and whose verse number is not greater than the
     * length of the verse's chapter. A verse in also invalid if it has a negative verse or chapter number.
     *
     * @param verse The verse to validate.
     * @return True if the given verse is "valid".
     */
    public static boolean isVerseValid(Verse verse) {
        return  verse.getVerseNumber() > 0 &&
                verse.getChapterNumber() > 0 &&
                bibleInfo.bookExists(verse.getBook()) &&
                verse.getChapterNumber() <= bibleInfo.getBookLength(verse.getBook()) &&
                verse.getVerseNumber() <= bibleInfo.getChapterLength(verse.getBook(), verse.getChapterNumber());
    }
}
