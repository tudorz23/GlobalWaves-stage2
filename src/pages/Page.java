package pages;

import utils.enums.PageType;

public abstract class Page {
    private PageType type;

    /* Constructor */
    public Page() {
    }

    /* Getters and Setters */
    public PageType getType() {
        return type;
    }
    public void setType(PageType type) {
        this.type = type;
    }
}
