package pages;

import database.audio.Playlist;
import database.audio.Song;
import utils.enums.PageType;

import java.util.ArrayList;

public class HomePage extends Page {
    private ArrayList<Song> likedSortedSongs;
    private ArrayList<Playlist> followedSortedPlaylists;

    /* Constructor */
    public HomePage() {
        super();
        setType(PageType.HOME);
        followedSortedPlaylists = new ArrayList<>();
        likedSortedSongs = new ArrayList<>();
    }

    /* Getters and Setters */
    public ArrayList<Song> getLikedSortedSongs() {
        return likedSortedSongs;
    }
    public void setLikedSortedSongs(ArrayList<Song> likedSortedSongs) {
        this.likedSortedSongs = likedSortedSongs;
    }
    public ArrayList<Playlist> getFollowedSortedPlaylists() {
        return followedSortedPlaylists;
    }
    public void setFollowedSortedPlaylists(ArrayList<Playlist> followedSortedPlaylists) {
        this.followedSortedPlaylists = followedSortedPlaylists;
    }
}
