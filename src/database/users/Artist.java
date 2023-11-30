package database.users;

import utils.enums.UserType;

public class Artist extends User {


    /* Constructor */
    public Artist(String username, int age, String city) {
        super(username, age, city);
        this.setType(UserType.ARTIST);
    }

    /* Getters and Setters */
}
