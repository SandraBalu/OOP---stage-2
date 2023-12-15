package app.audio.Collections;
import app.audio.Files.AudioFile;
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

    /**
     * Sets total likes for album.
     *
     * @param albumSongs the song
     */
    public void setLikes(final List<Song> albumSongs) {
        for (Song song : albumSongs) {
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

    public void setTimestamp(final int timestamp) {
        this.timestamp = timestamp;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(final int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
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
