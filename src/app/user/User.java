package app.user;

import app.Admin;
import app.CommandRunner;
import app.audio.Collections.*;
import app.audio.Files.*;
import app.audio.LibraryEntry;
import app.pages.HomePage;
import app.pages.HostPage;
import app.pages.Page;
import app.pages.PageFactory;
import app.player.Player;
import app.player.PlayerStats;
import app.searchBar.Filters;
import app.searchBar.SearchBar;
import app.utils.Enums;
import fileio.input.CommandInput;
import fileio.input.EpisodeInput;
import fileio.input.SongInput;
import lombok.Getter;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * The type User.
 */
public class User extends LibraryEntry{
    @Getter
    private String username;
    @Getter
    private int age;
    @Getter
    private String city;
    @Getter
    private ArrayList<Playlist> playlists;
    @Getter
    private ArrayList<Song> likedSongs;
    @Getter
    private ArrayList<Playlist> followedPlaylists;
    private ArrayList<Album> albums;
    private ArrayList<Podcast> podcasts;
    private final Player player;
    private final SearchBar searchBar;
    private String type;
    private ArrayList<Merch> merches;
    private ArrayList<Event> events;
    private ArrayList<Announcement> announcements;
    private boolean lastSearched;
    private boolean isConnected;
    private Page currentPage;
    private String pageType;
    private  int likes;

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public ArrayList<Song> getLikedSongs() {
        return likedSongs;
    }

    public void setLikedSongs(ArrayList<Song> likedSongs) {
        this.likedSongs = likedSongs;
    }

    public ArrayList<Playlist> getFollowedPlaylists() {
        return followedPlaylists;
    }

    public void setFollowedPlaylists(ArrayList<Playlist> followedPlaylists) {
        this.followedPlaylists = followedPlaylists;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public ArrayList<Merch> getMerches() {
        return merches;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public Player getPlayer() {
        return player;
    }

    public SearchBar getSearchBar() {
        return searchBar;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isLastSearched() {
        return lastSearched;
    }

    public void setLastSearched(boolean lastSearched) {
        this.lastSearched = lastSearched;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }


    public ArrayList<Announcement> getAnnouncements() {
        return announcements;
    }

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param age      the age
     * @param city     the city
     */
    public User(final String username, final int age, final String city, final String type) {
        super(username);
        this.username = username;
        this.age = age;
        this.city = city;
        likes = 0;
        playlists = new ArrayList<>();
        likedSongs = new ArrayList<>();
        albums = new ArrayList<>();
        podcasts = new ArrayList<>();
        followedPlaylists = new ArrayList<>();
        player = new Player();
        searchBar = new SearchBar(username);
        merches = new  ArrayList<>();
        events  = new  ArrayList<>();
        announcements = new ArrayList<>();
        lastSearched = false;
        this.isConnected = true;
        this.type = type;
    }



    /**
     * Search array list.
     *
     * @param filters the filters
     * @param type    the type
     * @return the array list
     */
    public ArrayList<String> search(final Filters filters, final String type) {
        searchBar.clearSelection();
        player.stop();

        lastSearched = true;
        ArrayList<String> results = new ArrayList<>();
        List<LibraryEntry> libraryEntries = searchBar.search(filters, type);

        for (LibraryEntry libraryEntry : libraryEntries) {
            results.add(libraryEntry.getName());
        }
        return results;
    }

    /**
     * Select string.
     *
     * @param itemNumber the item number
     * @return the string
     */
    public String select(final int itemNumber) {
        if (!lastSearched) {
            return "Please conduct a search before making a selection.";
        }

        lastSearched = false;

        LibraryEntry selected = searchBar.select(itemNumber);

        if (selected == null) {
            return "The selected ID is too high.";
        }

        if (searchBar.getLastSearchType().equals("artist") || searchBar.getLastSearchType().equals("host")) {
            return "Successfully selected %s's page.".formatted(selected.getName());
        }

        return "Successfully selected %s.".formatted(selected.getName());
    }

    /**
     * Load string.
     *
     * @return the string
     */
    public String load() {
        if (searchBar.getLastSelected() == null) {
            return "Please select a source before attempting to load.";
        }

        if (!searchBar.getLastSearchType().equals("song")
            && ((AudioCollection) searchBar.getLastSelected()).getNumberOfTracks() == 0) {
            return "You can't load an empty audio collection!";
        }

        player.setSource(searchBar.getLastSelected(), searchBar.getLastSearchType());

        player.setCollectionName(searchBar.getLastSelected().getName());

        searchBar.clearSelection();

        player.pause();


        return "Playback loaded successfully.";
    }

    /**
     * Play pause string.
     *
     * @return the string
     */
    public String playPause() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before attempting to pause or resume playback.";
        }

        player.pause();

        if (player.getPaused()) {
            return "Playback paused successfully.";
        } else {
            return "Playback resumed successfully.";
        }
    }

    /**
     * Repeat string.
     *
     * @return the string
     */
    public String repeat() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before setting the repeat status.";
        }

        Enums.RepeatMode repeatMode = player.repeat();
        String repeatStatus = "";

        switch (repeatMode) {
            case NO_REPEAT -> {
                repeatStatus = "no repeat";
            }
            case REPEAT_ONCE -> {
                repeatStatus = "repeat once";
            }
            case REPEAT_ALL -> {
                repeatStatus = "repeat all";
            }
            case REPEAT_INFINITE -> {
                repeatStatus = "repeat infinite";
            }
            case REPEAT_CURRENT_SONG -> {
                repeatStatus = "repeat current song";
            }
            default -> {
                repeatStatus = "";
            }
        }

        return "Repeat mode changed to %s.".formatted(repeatStatus);
    }

    /**
     * Shuffle string.
     *
     * @param seed the seed
     * @return the string
     */
    public String shuffle(final Integer seed) {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before using the shuffle function.";
        }

        if (!player.getType().equals("playlist") && !player.getType().equals("album")) {
            return "The loaded source is not a playlist or an album.";
        }

        player.shuffle(seed);

        if (player.getShuffle()) {
            return "Shuffle function activated successfully.";
        }
        return "Shuffle function deactivated successfully.";
    }

    /**
     * Forward string.
     *
     * @return the string
     */
    public String forward() {
        if (player.getSource() == null ) {
            return "Please load a source before attempting to forward.";
        }

        if (!player.getType().equals("podcast")) {
            return "The loaded source is not a podcast.";
        }

        player.skipNext();


        return "Skipped forward successfully.";
    }

    /**
     * Backward string.
     *
     * @return the string
     */
    public String backward() {
        if (player.getCurrentAudioFile() == null) {
            return "Please select a source before rewinding.";
        }

        if (!player.getType().equals("podcast")) {
            return "The loaded source is not a podcast.";
        }

        player.skipPrev();

        return "Rewound successfully.";
    }

    /**
     * Like string.
     *
     * @return the string
     */
    public String like() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before liking or unliking.";
        }

        if (player.getType().equals("podcast")) {
            return "Loaded source is not a song.";
        }

        if (!isConnected) {
            return username + " is offline.";
        }

        Song song = (Song) player.getCurrentAudioFile();

        if (likedSongs.contains(song)) {
            likedSongs.remove(song);
            song.dislike();

            return "Unlike registered successfully.";
        }

        likedSongs.add(song);
        song.like();
        return "Like registered successfully.";
    }

    /**
     * Next string.
     *
     * @return the string
     */
    public String next() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before skipping to the next track.";
        }

        player.next();

        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before skipping to the next track.";
        }

        return "Skipped to next track successfully. The current track is %s."
                .formatted(player.getCurrentAudioFile().getName());
    }

    /**
     * Prev string.
     *
     * @return the string
     */
    public String prev() {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before returning to the previous track.";
        }

        player.prev();

        return "Returned to previous track successfully. The current track is %s."
                .formatted(player.getCurrentAudioFile().getName());
    }

    /**
     * Create playlist string.
     *
     * @param name      the name
     * @param timestamp the timestamp
     * @return the string
     */
    public String createPlaylist(final String name, final int timestamp) {
        if (playlists.stream().anyMatch(playlist -> playlist.getName().equals(name))) {
            return "A playlist with the same name already exists.";
        }

        playlists.add(new Playlist(name, username, timestamp));

        return "Playlist created successfully.";
    }

    /**
     * Add remove in playlist string.
     *
     * @param id the id
     * @return the string
     */
    public String addRemoveInPlaylist(final int id) {
        if (player.getCurrentAudioFile() == null) {
            return "Please load a source before adding to or removing from the playlist.";
        }

        if (player.getType().equals("podcast")) {
            return "The loaded source is not a song.";
        }

        if (id > playlists.size()) {
            return "The specified playlist does not exist.";
        }

        Playlist playlist = playlists.get(id - 1);

        if (playlist.containsSong((Song) player.getCurrentAudioFile())) {
            playlist.removeSong((Song) player.getCurrentAudioFile());
            return "Successfully removed from playlist.";
        }

        playlist.addSong((Song) player.getCurrentAudioFile());
        return "Successfully added to playlist.";
    }

    /**
     * Switch playlist visibility string.
     *
     * @param playlistId the playlist id
     * @return the string
     */
    public String switchPlaylistVisibility(final Integer playlistId) {
        if (playlistId > playlists.size()) {
            return "The specified playlist ID is too high.";
        }

        Playlist playlist = playlists.get(playlistId - 1);
        playlist.switchVisibility();

        if (playlist.getVisibility() == Enums.Visibility.PUBLIC) {
            return "Visibility status updated successfully to public.";
        }

        return "Visibility status updated successfully to private.";
    }

    /**
     * Show playlists array list.
     *
     * @return the array list
     */
    public ArrayList<PlaylistOutput> showPlaylists() {
        ArrayList<PlaylistOutput> playlistOutputs = new ArrayList<>();
        for (Playlist playlist : playlists) {
            playlistOutputs.add(new PlaylistOutput(playlist));
        }

        return playlistOutputs;
    }

    /**
     * Show albums array list.
     *
     * @return the array list
     */
    public ArrayList<AlbumOutput> showAlbums() {
        ArrayList<AlbumOutput> albumOutputs = new ArrayList<>();
        for (Album album : albums) {
            albumOutputs.add(new AlbumOutput(album));
        }

        return albumOutputs;
    }

    public ArrayList<PodcastOutput> showPodcasts() {
        ArrayList<PodcastOutput> podcastOutputs = new ArrayList<>();
        for (Podcast podcast: podcasts) {
            podcastOutputs.add(new PodcastOutput(podcast));
        }
        return podcastOutputs;
    }

    /**
     * Follow string.
     *
     * @return the string
     */
    public String follow() {
        LibraryEntry selection = searchBar.getLastSelected();
        String type = searchBar.getLastSearchType();

        if (selection == null) {
            return "Please select a source before following or unfollowing.";
        }

        if (!type.equals("playlist")) {
            return "The selected source is not a playlist.";
        }

        Playlist playlist = (Playlist) selection;

        if (playlist.getOwner().equals(username)) {
            return "You cannot follow or unfollow your own playlist.";
        }

        if (followedPlaylists.contains(playlist)) {
            followedPlaylists.remove(playlist);
            playlist.decreaseFollowers();

            return "Playlist unfollowed successfully.";
        }

        followedPlaylists.add(playlist);
        playlist.increaseFollowers();


        return "Playlist followed successfully.";
    }

    /**
     * Gets player stats.
     *
     * @return the player stats
     */
    public PlayerStats getPlayerStats() {
        return player.getStats();
    }

    /**
     * Show preferred songs array list.
     *
     * @return the array list
     */
    public ArrayList<String> showPreferredSongs() {
        ArrayList<String> results = new ArrayList<>();
        for (AudioFile audioFile : likedSongs) {
            results.add(audioFile.getName());
        }

        return results;
    }

    /**
     * Gets preferred genre.
     *
     * @return the preferred genre
     */
    public String getPreferredGenre() {
        String[] genres = {"pop", "rock", "rap"};
        int[] counts = new int[genres.length];
        int mostLikedIndex = -1;
        int mostLikedCount = 0;

        for (Song song : likedSongs) {
            for (int i = 0; i < genres.length; i++) {
                if (song.getGenre().equals(genres[i])) {
                    counts[i]++;
                    if (counts[i] > mostLikedCount) {
                        mostLikedCount = counts[i];
                        mostLikedIndex = i;
                    }
                    break;
                }
            }
        }

        String preferredGenre = mostLikedIndex != -1 ? genres[mostLikedIndex] : "unknown";
        return "This user's preferred genre is %s.".formatted(preferredGenre);
    }

    /**
     * Simulate time.
     *
     * @param time the time
     */
    public void simulateTime(final int time) {
        player.simulatePlayer(time);
    }


    /**
     * switch connection for the current user
     *
     * @param user the user whose connection is changed
     * @return the string
     */
    public String switchConnection(User user ,CommandInput command) {

        if (user.getType().equals("artist") || user.getType().equals("host")) {
            return user.username + " is not a normal user.";

        }
        user.setConnected(!user.isConnected());
        if (user.isConnected) {
            Admin.setTimestamp(command.getTimestamp());
        }

        return user.getUsername() + " has changed status successfully.";

    }

    public String addAlbum(final String name, final ArrayList<SongInput> songs) {
        if (albums.stream().anyMatch(album -> album.getName().equals(name))) {
            return username + " has another album with the same name.";
        }
        for (int i = 0; i < songs.size() - 1; i++) {
            for (int j = i + 1; j < songs.size(); j++) {
                if (songs.get(i).getName().equals(songs.get(j).getName())) {
                    return username + " has the same song at least twice in this album.";
                }
            }
        }

        Album newAlbum = new Album(name, username);
        newAlbum.setSongs(songs);
        albums.add(newAlbum);
        Admin.addAlbum(newAlbum);

        for (SongInput song : songs) {
            Admin.addSong(song);
        }

        return username + " has added new album successfully.";
    }

    public String removeAlbum(CommandInput commandInput) {
        if (!type.equals("artist")) {
            return username + " is not a artist";
        }
        List<String> albums1 = albums.stream().map(Album::getName).toList();
        if (!albums1.contains(commandInput.getName())) {
            //no album with the given name
            return username + " doesn't have an album with the given name.";
        } else {
            int index = albums1.indexOf(commandInput.getName());
            Album album = albums.get(index);
            for (User user : Admin.getUsers()) {
                if (user.getPlayer() != null && user.getPlayer().getType() != null &&
                        (user.getPlayer().getType().equals("album") || user.getPlayer().getType().equals("album"))) {

                    if (user.getPlayer().getCurrentAudioFile() != null) {
                        Song song = (Song) user.getPlayer().getCurrentAudioFile();
                        if (song.getAlbum().equals(commandInput.getName())) {
                            return commandInput.getUsername() + " can't delete this album.";
                        }
                    }
                } else if (user.getPlayer() != null && user.getPlayer().getType() != null && user.getPlayer().getType().equals("playlist")) {
                    String playlistName = user.getPlayer().getCollectionName();
                    int i = 0;
                    for (Playlist playlist : Admin.getPlaylists()) {
                        if (playlist.getName().equals(playlistName)) {
                            break;
                        }
                        i++;
                    }
                    Playlist currentPlaylist = Admin.getPlaylists().get(i);
                    for (Song song : currentPlaylist.getSongs()) {
                        System.out.println(song.getName() + " - " + song.getAlbum());
                        if (song.getAlbum().equals(commandInput.getName())) {
                            return commandInput.getUsername() + " can't delete this album.";
                        }
                    }
                }
            }
        }
            return  "delete";

        }

    public String addPodcast(final String name, final ArrayList<Episode> episodes) {
        if (podcasts.stream().anyMatch(podcast -> podcast.getName().equals(name))) {
            return username + " has another podcast with the same name.";
        }
        Podcast newPodcast = new Podcast(name, username, episodes);
        if (podcasts != null) {
            podcasts.add(newPodcast);
        }
        Admin.addPodcast(newPodcast);
        return username + " has added new podcast successfully.";
    }

    public String removePodcast(final String name) {

        for (Podcast podcast : podcasts) {
            if (podcast.getName().equals(name)) {
                   for (User user : Admin.getUsers()) {
                       if (user.getPlayer() != null && user.getPlayer().getType() != null) {
                           if (user.getPlayer().getType().equals("podcast") && user.getPlayer().getSource() != null) {
                               if (podcast.getEpisodes().contains(user.getPlayer().getSource().getAudioFile())) {
                                   return username + " can't delete this podcast.";
                               }
                           }
                       }
                   }
                    podcasts.remove(podcast);
                    Admin.removePodcast(name);
                    return username + " deleted the podcast successfully.";

            }
        }
        return username + " doesn't have a podcast with the given name.";
    }


    public String addMerch(CommandInput commandInput) {

        if (commandInput.getPrice() < 0) {
            return "Price for merchandise can not be negative.";
        }
        List<User> users = Admin.getUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (!type.equals("artist")) {
                    return username + " is not an artist";
                }
                for (Merch merch : merches) {
                    if (merch.getName().equals(commandInput.getName())) {
                        return username + " has merchandise with the same name.";
                    }
                }
                Merch newMerch = new Merch(commandInput.getName(),
                        commandInput.getDescription(), commandInput.getPrice());

                merches.add(newMerch);
                return username + " has added new merchandise successfully.";
            }
        }
        return "The username " + username + " doesn't exist.";
    }


    public String addEvent(CommandInput commandInput) {

        List<User> users = Admin.getUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (!type.equals("artist")) {
                    return username + " is not an artist.";
                }
                for (Event event : events) {
                    if (event.getName().equals(commandInput.getName())) {
                        return username + " has another event with the same name.";
                    }
                }

                String date = commandInput.getDate();
                String[] componente = date.split("-");
                int day = Integer.parseInt(componente[0]);
                int month = Integer.parseInt(componente[1]);
                int year = Integer.parseInt(componente[2]);

                if (year < 1900 || year > 2023) {
                    return "Event for " + username +" does not have a valid date.";
                }
                if (month > 12) {
                    return "Event for " + username +" does not have a valid date.";
                }
                if (day > 31 || (month == 2 && day > 28)) {
                    return "Event for " + username +" does not have a valid date.";
                }

                Event newEvent = new Event(commandInput.getName(),
                        commandInput.getDescription(), commandInput.getDate());

                events.add(newEvent);
                return username + " has added new event successfully.";
            }
        }
        return "The username " + username + " doesn't exist.";
    }

    public String removeEvent(CommandInput commandInput) {
        if (!type.equals("artist")) {
            return username + " is not a artist.";
        } else {
            String message = new String();
            int index = 0;
            for (Event event : events) {
                if (event.getName().equals(commandInput.getName())) {
                    message = username + " deleted the event successfully.";
                    break;
                }
                index++;
            }

            if (index < events.size() && message != null) {
                Event eventRemove = events.get(index);
                events.remove(eventRemove);
                return message;
            }
            return username + " has no announcement with the given name.";
        }
    }
    public String addAnnouncement(CommandInput commandInput) {

        List<User> users = Admin.getUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (!type.equals("host")) {
                    return username + " is not a host.";
                }
                for (Announcement announcement : announcements) {
                    if (announcement.getName().equals(commandInput.getName())) {
                        return username + " has already added an announcement with this name.";
                    }
                }
                Announcement newAnnouncement = new Announcement(commandInput.getName(), commandInput.getDescription());

                announcements.add(newAnnouncement);
                return username + " has successfully added new announcement.";
            }
        }
        return "The username " + username + " doesn't exist.";
    }

    public String removeAnnouncement(CommandInput commandInput) {
        if (!type.equals("host")) {
            return username + " is not a host.";
        } else {
            String message = new String();
            int index = 0;
            for (Announcement announcement : announcements) {
                if (announcement.getName().equals(commandInput.getName())) {
                    message = username + " has successfully deleted the announcement.";
                    break;
                }
                index++;
            }
            if (index < announcements.size() && message != null) {
                Announcement announcementRemove = announcements.get(index);
                announcements.remove(announcementRemove);
                return message;
            }
            return username + " has no announcement with the given name.";
        }
    }
}
