package database;

public class Event {
    private final String name;
    private final String description;
    private final String date;

    /* Constructor */
    public Event(String name, String description, String date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    /* Getters */
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getDate() {
        return date;
    }
}
