package app.pages;

import app.audio.Collections.Playlist;
import app.audio.Files.Song;
import app.user.User;

import java.util.ArrayList;
import java.util.List;

public class HomePage implements Page {
    private List<String> likedSongs;
    private List<String> followedPlaylists;

    public HomePage() {
        likedSongs = new ArrayList<>();
        followedPlaylists = new ArrayList<>();
    }

    public void setLikedSongs(ArrayList<Song> songs) {
        for (Song song : songs) {
            likedSongs.add(song.getName());
        }
    }

    public void setFollowedPlaylists(ArrayList<Playlist> playlists) {
        for (Playlist playlist : playlists) {
            followedPlaylists.add(playlist.getName());
        }
    }

    @Override
    public String displayContent() {
        return "Liked songs:\n\t" + likedSongs + "\n\nFollowed playlists:\n\t" + followedPlaylists;
    }

}
