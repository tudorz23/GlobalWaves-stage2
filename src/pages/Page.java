package pages;

import database.audio.Playlist;
import database.audio.Song;
import database.users.User;
import utils.enums.PageType;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Page {
    private User owningUser;
    private PageType type;

    /* Constructor */
    public Page(User owningUser) {
        this.owningUser = owningUser;
    }

    public abstract String printPage();

    /* Getters and Setters */
    public PageType getType() {
        return type;
    }
    public void setType(PageType type) {
        this.type = type;
    }
    public User getOwningUser() {
        return owningUser;
    }
    public void setOwningUser(User owningUser) {
        this.owningUser = owningUser;
    }
}
