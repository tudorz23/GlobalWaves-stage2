package pages;

import database.audio.Album;
import database.audio.Song;
import database.users.User;
import fileio.input.CommandInput;
import fileio.input.SongInput;
import utils.enums.PageType;

import java.util.ArrayList;
import java.util.Iterator;

public class ArtistPage extends Page {
    private ArrayList<Album> albums;

    /* Constructor */
    public ArtistPage(User owningUser) {
        super(owningUser);
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

    @Override
    public String printPage() {
        StringBuilder stringBuilder = new StringBuilder("Albums:\n\t[");

        Iterator<Album> albumIterator = albums.iterator();
        while (albumIterator.hasNext()) {
            Album album = albumIterator.next();
            stringBuilder.append(album.getName());

            if (albumIterator.hasNext()) {
                stringBuilder.append(", ");
            }
        }

        // TODO: Print merch and events.
        stringBuilder.append("\n\nMerch:\n\t[");

        return stringBuilder.toString();
    }

    /* Getters and Setters */
    public ArrayList<Album> getAlbums() {
        return albums;
    }
}
