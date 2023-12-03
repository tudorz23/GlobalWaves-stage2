package commands.statsCommands.adminStats;

import client.Session;
import com.fasterxml.jackson.databind.node.ArrayNode;
import commands.ICommand;
import database.users.Artist;
import fileio.input.CommandInput;
import fileio.output.PrinterBasic;

import java.util.ArrayList;

import static utils.Constants.MAX_ARTIST_RANK_NUMBER;

public class GetTop5ArtistsCommand implements ICommand {
    private final Session session;
    private final CommandInput commandInput;
    private final ArrayNode output;

    /* Constructor */
    public GetTop5ArtistsCommand(final Session session, final CommandInput commandInput,
                                final ArrayNode output) {
        this.session = session;
        this.commandInput = commandInput;
        this.output = output;
    }

    @Override
    public void execute() {
        session.setTimestamp(commandInput.getTimestamp());
        PrinterBasic printer = new PrinterBasic(output, commandInput);

        ArrayList<Artist> artists = new ArrayList<>(session.getDatabase().getArtists());

        artists.sort((artist1, artist2) -> artist2.computeLikeCnt() - artist1.computeLikeCnt());

        while (artists.size() > MAX_ARTIST_RANK_NUMBER) {
            artists.remove(artists.size() - 1);
        }

        ArrayList<String> results = new ArrayList<>();
        for (Artist artist : artists) {
            results.add(artist.getUsername());
        }

        printer.printStringResultsStats(results);
    }
}
