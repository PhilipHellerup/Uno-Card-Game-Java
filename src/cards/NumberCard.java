package cards; // Project Organization

/* --- NumberCard Class --- */
// Represents a numbered Uno Card (0-9)
public class NumberCard extends UnoCard {
    // Attributes
    private final int value;

    // Constructor
    public NumberCard(Color color, int value) {
        // Call the UnoCard constructor to set the color
        super(color);
        this.value = value;
    }

    // Getters & Setters
    // Returns the numeric value of the card
    public int getValue() {
        return value;
    }

    // Methods
    // Determines if this NumberCard can be played on top of another UnoCard.
    @Override
    public boolean canPlayCard(UnoCard topCard) {
        // If both UnoCards are the same color, playable
        if (topCard.getColor() == this.getColor()) {
            return true;
        }

        // If both are NumberCards and the numbers match, playable
        if (topCard instanceof NumberCard otherNumberCard) {
            return this.value == otherNumberCard.getValue();
        }

        // Otherwise, cannot play
        return false;
    }

    // Defines what happens when this card is played
    @Override
    public void play() {
        System.out.println("Played a " + toString());
    }

    // Returns a string representation of the card (e.g. "RED 5")
    @Override
    public String toString() {
        return getColor() + " " + value;
    }
}
