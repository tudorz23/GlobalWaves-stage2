package database.users;

import database.*;
import database.audio.Audio;
import database.audio.Playlist;
import database.audio.Podcast;
import database.audio.Song;
import fileio.input.UserInput;
import pages.Page;
import utils.enums.LogStatus;
import utils.enums.UserType;

import java.util.ArrayList;

public abstract class User {
    private String username;
    private int age;
    private String city;
    private ArrayList<Audio> searchResult;  // null when no search has been done.
    private Audio selection;
    private Player player;
    private ArrayList<Playlist> playlists;
    private ArrayList<Podcast> listenedPodcasts;
    private ArrayList<Song> likedSongs;
    private ArrayList<Playlist> followedPlaylists;
    private UserType type;
    private Page currPage;
    private LogStatus logStatus;

    /* Constructors */
    public User(String username, int age, String city) {
        this.username = username;
        this.age = age;
        this.city = city;
        this.searchResult = null;
        this.player = new Player();
        this.playlists = new ArrayList<>();
        this.listenedPodcasts = new ArrayList<>();
        this.likedSongs = new ArrayList<>();
        this.followedPlaylists = new ArrayList<>();
    }

    public User(final UserInput userInput) {
        this(userInput.getUsername(), userInput.getAge(), userInput.getCity());
    }

    /**
     * Adds a new Playlist to user's playlists.
     * @return true, for success, false, if a playlist with the
     * same name already exists in user's list.
     */
    public boolean addPlaylist(final Playlist newPlaylist) {
        for (Playlist playlist : playlists) {
            if (playlist.getName().equals(newPlaylist.getName())) {
                return false;
            }
        }

        playlists.add(newPlaylist);
        return true;
    }

    /**
     * Adds a new Playlist to user's followed playlists.
     */
    public void addFollowedPlaylist(final Playlist playlist) {
        followedPlaylists.add(playlist);
    }

    /**
     * Removes a Playlist from user's followed playlists.
     */
    public void removeFollowedPlaylist(final Playlist playlist) {
        followedPlaylists.remove(playlist);
    }

    /**
     * Adds a new Song to user's liked songs.
     */
    public void addLikedSong(final Song song) {
        likedSongs.add(song);
        song.incrementLikeCnt();
    }

    /**
     * Removes a Song from user's liked songs.
     */
    public void removeLikedSong(final Song song) {
        likedSongs.remove(song);
        song.decrementLikeCnt();
    }

    /* Getters and Setters */
    public String getUsername() {
        return username;
    }
    public void setUsername(final String username) {
        this.username = username;
    }
    public int getAge() {
        return age;
    }
    public void setAge(final int age) {
        this.age = age;
    }
    public String getCity() {
        return city;
    }
    public void setCity(final String city) {
        this.city = city;
    }
    public ArrayList<Audio> getSearchResult() {
        return searchResult;
    }
    public void setSearchResult(final ArrayList<Audio> searchResult) {
        this.searchResult = searchResult;
    }
    public Audio getSelection() {
        return selection;
    }
    public void setSelection(final Audio selection) {
        this.selection = selection;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(final Player player) {
        this.player = player;
    }
    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }
    public ArrayList<Podcast> getListenedPodcasts() {
        return listenedPodcasts;
    }
    public ArrayList<Song> getLikedSongs() {
        return likedSongs;
    }
    public ArrayList<Playlist> getFollowedPlaylists() {
        return followedPlaylists;
    }
    public UserType getType() {
        return type;
    }
    public void setType(UserType type) {
        this.type = type;
    }
    public Page getCurrPage() {
        return currPage;
    }
    public void setCurrPage(Page currPage) {
        this.currPage = currPage;
    }
    public LogStatus getLogStatus() {
        return logStatus;
    }
    public void setLogStatus(LogStatus logStatus) {
        this.logStatus = logStatus;
    }
}
