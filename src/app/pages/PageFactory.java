package app.pages;

import app.user.User;

public class PageFactory {
    public Page getPage(String type, User user) {
        switch (type) {
            case "Home":
                return new HomePage(user.getUsername(), user.getLikedSongs(), user.getFollowedPlaylists());
            case "LikedContent":
                return new LikedContent(user.getUsername(), user.getLikedSongs(), user.getFollowedPlaylists());
            case "ArtistPage":
                return new ArtistPage(user.getUsername(), user.getAlbums(),user.getMerches(), user.getEvents());
            case "HostPage":
                return new HostPage(user.getUsername(), user.getPodcasts(), user.getAnnouncements());
            default:
                return null;
        }
    }
}
