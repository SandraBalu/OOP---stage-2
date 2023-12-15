package app.audio.Files;

import app.audio.LibraryEntry;

public final class Announcement extends LibraryEntry {

    private String description;
    /**
     * Instantiates a new Library entry.
     *
     * @param name the name
     */
    public Announcement(final String name, final String description) {
        super(name);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
