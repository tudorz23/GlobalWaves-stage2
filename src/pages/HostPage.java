package pages;

import database.audio.Podcast;
import utils.enums.PageType;
import java.util.ArrayList;

public class HostPage extends Page {
    private ArrayList<Podcast> podcasts;

    /* Constructor */
    public HostPage() {
        super();
        setType(PageType.HOST_PAGE);
        podcasts = new ArrayList<>();
    }

    /* Getters and Setters */
    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }
}
