package database.users;

import utils.enums.UserType;

public class Host extends User {


    /* Constructor */
    public Host(String username, int age, String city) {
        super(username, age, city);
        this.setType(UserType.HOST);
    }

    /* Getters and Setters */
}
