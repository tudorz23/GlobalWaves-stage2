package database;

public final class Announcement {
    private final String name;
    private final String description;

    /* Constructor */
    public Announcement(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    /* Getters */
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}
