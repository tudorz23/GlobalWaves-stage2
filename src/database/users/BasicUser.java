package database.users;

import fileio.input.UserInput;
import utils.enums.LogStatus;
import utils.enums.UserType;

public class BasicUser extends User {

    /* Constructors */
    public BasicUser(UserInput userInput) {
        super(userInput);
        this.setType(UserType.BASIC_USER);
        this.setLogStatus(LogStatus.ONLINE);
    }

    public BasicUser(String username, int age, String city) {
        super(username, age, city);
        this.setType(UserType.BASIC_USER);
        this.setLogStatus(LogStatus.ONLINE);
    }

    /* Getters and Setters */
}
