package database.users;

import database.Announcement;
import database.audio.Podcast;
import fileio.input.CommandInput;
import pages.HostPage;
import utils.enums.UserType;

import java.util.ArrayList;

public class Host extends User {
    private HostPage officialPage;

    /* Constructor */
    public Host(String username, int age, String city) {
        super(username, age, city);
        this.setType(UserType.HOST);
        this.officialPage = new HostPage(this);
    }

    public Podcast addPodcast(CommandInput commandInput) {
        return officialPage.addPodcast(commandInput);
    }

    public void removePodcast(Podcast podcast) {
        officialPage.removePodcast(podcast);
    }

    public Podcast findPodcast(String name) {
        return officialPage.findPodcast(name);
    }

    public void addAnnouncement(CommandInput commandInput) {
        officialPage.addAnnouncement(commandInput);
    }

    public Announcement findAnnouncement(String name) {
        return officialPage.findAnnouncement(name);
    }

    public void removeAnnouncement(Announcement announcement) {
        officialPage.removeAnnouncement(announcement);
    }

    /* Getters and Setters */
    public HostPage getOfficialPage() {
        return officialPage;
    }
    public ArrayList<Podcast> getPodcasts() {
        return officialPage.getPodcasts();
    }
}
