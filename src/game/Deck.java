package game; // Project Organization

/* --- Library --- */
import cards.*;
import java.util.*;

/* --- Deck Class --- */
// Represents the Uno Deck used during the game
public class Deck {
    // Attributes
    // Stack for drawing cards (LIFO = Last-in -> First-Out)
    private final Stack<UnoCard> drawPile = new Stack<>();

    // Stack for discarded cards (top card = last played)
    private final Stack<UnoCard> discardPile = new Stack<>();

    // Constructor
    public Deck() {
        initialize();
        shuffle();
    }

    // Getters & Setters
    // Returns the current top card from the discard pile
    public UnoCard getTopCard() {
        return discardPile.peek();
    }

    // Methods
    // Builds a simplified Uno deck with:
    // - NumberCards (0-9 in each color)
    // - 1 of each ActionCard type per color
    // - 2 WildCards (1 normal, 1 draw four)
    private void initialize() {
        // Add number cards
        for (Color c : Color.values()) {
            if (c == Color.NONE) {
                continue; // Skip "NONE" color
            }

            for (int i = 0; i <= 9; i++) {
                drawPile.push(new NumberCard(c, i));
            }

            // Add one of each action card per color
            drawPile.push(new ActionCard(c, ActionCard.ActionType.SKIP));
            drawPile.push(new ActionCard(c, ActionCard.ActionType.REVERSE));
            drawPile.push(new ActionCard(c, ActionCard.ActionType.DRAW_TWO));
        }

        // Add wil cards
        drawPile.push(new WildCard(WildCard.WildType.NORMAL));
        drawPile.push(new WildCard(WildCard.WildType.DRAW_FOUR));
    }

    // Randomly shuffles the draw pile
    private void shuffle() {
        Collections.shuffle(drawPile);
    }

    // Draws one card from the top of the draw pile
    public UnoCard drawCard() {
        // If draw pile is empty, reshuffle from discard pile
        if (drawPile.isEmpty()) {
            reshuffleFromDiscard();
        }

        return drawPile.pop();
    }

    // Starts the discard pile with an initial top card
    public void startDiscard(UnoCard card) {
        discardPile.push(card);
    }

    // Adds a card to the top of the discard pile
    public void discard(UnoCard card) {
        discardPile.push(card);
    }

    // NOTE: startDiscard() & discard() do the same it is just for readability

    // When the draw pile is empty, reshuffle the discard pile
    // (Except for the top card, which must remain).

    private void reshuffleFromDiscard() {
        System.out.println("Reshuffling the discard pile...");

        if (discardPile.size() <= 1) {
            return;
        }

        // Save the top card
        UnoCard topCard = discardPile.pop();

        // Move all other cards back to draw pile
        while (!discardPile.isEmpty()) {
            drawPile.push(discardPile.pop());
        }

        // Shuffle the new draw pile
        shuffle();

        // Put the top card back on the discard pile
        discardPile.push(topCard);
    }

    // Return how many cards are left to draw
    public int remainingCards() {
        return drawPile.size();
    }
}
