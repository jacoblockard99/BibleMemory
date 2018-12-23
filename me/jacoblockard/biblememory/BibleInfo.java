package me.jacoblockard.biblememory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains methods for loading information about individual bible chapters from an input file.
 */
public class BibleInfo {
    /**
     * Contains the bible chapter data.
     */
    private Map<String, List<Integer>> data;

    /**
     * Whether or not the data has been loaded yet.
     */
    private boolean loaded;

    /**
     * Creates a new, empty instance of BibleInfo. Note that before accessing any data, loadData(fileName) must be
     * called.
     */
    public BibleInfo() {
        data = new HashMap<>();
        setLoaded(false);
    }

    /**
     * Creates a new instance of BibleInfo and loads chapter data from the given file.
     *
     * @param fileName The name of the file to load chapter data from.
     * @throws IOException if an IO error occurs while loading chapter data.
     */
    public BibleInfo(String fileName) throws IOException {
        this();
        loadData(fileName);
    }

    /**
     * Loads bible chapter information from the given text file.
     *
     * @throws IOException if an IO error occurs.
     */
    public void loadData(String fileName) throws IOException {
        Files.lines(Paths.get(fileName)).forEach(this::addLine);
        setLoaded(true);
    }

    /**
     * Parses and adds a single line of bible chapter information to the data map.
     *
     * @param line The line to parse/add.
     */
    private void addLine(String line) {
        String book;
        String[] split;
        ArrayList<Integer> chapters;

        split = line.split("\t");
        book = split[0];
        chapters = new ArrayList<>();

        for (int i = 1; i < split.length; i++) {
            chapters.add(Integer.parseInt(split[i]));
        }

        data.put(book, chapters);
    }

    /**
     * Gets the length (in verses) of a given chapter.
     *
     * @param book          The bible book.
     * @param chapterNumber The chapter number.
     * @return An int representing the length (in verses) of the given chapter.
     * @throws IllegalStateException if the bible chapter data has not been loaded yet.
     */
    public int getChapterLength(String book, int chapterNumber) {
        checkLoaded();
        return data.get(book).get(chapterNumber - 1);
    }

    /**
     * Gets the amount of chapters in a given book of the bible.
     *
     * @param book The book of the bible.
     * @return An int representing the length (in chapters) of the given book.
     * @throws IllegalStateException if the bible chapter data has not been loaded yet.
     */
    public int getBookLength(String book) {
        checkLoaded();
        return data.get(book).size();
    }

    /**
     * Returns true if the given book exists in the currently loaded bible.
     *
     * @param book The book of the bible.
     * @return True if the given book exists in the bible.
     * @throws IllegalStateException if the bible chapter data has not been loaded yet.
     */
    public boolean bookExists(String book) {
        checkLoaded();
        return data.containsKey(book);
    }

    private void checkLoaded() {
        if (!isLoaded()) {
            throw new IllegalStateException("The bible chapter data has not been loaded yet!");
        }
    }

    /**
     * Returns true if the bible chapter data has been loaded.
     *
     * @return True if the bible chapter data has been loaded.
     */
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * Sets whether or not the bible chapter data has been loaded yet.
     *
     * @param loaded Whether or not the data has been loaded yet.
     */
    private void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }
}
