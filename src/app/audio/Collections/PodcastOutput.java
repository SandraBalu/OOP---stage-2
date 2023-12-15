package app.audio.Collections;
import java.util.ArrayList;

public final class PodcastOutput {
    private final String name;
    private final ArrayList<String> episodes;

    public String getName() {
        return name;
    }
    public ArrayList<String> getEpisodes() {
        return
                episodes;
    }


    public PodcastOutput(final  Podcast podcast) {
        this.name = podcast.getName();
        this.episodes = new ArrayList<>();
        for (int i = 0; i < podcast.getEpisodes().size(); i++) {
            episodes.add(podcast.getEpisodes().get(i).getName());
        }
    }
}
