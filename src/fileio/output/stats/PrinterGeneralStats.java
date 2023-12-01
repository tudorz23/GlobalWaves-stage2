package fileio.output.stats;

import client.Session;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import database.audio.Playlist;
import database.audio.Song;
import database.users.User;
import fileio.output.PrinterComplex;
import utils.enums.LogStatus;

import java.util.ArrayList;

public class PrinterGeneralStats extends PrinterComplex {
    /* Constructor */
    public PrinterGeneralStats(final Session session, final ArrayNode output) {
        super(session, output);
    }

    /**
     * Appends the Top5Playlists output to the output ArrayNode.
     */
    public void printTop5Playlists(final ArrayList<Playlist> followedPlaylists) {
        ObjectNode commandNode = mapper.createObjectNode();

        commandNode.put("command", "getTop5Playlists");
        commandNode.put("timestamp", session.getTimestamp());

        ArrayNode result = mapper.createArrayNode();
        for (Playlist playlist : followedPlaylists) {
            result.add(playlist.getName());
        }

        commandNode.set("result", result);
        output.add(commandNode);
    }

    /**
     * Appends the Top5Songs output to the output ArrayNode.
     */
    public void printTop5Songs(final ArrayList<Song> likedSongs) {
        ObjectNode commandNode = mapper.createObjectNode();

        commandNode.put("command", "getTop5Songs");
        commandNode.put("timestamp", session.getTimestamp());

        ArrayNode result = mapper.createArrayNode();
        for (Song song : likedSongs) {
            result.add(song.getName());
        }

        commandNode.set("result", result);
        output.add(commandNode);
    }

    public void printOnlineUsers() {
        ObjectNode commandNode = mapper.createObjectNode();

        commandNode.put("command", "getOnlineUsers");
        commandNode.put("timestamp", session.getTimestamp());

        ArrayNode result = mapper.createArrayNode();
        for (User user : session.getDatabase().getBasicUsers()) {
            if (user.getLogStatus() == LogStatus.ONLINE) {
                result.add(user.getUsername());
            }
        }

        commandNode.set("result", result);
        output.add(commandNode);
    }
}
