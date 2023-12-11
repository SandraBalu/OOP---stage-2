package app.pages;

import java.util.ArrayList;
import java.util.List;

public class LikedContentPage implements Page {
//    private List<String> likedSongs;
//    private List<String> followedPlaylists;
//
//    private LikedContentPage(String type) {
//        super(type);
//        likedSongs = new ArrayList<>();
//        followedPlaylists = new ArrayList<>();
//    }
//
//    public static LikedContentPage getInstance() {
//        return (LikedContentPage) Page.getInstance("Liked Content");
//    }

    @Override
    public String displayContent() {
//        return "\nLiked Songs:\n\t" + likedSongs + "\nFollowed Playlists:\n\t" + followedPlaylists;
        return "LIked";
    }

    // Metode specifice pentru LikedContentPage
}
