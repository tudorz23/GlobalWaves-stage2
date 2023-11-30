package database.users;

import pages.HostPage;
import utils.enums.UserType;

public class Host extends User {
    private HostPage officialPage;

    /* Constructor */
    public Host(String username, int age, String city) {
        super(username, age, city);
        this.setType(UserType.HOST);
    }

    /* Getters and Setters */
    public HostPage getOfficialPage() {
        return officialPage;
    }
}
