package database.users;

import database.Event;
import database.audio.Album;
import database.audio.Song;
import fileio.input.CommandInput;
import pages.ArtistPage;
import utils.enums.UserType;

import java.util.ArrayList;

public class Artist extends User {
    private ArtistPage officialPage;

    /* Constructor */
    public Artist(String username, int age, String city) {
        super(username, age, city);
        this.setType(UserType.ARTIST);
        this.officialPage = new ArtistPage(this);
    }

    public Album addAlbum(CommandInput commandInput) {
        return officialPage.addAlbum(commandInput);
    }

    public void removeAlbum(Album album) {
        officialPage.removeAlbum(album);
    }

    public Album findAlbum(String name) {
        return officialPage.findAlbum(name);
    }

    public void addEvent(CommandInput commandInput) {
        officialPage.addEvent(commandInput);
    }

    public Event findEvent(String name) {
        return officialPage.findEvent(name);
    }

    public void removeEvent(Event event) {
        officialPage.removeEvent(event);
    }

    public void addMerch(CommandInput commandInput) {
        officialPage.addMerch(commandInput);
    }

    /* Getters and Setters */
    public ArtistPage getOfficialPage() {
        return officialPage;
    }
    public ArrayList<Album> getAlbums() {
        return officialPage.getAlbums();
    }
}
