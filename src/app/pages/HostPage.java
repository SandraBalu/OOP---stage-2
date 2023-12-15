package app.pages;


import app.audio.Collections.Podcast;
import app.audio.Files.Announcement;
import app.audio.Files.Episode;
import java.util.ArrayList;
import java.util.List;

public class HostPage implements Page {

    private String username;
    private List<Podcast> podcasts;
    private List<Announcement> announcements = new ArrayList<>();

    public HostPage(final String username, final List<Podcast> podcasts,
                    final List<Announcement> announcements) {
        this.username = username;
        this.podcasts = podcasts;
        this.announcements = announcements;

    }

    /**
     *String for displaying current page content
     */
    @Override
    public String displayContent() {
        ArrayList<String> message1 = new ArrayList<>();
        for (Podcast podcast: podcasts) {
            StringBuilder podcastOutput = new StringBuilder();
            List<Episode> episodes = podcast.getEpisodes();
            podcastOutput.append(podcast.getName()).append(":\n\t");

            List<String> episodesOutput = new ArrayList<>();
            for (Episode episode : episodes) {
                String ep = episode.getName() + " - " + episode.getDescription();
                episodesOutput.add(ep);
            }
            podcastOutput.append(episodesOutput);
            podcastOutput.append("\n");
            message1.add(podcastOutput.toString());
        }
        ArrayList<String> message2 = new ArrayList<>();
        for (Announcement announcement : announcements) {
            StringBuilder podcastOutput = new StringBuilder();
            podcastOutput.append(announcement.getName()).append(":\n\t").
                    append(announcement.getDescription()).append("\n");
            message2.add(podcastOutput.toString());
            }


        return "Podcasts:\n\t" + message1 + "\n\nAnnouncements:\n\t" + message2;
    }

    /**
     *String for displaying change page message
     */
    @Override
    public String switchMessage() {
        return "Home page";
    }


}

