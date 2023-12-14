package app.pages;

import app.user.User;

public final class PageFactory {

    /**
     * create different types of pages objects
     *
     * @param user the user whose page is generated
     * @param type the type of the generated page
     *
     * @return the page
     */
    public  Page getPage(final String type, final User user) {
        switch (type) {
            case "Home":
                return new HomePage(user.getUsername(),
                        user.getLikedSongs(), user.getFollowedPlaylists());
            case "LikedContent":
                return new LikedContent(user.getUsername(),
                        user.getLikedSongs(), user.getFollowedPlaylists());
            case "ArtistPage":
                return new ArtistPage(user.getUsername(),
                        user.getAlbums(), user.getMerches(), user.getEvents());
            case "HostPage":
                return new HostPage(user.getUsername(),
                        user.getPodcasts(), user.getAnnouncements());
            default:
                return null;
        }
    }
}
