package app.pages;

import java.util.ArrayList;
import java.util.List;

public class LikedContentPage implements Page {

    private String username;

    public LikedContentPage(String username) {
        this.username = username;
    }

    @Override
    public String displayContent() {
//        return "\nLiked Songs:\n\t" + likedSongs + "\nFollowed Playlists:\n\t" + followedPlaylists;
        return "Liked";
    }

    @Override
    public String switchMessage() {
        return username + " accessed LikedContent successfully.";
    }

    // Metode specifice pentru LikedContentPage

}
