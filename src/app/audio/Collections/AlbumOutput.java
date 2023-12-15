package app.audio.Collections;

import java.util.ArrayList;

public final class AlbumOutput {
    private final String name;
    private final ArrayList<String> songs;

    public String getName() {
        return name;
    }
    public ArrayList<String> getSongs() {
        return songs;
    }


    public AlbumOutput(final Album album) {
        this.name = album.getName();
        this.songs = new ArrayList<>();
        for (int i = 0; i < album.getSongs().size(); i++) {
            songs.add(album.getSongs().get(i).getName());
        }
    }
}
