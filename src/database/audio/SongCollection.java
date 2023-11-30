package database.audio;

import database.Player;
import utils.enums.PlayerState;
import utils.enums.RepeatState;
import java.util.ArrayList;

/**
 * General class for song collections that support changes in repeat mode and
 * shuffling. Extended by Playlist and Album.
 */
public abstract class SongCollection extends Audio {
    private ArrayList<Song> songs;
    private int playingSongIndex; // Index from the songs array.
    private ArrayList<Integer> shuffleArray;
    private int likeCnt; // Total number of likes of its songs.

    /* Constructor */
    public SongCollection(final String name) {
        super(name);
        this.songs = new ArrayList<>();
        this.likeCnt = 0;
    }

    @Override
    public void simulateTimePass(final Player player, final int currTime) {
        if (player.getPlayerState() == PlayerState.PAUSED
                || player.getPlayerState() == PlayerState.STOPPED) {
            return;
        }

        int elapsedTime = currTime - player.getPrevTimeInfo();
        int songRemainingTime = songs.get(playingSongIndex).getRemainedTime();

        while (elapsedTime >= songRemainingTime) {
            if (player.getPlayerState() == PlayerState.STOPPED) {
                return;
            }

            changeToNextSong(player);
            elapsedTime -= songRemainingTime;

            songRemainingTime = songs.get(playingSongIndex).getRemainedTime();
        }

        if (elapsedTime == 0) {
            return;
        }

        Song playingSong = songs.get(playingSongIndex);
        int songNewTimePos = playingSong.getTimePosition() + elapsedTime;
        playingSong.setTimePosition(songNewTimePos);
    }

    /**
     * Moves to the next song in the song collection, considering the shuffled state
     * of the collection and the repeat state.
     */
    private void changeToNextSong(final Player player) {
        if (player.getRepeatState() == RepeatState.REPEAT_CURR_SONG_COLLECTION) {
            songs.get(playingSongIndex).resetTimePosition();
            return;
        }

        int shuffleIndex = getShuffleIndex(playingSongIndex);

        if (shuffleIndex == shuffleArray.size() - 1
                && player.getRepeatState() == RepeatState.NO_REPEAT_COLLECTION) {
            // If No repeat is enabled and last song is reached, stop the player.
            Song currSong = songs.get(playingSongIndex);
            currSong.setTimePosition(currSong.getDuration());

            player.setPlayerState(PlayerState.STOPPED);
            player.setShuffle(false);
            return;
        }

        // Surely, it is either not last song or Repeat all is enabled.
        int nextShuffleIdx = (shuffleIndex + 1) % (shuffleArray.size());
        int nextSongIndex = shuffleArray.get(nextShuffleIdx);

        this.playingSongIndex = nextSongIndex;
        Song newSong = songs.get(nextSongIndex);
        newSong.setTimePosition(0);
    }

    /**
     * @return Index of the shuffleArray where the songArrayIndex
     * is found as a value.
     * @param songArrayIndex index from the array of songs.
     * @throws IllegalArgumentException if the passed index is out of bounds.
     */
    private int getShuffleIndex(final int songArrayIndex) {
        for (int i = 0; i < shuffleArray.size(); i++) {
            if (shuffleArray.get(i) == songArrayIndex) {
                return i;
            }
        }

        // Should never be reached.
        throw new IllegalArgumentException("Invalid index argument passed!");
    }

    @Override
    public int getRemainedTime() {
        Song currPlayingSong = songs.get(playingSongIndex);
        return currPlayingSong.getRemainedTime();
    }

    @Override
    public void next(final Player player) {
        changeToNextSong(player);

        if (player.getPlayerState() == PlayerState.PAUSED) {
            player.setPlayerState(PlayerState.PLAYING);
        }
    }

    @Override
    public void prev(final Player player) {
        Song playingSong = songs.get(playingSongIndex);

        if (playingSong.getTimePosition() != 0) {
            // At least 1 second passed.
            playingSong.setTimePosition(0);

            if (player.getPlayerState() == PlayerState.PAUSED) {
                player.setPlayerState(PlayerState.PLAYING);
            }
            return;
        }

        // No second passed.
        changeToPrevSong();

        if (player.getPlayerState() == PlayerState.PAUSED) {
            player.setPlayerState(PlayerState.PLAYING);
        }
    }

    /**
     * Moves to the previous song in the playlist, considering the shuffle state
     * of the playlist.
     */
    private void changeToPrevSong() {
        int shuffleIndex = getShuffleIndex(playingSongIndex);

        if (shuffleIndex == 0) {
            // First song.
            return;
        }

        int prevShuffleIndex = shuffleIndex - 1;
        int prevSongIndex = shuffleArray.get(prevShuffleIndex);

        playingSongIndex = prevSongIndex;
        Song prevSong = songs.get(prevSongIndex);
        prevSong.setTimePosition(0);
    }

    @Override
    public String getPlayingTrackName() {
        Song currPlayingSong = songs.get(playingSongIndex);
        return currPlayingSong.getName();
    }

    /**
     * Adds a song to the collection.
     */
    public void addSong(final Song song) {
        songs.add(song);
    }

    /**
     * Removes a song from the collection.
     */
    public void removeSong(final Song song) {
        songs.remove(song);
    }

    /**
     * Initializes the shuffle array with the array v[i] = i (i.e. un-shuffled).
     */
    public void initializeShuffleArray() {
        shuffleArray = new ArrayList<>();
        for (int i = 0; i < songs.size(); i++) {
            shuffleArray.add(i);
        }
    }

    /**
     * If the collection contains the song, likeCnt is incremented.
     * @param song recently liked song.
     */
    public void incrementLikeCntAttempt(Song song) {
        if (getSongs().contains(song)) {
            likeCnt++;
        }
    }

    /**
     * If the collection contains the song, likeCnt is decremented.
     * @param song recently liked song.
     */
    public void decrementLikeCntAttempt(Song song) {
        if (getSongs().contains(song)) {
            likeCnt--;
        }
    }

    /* Getters and Setters */
    public ArrayList<Song> getSongs() {
        return songs;
    }
    public void setSongs(final ArrayList<Song> songs) {
        this.songs = songs;
    }
    public int getPlayingSongIndex() {
        return playingSongIndex;
    }
    public void setPlayingSongIndex(int playingSongIndex) {
        this.playingSongIndex = playingSongIndex;
    }
    public ArrayList<Integer> getShuffleArray() {
        return shuffleArray;
    }
    public void setShuffleArray(ArrayList<Integer> shuffleArray) {
        this.shuffleArray = shuffleArray;
    }
    public int getLikeCnt() {
        return likeCnt;
    }
    public void setLikeCnt(int likeCnt) {
        this.likeCnt = likeCnt;
    }
}
