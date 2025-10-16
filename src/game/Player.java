package game; // Project Organization

/* --- Library --- */
import cards.*;
import java.util.*;

/* --- Player Class --- */
// Represents a Uno Player in the game
public class Player {
    // Attributes
    // The player's display name
    private final String name;

    // List of Cards currently in the player's hand
    private final List<UnoCard> hand;

    // Scanner for console input (shared across methods)
    private final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    // Constructor
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    // Getters & Setters
    // Returns the player's name
    public String getName() {
        return name;
    }

    // Returns the number of cards the player currently holds
    public int getHandSize() {
        return hand.size();
    }

    // Methods
    // Adds a card to the player's hand
    public void addCard(UnoCard card) {
        hand.add(card);
    }

    // Removes a specific card from the player's hand
    public void removeCard(UnoCard card) {
        hand.remove(card);
    }

    // Returns a list of all cards in the player's hand.
    public List<UnoCard> getHand() {
        return new ArrayList<>(hand); // Returns a copy to protect encapsulation
    }

    // Displays the player's hand in a readable way in the console
    public void showHand() {
        System.out.println("\n" + name + "'s hand:");
        for (int i = 0; i < getHandSize(); i++) {
            System.out.println("{" + i + "} " + hand.get(i));
        }
    }

    // Prompts the player to select a card to play from their hand
    public UnoCard chooseCardToPlay(UnoCard topCard) {
        showHand();
        System.out.println("Top Card: " + topCard);
        System.out.println("Choose a card index to play, or -1 to draw a card");

        while (true) {
            try {
                // User Choice
                int choice = scanner.nextInt();
                scanner.nextLine(); // Removes leftover space

                // If Player chooses to draw a card
                if (choice == -1) {
                    return null;
                }

                // Validate the choice
                if (choice < 0 || choice >= getHandSize()) {
                    System.out.println("Invalid index. Try Again:");
                    continue;
                }

                UnoCard selectedCard = hand.get(choice);

                // Check if it can be played on the top card
                if (!selectedCard.canPlayCard(topCard)) {
                    System.out.println("You cannot play this card on " + topCard);
                    continue;
                }

                // Valid play
                hand.remove(choice);
                return selectedCard;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            } catch (Exception e) {
                System.out.println("Something went wrong!");
            }
        }
    }

    // Checks if the player has no cards left = THEY WIN!
    public boolean hasWon() {
        return hand.isEmpty();
    }
}
