package pages;

import database.audio.Album;
import utils.enums.PageType;

import java.util.ArrayList;

public class ArtistPage extends Page {
    private ArrayList<Album> albums;

    /* Constructor */
    public ArtistPage() {
        super();
        setType(PageType.ARTIST_PAGE);
        albums = new ArrayList<>();
    }

    /* Getters and Setters */
    public ArrayList<Album> getAlbums() {
        return albums;
    }
}
