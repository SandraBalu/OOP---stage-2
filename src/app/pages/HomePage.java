package app.pages;

import app.audio.Collections.Playlist;
import app.audio.Files.Song;
import app.user.User;

import java.util.ArrayList;
import java.util.List;

public class HomePage implements Page {
    private String username;
    private List<String> likedSongs;
    private List<String> followedPlaylists;

    public HomePage(String username, List<Song> likedSongs, List<Playlist> followedPlaylists) {
        this.username = username;
        this.likedSongs = likedSongs.stream().map(Song::getName).toList();
        this.followedPlaylists = followedPlaylists.stream().map(Playlist::getName).toList();
    }

    public HomePage() {
        likedSongs = new ArrayList<>();
        followedPlaylists = new ArrayList<>();
    }

    @Override
    public String displayContent() {
        return "Liked songs:\n\t" + likedSongs + "\n\nFollowed playlists:\n\t" + followedPlaylists;
    }
    @Override
    public String switchMessage() {
        return "Home page";
    }

}
