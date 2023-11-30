package commands;

import client.Session;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Playlist;
import database.User;
import fileio.input.CommandInput;
import fileio.output.PrinterBasic;
import utils.AudioType;

public final class FollowPlaylistCommand implements ICommand {
    private final Session session;
    private final CommandInput commandInput;
    private final User user;
    private final ArrayNode output;

    /* Constructor */
    public FollowPlaylistCommand(final Session session, final CommandInput commandInput,
                                 final User user, final ArrayNode output) {
        this.session = session;
        this.commandInput = commandInput;
        this.user = user;
        this.output = output;
    }

    @Override
    public void execute() {
        session.setTimestamp(commandInput.getTimestamp());
        PrinterBasic printer = new PrinterBasic(user, session, output, commandInput.getCommand());

        if (user.getSelection() == null) {
            printer.print("Please select a source before following or unfollowing.");
            return;
        }

        if (user.getSelection().getType() != AudioType.PLAYLIST) {
            printer.print("The selected source is not a playlist.");
            return;
        }

        Playlist playlist = (Playlist) user.getSelection();

        if (playlist.getOwner().equals(user.getUsername())) {
            printer.print("You cannot follow or unfollow your own playlist.");
            return;
        }

        if (!user.getFollowedPlaylists().contains(playlist)) {
            user.addFollowedPlaylist(playlist);
            playlist.incrementFollowersCnt();
            printer.print("Playlist followed successfully.");
            return;
        }

        user.removeFollowedPlaylist(playlist);
        playlist.decrementFollowersCnt();
        printer.print("Playlist unfollowed successfully.");
    }
}
