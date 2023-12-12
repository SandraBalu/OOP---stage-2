package app.audio.Files;

import app.audio.LibraryEntry;

public class Merch extends LibraryEntry {
    private String description;
    private int price;

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    /**
     * Instantiates a new Library entry.
     *
     * @param name the name
     */
    public Merch(String name, String description, int price) {
        super(name);
        this.description = description;
        this.price = price;
    }

}
