package app.audio.Files;

import app.audio.LibraryEntry;

public class Announcement extends LibraryEntry {

    private String description;
    /**
     * Instantiates a new Library entry.
     *
     * @param name the name
     */
    public Announcement(String name, String description) {
        super(name);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
