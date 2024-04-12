import java.util.*;
public class Game {
    String name; 
    Adventurer adventurer;
    Location currentLocation;
    
    public Game(String name) {
        this.adventurer = new Adventurer(name, 100, 5); //new adventurer with name and health of 100 and attackpower of 5 (default attackpower)
        this.currentLocation = new Location("Home Base", "The starting point"); //makes home base (default starting point)
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        boolean continuePlaying = true;

        while(continuePlaying) { //just a loop to do while the player wants to continue playing
            System.out.println("You are at " + currentLocation.getName()); //prints location
            System.out.print("> "); //lets user input command
            String userCommand = scanner.nextLine();

            if(userCommand.equalsIgnoreCase("quit")) {
                continuePlaying = false; //quits the game
            } else {
                //code to do here
            }
        }

        System.out.println("Thanks for playing!");
    }
}
