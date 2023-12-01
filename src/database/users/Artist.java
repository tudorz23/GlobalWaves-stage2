package database.users;

import pages.ArtistPage;
import utils.enums.UserType;

public class Artist extends User {
    private ArtistPage officialPage;

    /* Constructor */
    public Artist(String username, int age, String city) {
        super(username, age, city);
        this.setType(UserType.ARTIST);
        this.officialPage = new ArtistPage();
    }

    /* Getters and Setters */
    public ArtistPage getOfficialPage() {
        return officialPage;
    }
}
