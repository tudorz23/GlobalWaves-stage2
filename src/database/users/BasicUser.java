package database.users;

import fileio.input.UserInput;
import utils.enums.UserType;

public class BasicUser extends User {

    /* Constructor */
    public BasicUser(UserInput userInput) {
        super(userInput);
        this.setType(UserType.BASIC_USER);
    }

    /* Getters and Setters */
}
