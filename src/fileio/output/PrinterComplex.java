package fileio.output;

import client.Session;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import database.audio.Album;
import database.audio.Playlist;
import database.audio.Song;

import java.util.ArrayList;

/**
 * Generic Printer class for complex commands.
 * To be subclassed by more specialized printers.
 */
public abstract class PrinterComplex extends Printer {
    protected Session session;

    /* Constructor */
    public PrinterComplex(final Session session, final ArrayNode output) {
        super(output);
        this.session = session;
    }

    /**
     * @param playlist Original Playlist object.
     * @return ObjectNode containing data regarding a Playlist.
     */
    public ObjectNode createPlaylistNode(final Playlist playlist) {
        ObjectNode playlistNode = mapper.createObjectNode();

        playlistNode.put("name", playlist.getName());

        ArrayNode songsNode = createSongsArrayNode(playlist.getSongs());
        playlistNode.set("songs", songsNode);

        playlistNode.put("visibility", playlist.getVisibility().getLabel());
        playlistNode.put("followers", playlist.getFollowersCnt());

        return playlistNode;
    }

    /**
     * @param songs ArrayList of Song objects.
     * @return ArrayNode containing the names of the songs from an ArrayList.
     */
    public ArrayNode createSongsArrayNode(final ArrayList<Song> songs) {
        ArrayNode songsArrayNode = mapper.createArrayNode();

        for (Song song : songs) {
            songsArrayNode.add(song.getName());
        }

        return songsArrayNode;
    }

    /**
     *
     * @param album Album object.
     * @return ObjectNode containing data regarding an Album.
     */
    public ObjectNode createAlbumNode(final Album album) {
        ObjectNode albumNode = mapper.createObjectNode();

        albumNode.put("name", album.getName());

        ArrayNode songsNode = createSongsArrayNode(album.getSongs());
        albumNode.set("songs", songsNode);

        return albumNode;
    }
}
