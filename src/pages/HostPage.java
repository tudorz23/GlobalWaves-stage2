package pages;

import database.audio.Episode;
import database.audio.Podcast;
import database.users.User;
import fileio.input.CommandInput;
import fileio.input.EpisodeInput;
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

    /**
     * Adds a new podcast to the podcast list.
     * @param commandInput Data containing details of the new podcast.
     * @throws IllegalArgumentException if the operation fails.
     */
    public Podcast addPodcast(CommandInput commandInput) throws IllegalArgumentException {
        for (Podcast podcast : podcasts) {
            if (podcast.getName().equals(commandInput.getName())) {
                throw new IllegalArgumentException(commandInput.getUsername()
                        + " has another podcast with the same name.");
            }
        }

        for (EpisodeInput episodeInput1 : commandInput.getEpisodes()) {
            for (EpisodeInput episodeInput2 : commandInput.getEpisodes()) {
                if (episodeInput1 != episodeInput2
                    && episodeInput1.getName().equals(episodeInput2.getName())) {
                    throw new IllegalArgumentException(commandInput.getUsername()
                            + " has the same episode in this podcast.");
                }
            }
        }

        Podcast newPodcast = new Podcast(commandInput.getName(), commandInput.getUsername());

        for (EpisodeInput episodeInput : commandInput.getEpisodes()) {
            newPodcast.addEpisode(new Episode(episodeInput));
        }

        podcasts.add(newPodcast);
        return newPodcast;
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
