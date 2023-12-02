package pages;

import database.users.User;
import fileio.input.CommandInput;
import utils.enums.PageType;

public class PageFactory {
    private final User user;

    /* Constructor */
    public PageFactory(final User user) {
        this.user = user;
    }


    public Page getPage(final CommandInput commandInput) throws IllegalArgumentException {
        PageType pageType = PageType.fromString(commandInput.getNextPage());

        switch (pageType) {
            case HOME -> {
                return new HomePage(user);
            }
            case LIKED_CONTENT -> {
                return new LikedContentPage(user);
            }
            default -> throw new IllegalArgumentException("Invalid page.");
        }
    }
}
