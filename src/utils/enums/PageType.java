package utils.enums;

public enum PageType {
    HOME("home"),
    LIKED_CONTENT("likedContent"),
    ARTIST_PAGE("artistPage"),
    HOST_PAGE("hostPage");

    private final String label;

    PageType(final String label) {
        this.label = label;
    }

    /**
     * Gets a PageType enum from the label String.
     * @param text String that will be compared to the label.
     * @return PageType enum corresponding to the label.
     */
    public static PageType fromString(final String text) {
        for (PageType pageType : PageType.values()) {
            if (pageType.label.equals(text)) {
                return pageType;
            }
        }
        return null;
    }
}
