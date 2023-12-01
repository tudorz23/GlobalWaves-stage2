package pages;

import database.audio.Playlist;
import database.audio.Song;
import database.users.User;
import utils.enums.PageType;
import java.util.ArrayList;
import java.util.Iterator;

import static utils.Constants.MAX_PLAYLIST_RANK_NUMBER;
import static utils.Constants.MAX_SONG_RANK_NUMBER;

public class HomePage extends Page {
    private ArrayList<Song> songRecommendation;
    private ArrayList<Playlist> playlistRecommendation;

    /* Constructor */
    public HomePage(User owningUser) {
        super(owningUser);
        setType(PageType.HOME);
    }

    @Override
    public String printPage() {
        refreshPage(getOwningUser());
        StringBuilder stringBuilder = new StringBuilder("Liked songs:\n\t[");

        Iterator<Song> songIterator = songRecommendation.iterator();
        while (songIterator.hasNext()) {
            Song song = songIterator.next();
            stringBuilder.append(song.getName());

            if (songIterator.hasNext()) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("]\n\nFollowed playlists:\n\t[");
        Iterator<Playlist> playlistIterator = playlistRecommendation.iterator();
        while (playlistIterator.hasNext()) {
            Playlist playlist = playlistIterator.next();
            stringBuilder.append(playlist.getName());

            if (playlistIterator.hasNext()) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private void refreshPage(User user) {
        refreshSongRecommendation(user);
        refreshPlaylistRecommendation(user);
    }

    private void refreshSongRecommendation(User user) {
        songRecommendation = new ArrayList<>(user.getLikedSongs());

        songRecommendation.sort((song1, song2) -> song2.getLikeCnt() - song1.getLikeCnt());

        while (songRecommendation.size() > MAX_SONG_RANK_NUMBER) {
            songRecommendation.remove(songRecommendation.size() - 1);
        }
    }

    private void refreshPlaylistRecommendation(User user) {
        playlistRecommendation = new ArrayList<>(user.getFollowedPlaylists());

        playlistRecommendation.sort((playlist1, playlist2) ->
                playlist2.computeLikeCnt() - playlist1.computeLikeCnt());

        while (playlistRecommendation.size() > MAX_PLAYLIST_RANK_NUMBER) {
            playlistRecommendation.remove(playlistRecommendation.size() - 1);
        }
    }

    /* Getters and Setters */
    public ArrayList<Song> getSongRecommendation() {
        return songRecommendation;
    }
    public void setSongRecommendation(ArrayList<Song> songRecommendation) {
        this.songRecommendation = songRecommendation;
    }
    public ArrayList<Playlist> getPlaylistRecommendation() {
        return playlistRecommendation;
    }
    public void setPlaylistRecommendation(ArrayList<Playlist> playlistRecommendation) {
        this.playlistRecommendation = playlistRecommendation;
    }
}
