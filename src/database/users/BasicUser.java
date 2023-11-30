package database.users;

import fileio.input.UserInput;
import utils.enums.LogStatus;
import utils.enums.UserType;

public class BasicUser extends User {

    /* Constructor */
    public BasicUser(UserInput userInput) {
        super(userInput);
        this.setType(UserType.BASIC_USER);
        this.setLogStatus(LogStatus.ONLINE);
    }

    /* Getters and Setters */
}
