package game; // Project Organization

/* --- Library --- */
import cards.*;

/* --- Main Class --- */
public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();

        System.out.println("Drawing 5 cards:");
        for (int i = 0; i < 5; i++) {
            System.out.println(deck.drawCard());
        }

        // Simulate starting a discard pile
        UnoCard top = deck.drawCard();
        deck.startDiscard(top);
        System.out.println("\nTop card on discard pile: " + deck.getTopCard());

        System.out.println("\nRemaining cards in deck: " + deck.remainingCards());

        Player p = new Player("Philip");

        // Give Alice a few cards
        p.addCard(new NumberCard(Color.RED, 5));
        p.addCard(new ActionCard(Color.RED, ActionCard.ActionType.SKIP));
        p.addCard(new NumberCard(Color.BLUE, 7));
        p.addCard(new WildCard(WildCard.WildType.NORMAL));

        // Simulate a top card
        UnoCard topCard = new NumberCard(Color.RED, 9);

        // Ask the player which card to play
        UnoCard chosen = p.chooseCardToPlay(topCard);

        if (chosen == null) {
            System.out.println("Philip chose to draw a card.");
        } else {
            System.out.println("Philip played: " + chosen);
        }

        /*
        NumberCard red5 = new NumberCard(Color.RED, 5);
        NumberCard blue5 = new NumberCard(Color.BLUE, 5);
        NumberCard red2 = new NumberCard(Color.RED, 2);

        System.out.println(red5.canPlayCard(blue5)); // true (same number)
        System.out.println(red5.canPlayCard(red2));  // true (same color)
        System.out.println(red5.canPlayCard(new NumberCard(Color.GREEN, 9))); // false

        ActionCard skipRed = new ActionCard(Color.RED, ActionCard.ActionType.SKIP);
        ActionCard drawTwoBlue = new ActionCard(Color.BLUE, ActionCard.ActionType.DRAW_TWO);
        NumberCard red7 = new NumberCard(Color.RED, 7);

        System.out.println(skipRed.canPlayCard(red7)); // true (same color)
        skipRed.play();
        drawTwoBlue.play();

        WildCard wild = new WildCard(WildCard.WildType.NORMAL);
        WildCard wild4 = new WildCard(WildCard.WildType.DRAW_FOUR);

        System.out.println(wild.canPlayCard(new NumberCard(Color.RED, 5))); // true
        wild.play();
        wild4.play();
        */
    }
}
