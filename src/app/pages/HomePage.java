package app.pages;

import app.audio.Collections.Playlist;
import app.audio.Files.Song;
import app.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class HomePage implements Page {
    private static final int LIMIT =  5;
    private String username;
    private List<Song> likedSongs;
    private List<Playlist> followedPlaylists;

    public HomePage(String username, List<Song> likedSongs, List<Playlist> followedPlaylists) {
        this.username = username;
        this.likedSongs = likedSongs;
        this.followedPlaylists = followedPlaylists;
    }

    public HomePage() {
        likedSongs = new ArrayList<>();
        followedPlaylists = new ArrayList<>();
    }

    @Override
    public String displayContent() {
        List<Song> sortedSongs = new ArrayList<>(likedSongs);
        sortedSongs.sort(Comparator.comparingInt(Song::getLikes).reversed());
        List<String> songs = new ArrayList<>();
        for (Song song : sortedSongs) {
            songs.add(song.getName());
        }

        List<Playlist> sortedPlaylists = new ArrayList<>(followedPlaylists);
        sortedPlaylists.sort(Comparator.comparingInt(Playlist::getFollowers)
                .reversed()
                .thenComparing(Playlist::getTimestamp, Comparator.naturalOrder()));
        List<String> topSongs = new ArrayList<>();
        int count = 0;
        for (Song song : sortedSongs) {
            if (count >= LIMIT) {
                break;
            }
            topSongs.add(song.getName());
            count++;
        }

        List<String> playlists = new ArrayList<>();
        for (Playlist playlist : sortedPlaylists) {
            playlists.add(playlist.getName());
        }
        List<String> topPlaylists = new ArrayList<>();
        count = 0;
        for (Playlist playlist : sortedPlaylists) {
            if (count >= LIMIT) {
                break;
            }
            topPlaylists.add(playlist.getName());
            count++;
        }
        return "Liked songs:\n\t" + topSongs + "\n\nFollowed playlists:\n\t" +  topPlaylists;
    }
    @Override
    public String switchMessage() {
        return username + " accessed Home successfully.";
    }

}
