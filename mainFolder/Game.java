import java.util.*;

public class Game {
    // instance variables
    String name;
    Adventurer adventurer;
    Location currentLocation;
    private ArrayList<Location> locations;

    // constructor
    public Game(String name) {
        this.adventurer = new Adventurer(name, 100, 5); // new adventurer with name and health of 100 and attackpower of
                                                        // 5 (default attackpower)
        this.currentLocation = new Location("Home Base", "The starting point"); // makes home base (default starting
                                                                                // point)
        this.locations = new ArrayList<Location>();
        this.locations.add(new Location("Home Base", "The starting point")); // adds home base
        Location forest = new Location("Forest", "A dense forest with tall trees"); // new location forest and
        locations.add(forest); // adds to locations list
        forest.addAdventurer(new Adventurer("Goblin", 100, 2)); // add goblin enemy to forest location
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in); // scanner to read user input

        boolean continuePlaying = true; // thing to control game loop

        while (continuePlaying) { // just a loop to do while the player wants to continue playing
            System.out.println("\nCurrent Location: [" + currentLocation.getName() + "]"); // prints location
            System.out.println("Input a command (type \'help\' to get a list of every command!): ");
            System.out.print("> "); // lets user input command
            String userCommand = scanner.nextLine();

            switch (userCommand.toLowerCase()) { // switch statement to handle user commands
                case "move": // print all locations
                    System.out.println("\nLocations: ");
                    int locnumber = 1;
                    for (Location loc : locations) {
                        System.out.println(locnumber + ". " + loc.getName() + "\n   Description: "
                                + loc.getDescription() + "\n");
                        locnumber++;
                    }
                    /// asks which location user wants to move toz
                    System.out.println("Which location would you like to move to?");
                    String userLocationChoice = scanner.nextLine();
                    move(userLocationChoice);
                    break;

                case "attack":
                    // asks user who to attack
                    System.out.println("Who would you like to attack?\n");
                    int foradvnum = 1;
                    for (Adventurer adv : currentLocation.getAdventurers()) {
                        System.out.println(foradvnum + ". " + adv.getName());
                        foradvnum++;
                    }
                    String userAttackChoice = scanner.nextLine();
                    // iterate over all adventurers
                    for (Adventurer adv : currentLocation.getAdventurers()) {
                        if (adv.getName().equalsIgnoreCase(userAttackChoice)) {
                            fight(adv); // if chosen adventurer is found call fight method
                            break;
                        }
                    }
                    break;

                case"credits": // prints credits a method isnt really needed for this
                    System.out.println("\nDeveloped by: umadkek\n");
                    System.out.println("Testing by: umadkek\n");
                    System.out.println("Utilities: \nJava Version 8\nVisual Studio Code Version 1.88.1\nGit GUI Version 0.21\n");
                    break;

                case"getstats":
                // asks user who to get status of
                    System.out.println("Which adventurer would you like to get the status of? (You can only get the status of adventurers in your location!)");
                    // prints all adventurers in location
                    System.out.println("Adventurers in location " + currentLocation.getName() + ":");
                    currentLocation.printAdventurers();
                    // gets user input
                    String userStatusChoice = scanner.nextLine();
                    getStats(userStatusChoice);
                    break;

                case"help": // a list of every command
                    System.out.println("move: lets you move to a different location\nattack: lets you attack an adventurer at your current location\ngetstats: gets the statistics of an adventurer at your location\ncredits: displays game credits\nquit: quits the game");
                    break;

                case "quit":
                    // set continueplaying to false to break game loop
                    continuePlaying = false;
                    break;

                default:
                    // error message if user inputs invalid command
                    System.out.println("Invalid Command.\n");
                    break;
            }
        }

        // prints thank you msg and closes scanner to prevent overflow
        System.out.println("Thanks for playing!");
        scanner.close();
    }

    // move method
    public void move(String locationName) {
        // iterate over all locations
        for (Location loc : locations) {
            // if chosen location is found print a message and move user to chosen location
            if (loc.getName().equalsIgnoreCase(locationName)) {
                System.out.println("\nAdventurer: " + adventurer.getName() + " has moved from "
                        + currentLocation.getName() + " to " + loc.getName() + "!\n");
                currentLocation.removeAdventurer(adventurer);
                loc.addAdventurer(adventurer);
                currentLocation = loc;
                return;
            }
        }
        // prints error message if location is invalid and ends move method
        System.out.println("Invalid location!");
        return;
    }

    // fight method
    public void fight(Adventurer opponent) {
        // if opponents health is greater than zero call attack method
        if (opponent.getHealth() > 0) {
            adventurer.attack(opponent);
            // if opponents health is less than or equal to zero then print message that
            // adventurer has defeated opponent
            if (opponent.getHealth() <= 0) {
                System.out.println(adventurer.getName() + " has defeated " + opponent.getName() + "!\n");
                // if opponents health is still greater than zero then it attacks back
            } else {
                opponent.attack(adventurer); // opponent attacks user
                if (adventurer.getHealth() <= 0) { // if users health is zero or less prints defeat statement, sad i
                                                   // know
                    System.out.println("You have been defeated!");
                }
            }
        } else { // tryna attack a dead person is crazy though
            System.out.println(opponent.getName() + " is already defeated.");
        }
    }

    public void getStats(String adventurer) {
        for (Adventurer adv : currentLocation.getAdventurers()) {
            if(adventurer.equalsIgnoreCase(adv.getName()) && !adventurer.equalsIgnoreCase(this.adventurer.getName())){ // for if loop that i cba to explain in comments
                System.out.println("Name: " + adv.getName());
                System.out.println("Health: " + adv.getHealth());
                System.out.println("Strength: " + adv.getAttackPower()); // prints details of adventurers in current location
            }
        }
    }
}
