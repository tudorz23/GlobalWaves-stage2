package fileio.output;

import client.Session;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import database.users.User;
import utils.enums.LogStatus;

public class PrinterUsers extends PrinterComplex {
    /* Constructor */
    public PrinterUsers(final Session session, final ArrayNode output) {
        super(session, output);
    }

    public void printOnlineUsers() {
        ObjectNode commandNode = mapper.createObjectNode();

        commandNode.put("command", "getOnlineUsers");
        commandNode.put("timestamp", session.getTimestamp());

        ArrayNode result = mapper.createArrayNode();
        for (User user : session.getDatabase().getUsers()) {
            if (user.getLogStatus() == LogStatus.ONLINE) {
                result.add(user.getUsername());
            }
        }

        commandNode.set("result", result);
        output.add(commandNode);
    }
}
