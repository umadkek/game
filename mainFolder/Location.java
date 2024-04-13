import java.util.*;

public class Location {
    private String name;
    private String description;
    private ArrayList<Adventurer> adventurers;

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.adventurers = new ArrayList<>();
    }

    public String getName() {
        return name; // gets name
    }

    public ArrayList<Adventurer> getAdventurers() {
        return adventurers; // gets all adventurers
    }

    public String getDescription() {
        return description; // returns description
    }

    public void addAdventurer(Adventurer adventurer) {
        this.adventurers.add(adventurer); // adds an adventurer to arraylist adventurers
    }

    public void removeAdventurer(Adventurer adventurer) {
        this.adventurers.remove(adventurer); // removes an adventurer from arraylist adventurers
    }
}