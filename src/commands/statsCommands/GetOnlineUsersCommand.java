package commands.statsCommands;

import client.Session;
import com.fasterxml.jackson.databind.node.ArrayNode;
import commands.ICommand;
import fileio.input.CommandInput;
import fileio.output.PrinterUsers;

public class GetOnlineUsersCommand implements ICommand {
    private final Session session;
    private final CommandInput commandInput;
    private final ArrayNode output;

    /* Constructor */
    public GetOnlineUsersCommand(final Session session, final CommandInput commandInput,
                               final ArrayNode output) {
        this.session = session;
        this.commandInput = commandInput;
        this.output = output;
    }

    @Override
    public void execute() {
        session.setTimestamp(commandInput.getTimestamp());
        PrinterUsers printer = new PrinterUsers(session, output);

        printer.printOnlineUsers();
    }
}
