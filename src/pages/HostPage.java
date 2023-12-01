package pages;

import database.audio.Episode;
import database.audio.Podcast;
import database.users.User;
import utils.enums.PageType;
import java.util.ArrayList;
import java.util.Iterator;

public class HostPage extends Page {
    private ArrayList<Podcast> podcasts;

    /* Constructor */
    public HostPage(User owningUser) {
        super(owningUser);
        setType(PageType.HOST_PAGE);
        podcasts = new ArrayList<>();
    }

    @Override
    public String printPage() {
        StringBuilder stringBuilder = new StringBuilder("Podcasts:\n\t[");

        Iterator<Podcast> podcastIterator = podcasts.iterator();
        while (podcastIterator.hasNext()) {
            Podcast podcast = podcastIterator.next();
            stringBuilder.append(podcast.getName()).append(":\n\t[");

            Iterator<Episode> episodeIterator = podcast.getEpisodes().iterator();
            while (episodeIterator.hasNext()) {
                Episode episode = episodeIterator.next();
                stringBuilder.append(episode.getName()).append("-").append(episode.getDescription());

                if (episodeIterator.hasNext()) {
                    stringBuilder.append(", ");
                }
            }

            stringBuilder.append("]");

            if (podcastIterator.hasNext()) {
                stringBuilder.append(", [");
            }
        }

        stringBuilder.append("]\n\nAnnouncements\n\t[");
        //TODO: Print announcements.

        return stringBuilder.toString();
    }

    /* Getters and Setters */
    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }
}
