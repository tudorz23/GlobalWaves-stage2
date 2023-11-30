package fileio.output;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.CommandInput;

/**
 * The most commonly-used printer for a simple message.
 */
public final class PrinterBasic extends Printer {
    private final CommandInput commandInput;

    /* Constructor */
    public PrinterBasic(final ArrayNode output, CommandInput commandInput) {
        super(output);
        this.commandInput = commandInput;
    }

    /**
     * Appends a standard output, composed of generic data and a message,
     * to the output ArrayNode.
     */
    public void print(final String message) {
        ObjectNode commandNode = mapper.createObjectNode();

        commandNode.put("command", commandInput.getCommand());
        commandNode.put("user", commandInput.getUsername());
        commandNode.put("timestamp", commandInput.getTimestamp());
        commandNode.put("message", message);

        output.add(commandNode);
    }
}
