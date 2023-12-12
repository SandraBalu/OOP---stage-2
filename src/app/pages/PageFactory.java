package app.pages;

import app.user.User;
import fileio.input.CommandInput;

public class PageFactory {
    public Page getPage(String type, User user) {
        switch (type) {
            case "HomePage":
                return new HomePage(user.getUsername(), user.getLikedSongs(), user.getFollowedPlaylists());
            case "LikedContent":
                return new LikedContentPage(user.getUsername());
            case "ArtistPage":
                return new ArtistPage(user.getUsername(), user.getAlbums(),user.getMerches(), user.getEvents());
            case "HostPage":
                return new HostPage(user.getUsername(), user.getPodcasts(), user.getAnnouncements());
            default:
                return null;
        }
    }
}
