package app.pages;

import app.audio.Collections.Playlist;
import app.audio.Files.Song;

import java.util.ArrayList;
import java.util.List;

public class LikedContent implements Page {

    private List<Playlist> followedPlaylists;
    private String username;
    private List<Song> likedSongs;

    public LikedContent(final String username, final List<Song> likedSongs,
                        final List<Playlist> followedPlaylists) {

        this.username = username;
        this.likedSongs = likedSongs;
        this.followedPlaylists = followedPlaylists;
    }

    /**
     *String for displaying current page content
     */
    @Override
    public String displayContent() {
        ArrayList<String> message = new ArrayList<>();
        for (Playlist playlist : followedPlaylists) {
            StringBuilder playlistOutput = new StringBuilder();
            playlistOutput.append(playlist.getName()).append(" - ").
                    append(playlist.getOwner());
            message.add(playlistOutput.toString());
        }

        ArrayList<String> message1 = new ArrayList<>();
        for (Song song : likedSongs) {
            StringBuilder playlistOutput = new StringBuilder();
            playlistOutput.append(song.getName()).append(" - ").append(song.getArtist());
            message1.add(playlistOutput.toString());
        }

        return "Liked songs:\n\t" + message1 + "\n\nFollowed playlists:\n\t" + message;
    }

    /**
     *String for displaying change page message
     */
    @Override
    public String switchMessage() {
        return username + " accessed LikedContent successfully.";
    }


}
