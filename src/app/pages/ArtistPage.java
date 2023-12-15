package app.pages;

import app.audio.Collections.Album;
import app.audio.Files.Event;
import app.audio.Files.Merch;
import java.util.ArrayList;
import java.util.List;

public class ArtistPage implements Page {

    private String username;
    private List<String> albums;

    private List<String> merches = new ArrayList<>();
    private List<String> events = new ArrayList<>();


    public ArtistPage(final String username, final List<Album> albums,
                      final ArrayList<Merch> merches, final ArrayList<Event> events) {
        this.username = username;
        this.albums = albums.stream().map(Album::getName).toList();

        for (Merch merch : merches) {
            StringBuilder merchOutput = new StringBuilder();
            merchOutput.append(merch.getName()).append(" - ").
                    append(merch.getPrice()).append(":\n\t").append(merch.getDescription());
            this.merches.add(merchOutput.toString());
        }
        for (Event event : events) {
            StringBuilder eventOutput = new StringBuilder();
            eventOutput.append(event.getName()).append(" - ").
                    append(event.getDate()).append(":\n\t").append(event.getDescription());
            this.events.add(eventOutput.toString());
        }
    }

    /**
     *String for displaying current page content
     */
    @Override
    public String displayContent() {

        return "Albums:\n\t" + albums + "\n\nMerch:\n\t" + merches + "\n\nEvents:\n\t" + events;
    }

    /**
     *String for displaying change page message
     */
    @Override
    public String switchMessage() {
        return "ArtistPage";
    }

}
