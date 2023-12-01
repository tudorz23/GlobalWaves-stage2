package pages;

import database.Event;
import database.audio.Album;
import database.audio.Song;
import database.users.Merch;
import database.users.User;
import fileio.input.CommandInput;
import fileio.input.SongInput;
import utils.enums.PageType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;

public class ArtistPage extends Page {
    private ArrayList<Album> albums;
    private ArrayList<Event> events;
    private ArrayList<Merch> merchList;

    /* Constructor */
    public ArtistPage(User owningUser) {
        super(owningUser);
        setType(PageType.ARTIST_PAGE);
        albums = new ArrayList<>();
        events = new ArrayList<>();
        merchList = new ArrayList<>();
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
        stringBuilder.append("]\n\nMerch:\n\t[");

        Iterator<Merch> merchIterator = merchList.iterator();
        while (merchIterator.hasNext()) {
            Merch merch = merchIterator.next();
            stringBuilder.append(merch.getName()).append(" - ").append(merch.getPrice())
                    .append(":\n\t").append(merch.getDescription());

            if (merchIterator.hasNext()) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("]\n\nEvents:\n\t[");

        Iterator<Event> eventIterator = events.iterator();
        while (eventIterator.hasNext()) {
            Event event = eventIterator.next();
            stringBuilder.append(event.getName()).append(" - ").append(event.getDate())
                    .append(":\n\t").append(event.getDescription());

            if (eventIterator.hasNext()) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    /**
     * Adds a new event to the event list.
     * @param commandInput Data containing details of the new event.
     * @throws IllegalArgumentException if the operation fails.
     */
    public void addEvent(CommandInput commandInput) throws IllegalArgumentException {
        for (Event event : events) {
            if (event.getName().equals(commandInput.getName())) {
                throw new IllegalArgumentException(commandInput.getUsername()
                        + " has another event with the same name.");
            }
        }

        LocalDate date;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy");
            date = LocalDate.parse(commandInput.getDate(), formatter);
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException("Event for " + commandInput.getUsername()
                                                + " does not have a valid date.");
        }

        if (date.getYear() < 1900 || date.getYear() > 2023) {
            throw new IllegalArgumentException("Event for " + commandInput.getUsername()
                                                + " does not have a valid date.");
        }

        Event newEvent = new Event(commandInput.getName(), commandInput.getDescription(),
                                    commandInput.getDate());
        events.add(newEvent);
    }

    /**
     * Adds a new merch to the merch list.
     * @param commandInput Data containing details of the new merch.
     * @throws IllegalArgumentException if the operation fails.
     */
    public void addMerch(CommandInput commandInput) throws IllegalArgumentException {
        for (Merch merch : merchList) {
            if (merch.getName().equals(commandInput.getName())) {
                throw new IllegalArgumentException(commandInput.getUsername()
                        + " has merchandise with the same name.");
            }
        }

        if (commandInput.getPrice() < 0) {
            throw new IllegalArgumentException("Price for merchandise can not be negative.");
        }

        Merch newMerch = new Merch(commandInput.getName(), commandInput.getDescription(),
                                    commandInput.getPrice());
        merchList.add(newMerch);
    }

    /* Getters */
    public ArrayList<Album> getAlbums() {
        return albums;
    }
    public ArrayList<Event> getEvents() {
        return events;
    }
}
