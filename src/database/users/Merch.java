package database.users;

public class Merch {
    private final String name;
    private final String description;
    private final int price;

    /* Constructor */
    public Merch(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /* Getters */
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getPrice() {
        return price;
    }
}
