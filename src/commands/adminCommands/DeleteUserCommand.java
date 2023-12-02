package commands.adminCommands;

import client.Session;
import com.fasterxml.jackson.databind.node.ArrayNode;
import commands.ICommand;
import database.users.Artist;
import database.users.User;
import fileio.input.CommandInput;
import fileio.output.PrinterBasic;

public class DeleteUserCommand implements ICommand {
    private final Session session;
    private final CommandInput commandInput;
    private final User user;
    private final ArrayNode output;

    /* Constructor */
    public DeleteUserCommand(final Session session, final CommandInput commandInput,
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

        session.getDatabase().simulateTimeForEveryone(session.getTimestamp());

        IDeleteUserStrategy deleteUserStrategy;
        try {
            deleteUserStrategy = getDeleteUserStrategy();
        } catch (IllegalArgumentException exception) {
            printer.print("No delete strategy for this user.");
            return;
        }

        if (deleteUserStrategy.deleteUser()) {
            printer.print(user.getUsername() + " was successfully deleted.");
            return;
        }

        printer.print(user.getUsername() + " can't be deleted.");
    }


    /**
     * Factory method to get the appropriate Strategy object for Delete USer Command.
     */
    private IDeleteUserStrategy getDeleteUserStrategy() {
        switch (user.getType()) {
            case ARTIST -> {
                return new DeleteArtistStrategy(session, (Artist) user);
            }
            // Never reached.
            default -> throw new IllegalArgumentException("Invalid user.");
        }
    }
}
