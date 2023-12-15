package app.audio.Files;

import app.audio.LibraryEntry;

public final class Event extends LibraryEntry {
    private String description;
    private String date;

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }


    /**
     * Instantiates a new Library entry.
     *
     * @param name the name
     */
    public Event(final String name, final String description, final String date) {
        super(name);
        this.description = description;
        this.date = date;
    }

}
