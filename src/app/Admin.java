package app;

import app.audio.Collections.Album;
import app.audio.Collections.Playlist;
import app.audio.Collections.Podcast;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import app.user.User;
import fileio.input.EpisodeInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;
import fileio.input.UserInput;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The type Admin.
 */
public final class Admin {
    private static List<User> users = new ArrayList<>();
    private static List<Song> songs = new ArrayList<>();
    private static List<Podcast> podcasts = new ArrayList<>();

    private static List<Album> albums = new ArrayList<>();

    private static int timestamp = 0;
    private static final int LIMIT = 5;


    private Admin() {
    }

    /**
     * Sets users.
     *
     * @param userInputList the user input list
     */
    public static void setUsers(final List<UserInput> userInputList) {
        users = new ArrayList<>();
        for (UserInput userInput : userInputList) {
            users.add(new User(userInput.getUsername(), userInput.getAge(), userInput.getCity(),"normal"));
        }
    }

    /**
     * Add new user in the list
     *
     * @param userInput the user to add
     */
    public static void addUser(UserInput userInput) {
        User newUser = new User(userInput.getUsername(), userInput.getAge(), userInput.getCity(), userInput.getType());
        users.add(newUser);
    }


    /**
     * Sets songs.
     *
     * @param songInputList the song input list
     */
    public static void setSongs(final List<SongInput> songInputList) {
        songs = new ArrayList<>();
        for (SongInput songInput : songInputList) {
            songs.add(new Song(songInput.getName(), songInput.getDuration(), songInput.getAlbum(),
                    songInput.getTags(), songInput.getLyrics(), songInput.getGenre(),
                    songInput.getReleaseYear(), songInput.getArtist()));
        }
    }

    /**
     * Add new song to the lieary
     *
     * @param songInput the song to add
     */
    public static void addSong(SongInput songInput) {
        Song newSong = new Song(songInput.getName(), songInput.getDuration(), songInput.getAlbum(),
                songInput.getTags(), songInput.getLyrics(), songInput.getGenre(),
                songInput.getReleaseYear(), songInput.getArtist());
        songs.add(newSong);
    }


    /**
     * Sets podcasts.
     *
     * @param podcastInputList the podcast input list
     */
    public static void setPodcasts(final List<PodcastInput> podcastInputList) {
        podcasts = new ArrayList<>();
        for (PodcastInput podcastInput : podcastInputList) {
            List<Episode> episodes = new ArrayList<>();
            for (EpisodeInput episodeInput : podcastInput.getEpisodes()) {
                episodes.add(new Episode(episodeInput.getName(),
                                         episodeInput.getDuration(),
                                         episodeInput.getDescription()));
            }
            podcasts.add(new Podcast(podcastInput.getName(), podcastInput.getOwner(), episodes));
        }
    }

    /**
     * Removes a podcast and all its episodes.
     *
     * @param podcastName the name of the podcast to be removed
     */
    public static void removePodcast(String podcastName) {
        // Find the podcast by name
        Podcast podcastToRemove = null;
        for (Podcast podcast : podcasts) {
            if (podcast.getName().equals(podcastName)) {
                podcastToRemove = podcast;
                break;
            }
        }

        // If the podcast is found, remove it and its episodes
        if (podcastToRemove != null) {
            podcasts.remove(podcastToRemove);
        }
    }

    /**
     * Add new podcast
     *
     * @param newPodcast podcast to add
     */
    public static void addPodcast(Podcast newPodcast) {
        podcasts.add(newPodcast);
    }

    /**
     * Gets songs.
     *
     * @return the songs
     */
    public static List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    /**
     * Gets podcasts.
     *
     * @return the podcasts
     */
    public static List<Podcast> getPodcasts() {
        return new ArrayList<>(podcasts);
    }

    /**
     * Gets playlists.
     *
     * @return the playlists
     */
    public static List<Playlist> getPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        for (User user : users) {
            playlists.addAll(user.getPlaylists());
        }
        return playlists;
    }

    /**
     * Gets users
     *
     * @return the users
     */
    public static List<User> getUsers() {
        return users;
    }

    /**
     * Gets user.
     *
     * @param username the username
     * @return the user
     */
    public static User getUser(final String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Add new album to the library.
     *
     * @param album the album to add
     */
    public static void addAlbum(Album album) {
        albums.add(album);
    }

    public static List<Album> getAlbums() {
        return albums;
    }

    /**
     * Update timestamp.
     *
     * @param newTimestamp the new timestamp
     */
    public static void updateTimestamp(final int newTimestamp) {
        int elapsed = newTimestamp - timestamp;
        timestamp = newTimestamp;
        if (elapsed == 0) {
            return;
        }

        for (User user : users) {
            user.simulateTime(elapsed);
        }
    }

    /**
     * Gets top 5 songs.
     *
     * @return the top 5 songs
     */
    public static List<String> getTop5Songs() {
        List<Song> sortedSongs = new ArrayList<>(songs);
        sortedSongs.sort(Comparator.comparingInt(Song::getLikes).reversed());
        List<String> topSongs = new ArrayList<>();
        int count = 0;
        for (Song song : sortedSongs) {
            if (count >= LIMIT) {
                break;
            }
            topSongs.add(song.getName());
            count++;
        }
        return topSongs;
    }



    /**
     * Gets top 5 playlists.
     *
     * @return the top 5 playlists
     */
    public static List<String> getTop5Playlists() {
        List<Playlist> sortedPlaylists = new ArrayList<>(getPlaylists());
        sortedPlaylists.sort(Comparator.comparingInt(Playlist::getFollowers)
                .reversed()
                .thenComparing(Playlist::getTimestamp, Comparator.naturalOrder()));
        List<String> topPlaylists = new ArrayList<>();
        int count = 0;
        for (Playlist playlist : sortedPlaylists) {
            if (count >= LIMIT) {
                break;
            }
            topPlaylists.add(playlist.getName());
            count++;
        }
        return topPlaylists;
    }


    /**
     * Gets top albums.
     *
     * @return the top 5 albums
     */
    public static List<String> getTop5Albums() {
        List<Album> sortedAlbums = new ArrayList<>(getAlbums());
        for (Album album : sortedAlbums) {
            album.setLikes(album.getSongs());
        }
        sortedAlbums.sort(Comparator.comparingInt(Album::getLikes)
                .reversed()
                .thenComparing(Album::getTimestamp, Comparator.naturalOrder()));
        List<String> topAlbums = new ArrayList<>();
        int count = 0;
        for (Album album : sortedAlbums) {
            if (count >= LIMIT) {
                break;
            }
            topAlbums.add(album.getName());
            count++;
        }
        return topAlbums;
    }

    /**
     * Reset.
     */
    public static void reset() {
        users = new ArrayList<>();
        songs = new ArrayList<>();
        podcasts = new ArrayList<>();
        albums = new ArrayList<>();
        timestamp = 0;
    }

    public static List<String> onlineUsers() {

        List<User> onUsers = new ArrayList<>(getUsers());
        List<String> userNames = new ArrayList<>();
        for(User user : onUsers) {
            if (user.isConnected()) {
                String name = user.getUsername();
                userNames.add(name);
            }
        }
        return userNames;
    }
}
