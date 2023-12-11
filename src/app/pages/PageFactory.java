package app.pages;

import app.user.User;
import fileio.input.CommandInput;

public class PageFactory {
    Page currentPage = new HomePage();
    public Page getPage(CommandInput commandInput, User user) {
        if (commandInput.getNextPage() == null) {
            HomePage homePage = new HomePage();
            homePage.setLikedSongs(user.getLikedSongs());
            homePage.setFollowedPlaylists(user.getFollowedPlaylists());
            return homePage;
        }

        switch (commandInput.getNextPage()) {
            case "HomePage":
                HomePage homePage = new HomePage();
                homePage.setLikedSongs(user.getLikedSongs());
                homePage.setFollowedPlaylists(user.getFollowedPlaylists());
                return homePage;

            default:
                return null;

        }

    }
}
