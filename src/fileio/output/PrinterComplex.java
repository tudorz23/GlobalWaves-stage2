package fileio.output;

import client.Session;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

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
}
