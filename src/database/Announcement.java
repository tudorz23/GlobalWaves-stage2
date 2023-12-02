package database;

public class Announcement {
    private final String name;
    private final String description;

    /* Constructor */
    public Announcement(String name, String description) {
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
