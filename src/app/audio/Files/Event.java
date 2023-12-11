package app.audio.Files;

import app.audio.LibraryEntry;

public class Event extends LibraryEntry {
    private String description;
    private String date;

    /**
     * Instantiates a new Library entry.
     *
     * @param name the name
     */
    public Event(String name, String description, String date) {
        super(name);
        this.description = description;
        this.date = date;
    }

}
