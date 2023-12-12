package app.pages;

import app.audio.Collections.Playlist;
import app.audio.Files.Song;

import java.util.ArrayList;
import java.util.List;

public class LikedContentPage implements Page {

    private List<Playlist> followedPlaylists;
    private String username;
    private List<String> likedSongs;

    public LikedContentPage(String username, List<Song> likedSongs, List<Playlist> followedPlaylists) {
        this.username = username;
        this.likedSongs = likedSongs.stream().map(Song::getName).toList();
        this.followedPlaylists = followedPlaylists;
    }

    @Override
    public String displayContent() {
        ArrayList<String> message = new ArrayList<>();
        for (Playlist playlist : followedPlaylists) {
            StringBuilder playlistOutput = new StringBuilder();
            playlistOutput.append(playlist.getName()).append(" - ").append(playlist.getOwner());
            message.add(playlistOutput.toString());
        }

        return "Liked songs:\n\t" + likedSongs + "\n\nFollowed playlists:\n\t" + message;
    }

    @Override
    public String switchMessage() {
        return username + " accessed LikedContent successfully.";
    }


}
