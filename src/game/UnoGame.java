package game; // Project Organization

/* --- Library --- */
import cards.*;
import java.util.*;

/* --- UnoGame Class --- */
// Represents a Uno Game Engine
public class UnoGame {
    // Attributes
    private final Player player1; // Player 1
    private final Player player2; // Player 2
    private final Deck deck;      // Card Piles (Draw Pile & Discard Pile)

    private Player currentPlayer; // Current Player
    private Player nextPlayer;    // Next Player
    private UnoCard topCard;      // Top Card in the deck

    private boolean gameOver = false; // GameOver Status

    // Scanner for console input (shared across methods)
    private final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    // Constructor
    public UnoGame(String p1, String p2) {
        // Create players
        player1 = new Player(p1);
        player2 = new Player(p2);

        // Set current and next player
        currentPlayer = player1;
        nextPlayer = player2;

        // Create the Card Piles (Draw Pile & Discard Pile)
        deck = new Deck();

        // Deals initial cards to players
        dealInitialCards();

        // Draw initial top card for discard pile
        topCard = deck.drawCard();
        deck.startDiscard(topCard);
        System.out.println("Initial top card: " + topCard);
    }

    // Methods
    // Deals 7 cards to each player at the start of the game
    private void dealInitialCards() {
        for (int i = 0; i < 7; i++) {
            player1.addCard(deck.drawCard());
            player2.addCard(deck.drawCard());
        }
    }

    // Start the main game loop
    public void startGame() {
        while (!gameOver) {
            playTurn();
            switchPlayers();
        }
    }

    // Handles a single player's turn
    private void playTurn() {
        System.out.println("\n--- " + currentPlayer.getName() + "'s turn ---");
        System.out.println("Top card: " + topCard);

        // Player chooses a card to play
        UnoCard playedCard = currentPlayer.chooseCardToPlay(topCard);

        if (playedCard == null) {
            // Player draws a card
            UnoCard drawn = deck.drawCard();
            System.out.println(currentPlayer.getName() + " draws: " + drawn);
            currentPlayer.addCard(drawn);
        } else {
            // Play the selected card
            topCard = playedCard;
            deck.discard(playedCard);
            playedCard.play();

            // Apply effects if action or wild card
            if (playedCard instanceof ActionCard action) {
                applyActionCard(action);
            } else if (playedCard instanceof WildCard wild) {
                applyWildCard(wild);
            }
        }

        // Check for winner
        if (currentPlayer.hasWon()) {
            System.out.println(currentPlayer.getName() + " wins! 🎉");
            gameOver = true;
        }
    }

    // Applies the effect of an ActionCard
    private void applyActionCard(ActionCard card) {
        switch (card.getAction()) {
            case SKIP -> {
                System.out.println(nextPlayer.getName() + " is skipped!");
                // Skip next turn by switching players twice
                switchPlayers();
            }
            case REVERSE -> {
                System.out.println("Reverse! But with 2 players, this just swaps turns normally.");
            }
            case DRAW_TWO -> {
                System.out.println(nextPlayer.getName() + " draws 2 cards.");
                nextPlayer.addCard(deck.drawCard()); // First Card Drawn
                nextPlayer.addCard(deck.drawCard()); // Second Card Drawn
            }
        }
    }

    // Applies the effect of a WildCard
    private void applyWildCard(WildCard card) {
        Color chosenColor = chooseColor();
        System.out.println(currentPlayer.getName() + " chooses color: " + chosenColor);

        // For simplicity, I just set the topCard's color visually
        // (Could extend UnoCard to have currentColor)
        topCard = card; // Top Card is wild
        if (card.getType() == WildCard.WildType.DRAW_FOUR) {
            System.out.println(nextPlayer.getName() + " draws 4 cards.");
            for (int i = 0; i < 4; i++) {
                nextPlayer.addCard(deck.drawCard());
            }
        }
    }

    // Prompts the current player to choose a color for a wild card.
    private Color chooseColor() {
        System.out.println("Choose a color: 0=RED, 1=BLUE, 2=GREEN, 3=YELLOW");
        while (true) {
            try {
                // User Choice
                int choice = scanner.nextInt();
                scanner.nextLine(); // Removes leftover space

                // Choice Switch
                return switch (choice) {
                    case 0 -> Color.RED;
                    case 1 -> Color.BLUE;
                    case 2 -> Color.GREEN;
                    case 3 -> Color.YELLOW;
                    default -> throw new NumberFormatException();
                };
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter 0, 1, 2, or 3:");
            }
        }
    }

    // Switches the current and next players-
    private void switchPlayers() {
        Player temp = currentPlayer;
        currentPlayer = nextPlayer;
        nextPlayer = temp;
    }
}
