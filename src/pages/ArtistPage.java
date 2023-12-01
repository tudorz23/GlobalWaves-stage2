package pages;

import database.audio.Album;
import database.audio.Song;
import fileio.input.CommandInput;
import fileio.input.SongInput;
import utils.enums.PageType;

import java.util.ArrayList;
import java.util.Iterator;

public class ArtistPage extends Page {
    private ArrayList<Album> albums;

    /* Constructor */
    public ArtistPage() {
        super();
        setType(PageType.ARTIST_PAGE);
        albums = new ArrayList<>();
    }

    /**
     * Adds a new album to the album list.
     * @param commandInput Data containing details of the new album.
     * @throws IllegalArgumentException if the operation fails.
     */
    public Album addAlbum(CommandInput commandInput) throws IllegalArgumentException {
        for (Album album : albums) {
            if (album.getName().equals(commandInput.getName())) {
                throw new IllegalArgumentException(commandInput.getUsername()
                        + " has another album with the same name.");
            }
        }

        for (SongInput songInput1 : commandInput.getSongs()) {
            for (SongInput songInput2 : commandInput.getSongs()) {
                if (songInput1 != songInput2
                        && songInput1.getName().equals(songInput2.getName())) {
                    throw new IllegalArgumentException(commandInput.getUsername()
                            + " has the same song at least twice in this album.");
                }
            }
        }

        Album newAlbum = new Album(commandInput.getName(), commandInput.getReleaseYear(),
                                    commandInput.getDescription());

        for (SongInput songInput : commandInput.getSongs()) {
            newAlbum.addSong(new Song(songInput));
        }

        albums.add(newAlbum);
        return newAlbum;
    }

    /* Getters and Setters */
    public ArrayList<Album> getAlbums() {
        return albums;
    }
}
