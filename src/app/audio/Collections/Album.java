package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import fileio.input.SongInput;

import java.util.ArrayList;
import java.util.List;

public final class Album extends AudioCollection {

    private final ArrayList<Song> songs = new ArrayList<>();
    private int timestamp;
    private int releaseYear;
    private String description;

    private int likes;

    public Album(final String name, final String owner) {
        super(name, owner);
    }


    /**
     * Sets songs.
     *
     * @param songInputList the song input list
     */
    public void setSongs(final List<SongInput> songInputList) {
        for (SongInput songInput : songInputList) {
            songs.add(new Song(songInput.getName(), songInput.getDuration(), songInput.getAlbum(),
                    songInput.getTags(), songInput.getLyrics(), songInput.getGenre(),
                    songInput.getReleaseYear(), songInput.getArtist()));
        }
    }

    public void setLikes(final List<Song> songs) {
        for (Song song : songs) {
            likes += song.getLikes();
        }
    }

    public int getLikes() {
        return likes;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getNumberOfTracks() {
        return songs.size();
    }

    @Override
    public AudioFile getTrackByIndex(final int index) {
        return songs.get(index);
    }
}
