package commands.adminCommands;

import client.Session;
import commands.ICommand;
import database.users.BasicUser;

public class DeleteBasicUserCommand implements IDeleteUserStrategy {
    private final Session session;
    private final BasicUser basicUser;

    /* Constructor */
    public DeleteBasicUserCommand(final Session session, final BasicUser basicUser) {
        this.session = session;
        this.basicUser = basicUser;
    }

    @Override
    public boolean deleteUser() {
        session.getDatabase().removeUser(basicUser);
        return  true;
    }
}
