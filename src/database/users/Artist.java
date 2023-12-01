package database.users;

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
        this.officialPage = new ArtistPage();
    }

    public Album addAlbum(CommandInput commandInput) {
        return officialPage.addAlbum(commandInput);
    }



    /* Getters and Setters */
    public ArtistPage getOfficialPage() {
        return officialPage;
    }
    public ArrayList<Album> getAlbums() {
        return officialPage.getAlbums();
    }
}
