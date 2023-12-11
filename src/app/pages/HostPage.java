package app.pages;

import java.util.ArrayList;
import java.util.List;

public class HostPage implements Page {
//    private List<String> podcasts;
//    private List<String> announcements;
//
//    private HostPage(String type) {
//        super(type);
//        podcasts = new ArrayList<>();
//        announcements = new ArrayList<>();
//    }
//
//    public static HostPage getInstance() {
//        return (HostPage) Page.getInstance("Host");
//    }

    @Override
    public String displayContent() {
//        return "\nPodcasts:\n\t" + podcasts + "\nAnnouncements:\n\t" + announcements;
        return "LIked";
    }

}

