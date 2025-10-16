/* --- Library --- */
import game.UnoGame;

import java.util.Locale;
import java.util.Scanner;

/* --- Main Class --- */
public class Main {
    public static void main(String[] args) {
        // Scanner for console input (shared across methods)
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        // Ask for player names
        System.out.println("Welcome to UNO!");
        System.out.print("Enter name for Player 1: ");
        String name1 = scanner.nextLine().trim();
        System.out.print("Enter name for Player 2: ");
        String name2 = scanner.nextLine().trim();

        // Create the game
        UnoGame game = new UnoGame(name1, name2);

        // Start the game loop
        game.startGame();
    }
}
