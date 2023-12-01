package database;

import database.audio.Album;
import database.audio.Playlist;
import database.audio.Podcast;
import database.audio.Song;
import database.users.User;

import java.util.ArrayList;

public final class Database {
    private ArrayList<User> users;
    private ArrayList<Song> songs;
    private ArrayList<Podcast> podcasts;
    private ArrayList<Playlist> playlists;
    private ArrayList<Album> albums;

    /* Constructor */
    public Database() {
        this.users = new ArrayList<>();
        this.songs = new ArrayList<>();
        this.podcasts = new ArrayList<>();
        this.playlists = new ArrayList<>();
        this.albums = new ArrayList<>();
    }

    /**
     * Adds a new song to the songs list.
     */
    public void addSong(final Song song) {
        songs.add(song);
    }

    /**
     * Adds a new podcast to the podcasts list.
     */
    public void addPodcast(final Podcast podcast) {
        podcasts.add(podcast);
    }

    /**
     * Adds a new user to the users list.
     */
    public void addUser(final User user) {
        users.add(user);
    }

    /**
     * Adds a new playlist to the playlists list.
     */
    public void addPlaylist(final Playlist playlist) {
        playlists.add(playlist);
    }

    /**
     * Adds a new album to the albums list.
     */
    public void addAlbum(Album album) {
        albums.add(album);
    }

    /**
     * Traverses the song database and returns the instance of the
     * requested song, if it exists.
     * @return Song instance for success, null otherwise.
     * @throws IllegalArgumentException if the song is not found.
     */
    public Song searchSongInDatabase(final Song reqSong) throws IllegalArgumentException {
        for (Song song : songs) {
            if (song.getName().equals(reqSong.getName())
                && song.getArtist().equals(reqSong.getArtist())) {
                return song;
            }
        }
        throw new IllegalArgumentException("Critical: Song not found in the database.");
    }

    /* Getters and Setters */
    public ArrayList<Song> getSongs() {
        return songs;
    }
    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }
    public ArrayList<User> getUsers() {
        return users;
    }
    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }
    public ArrayList<Album> getAlbums() {
        return albums;
    }
}
