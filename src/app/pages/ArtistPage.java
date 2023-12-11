package app.pages;

import java.util.ArrayList;
import java.util.List;

public class ArtistPage implements Page {
//    private List<String> albums;
//    private List<String> merch;
//    private List<String> events;
//
//    private ArtistPage(String type) {
//        super(type);
//        albums = new ArrayList<>();
//        merch = new ArrayList<>();
//        events = new ArrayList<>();
//    }
//
//    public static ArtistPage getInstance() {
//        return (ArtistPage) Page.getInstance("Artist");
//    }

    @Override
    public String displayContent() {
//        return "\nAlbums:\n\t" + albums + "\nMerch:\n\t" + merch + "\nEvents:\n\t" + events;
        return "LIked";
    }

    // Metode specifice pentru ArtistPage
}
