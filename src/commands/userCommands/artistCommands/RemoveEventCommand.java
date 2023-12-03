package commands.userCommands.artistCommands;

import client.Session;
import com.fasterxml.jackson.databind.node.ArrayNode;
import commands.ICommand;
import database.Event;
import database.users.Artist;
import database.users.User;
import fileio.input.CommandInput;
import fileio.output.PrinterBasic;
import utils.enums.UserType;

public class RemoveEventCommand implements ICommand {
    private final Session session;
    private final CommandInput commandInput;
    private final User user;
    private final ArrayNode output;

    /* Constructor */
    public RemoveEventCommand(final Session session, final CommandInput commandInput,
                              final User user, final ArrayNode output) {
        this.session = session;
        this.commandInput = commandInput;
        this.user = user;
        this.output = output;
    }

    @Override
    public void execute() {
        session.setTimestamp(commandInput.getTimestamp());
        PrinterBasic printer = new PrinterBasic(output, commandInput);

        if (user.getType() != UserType.ARTIST) {
            printer.print(user.getUsername() + " is not an artist.");
            return;
        }

        Artist artist = (Artist) user;
        Event event;

        try {
            event = artist.findEvent(commandInput.getName());
        } catch (IllegalArgumentException exception) {
            printer.print(exception.getMessage());
            return;
        }

        artist.removeEvent(event);
        printer.print(artist.getUsername() + " deleted the event successfully.");
    }
}
