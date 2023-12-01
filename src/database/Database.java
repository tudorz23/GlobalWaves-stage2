package database;

import database.audio.Album;
import database.audio.Playlist;
import database.audio.Podcast;
import database.audio.Song;
import database.users.Artist;
import database.users.BasicUser;
import database.users.Host;
import database.users.User;

import java.util.ArrayList;

public final class Database {
    private ArrayList<BasicUser> basicUsers;
    private ArrayList<Artist> artists;
    private ArrayList<Host> hosts;
    private ArrayList<Song> songs;
    private ArrayList<Podcast> podcasts;
    private ArrayList<Playlist> playlists;
    private ArrayList<Album> albums;

    /* Constructor */
    public Database() {
        this.basicUsers = new ArrayList<>();
        this.artists = new ArrayList<>();
        this.hosts = new ArrayList<>();
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
     * Adds a new basic user to the basicUsers list.
     */
    public void addBasicUser(final BasicUser user) {
        basicUsers.add(user);
    }

    /**
     * Adds a new artist to the artists list.
     */
    public void addArtist(final Artist artist) {
        artists.add(artist);
    }

    /**
     * Adds a new host to the hosts list.
     */
    public void addHost(final Host host) {
        hosts.add(host);
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

    /**
     * Traverses the 3 user lists and searches for the username.
     * @return true if the username exists in the database, false otherwise.
     */
    public boolean checkExistingUsername(String username) {
        for (User user : basicUsers) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }

        for (User user : artists) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }

        for (User user : hosts) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /* Getters and Setters */
    public ArrayList<Song> getSongs() {
        return songs;
    }
    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }
    public ArrayList<BasicUser> getBasicUsers() {
        return basicUsers;
    }
    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }
    public ArrayList<Album> getAlbums() {
        return albums;
    }
    public ArrayList<Artist> getArtists() {
        return artists;
    }
    public ArrayList<Host> getHosts() {
        return hosts;
    }
}
