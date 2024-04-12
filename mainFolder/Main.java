import java.util.*;
public class Main {
    
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Input username: ");
        String username = scanner.nextLine();
        Game game = new Game(username); //makes new game
        game.startGame(); //starts game
    }
}