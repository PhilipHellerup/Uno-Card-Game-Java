package cards; // Project Organization

/* --- UnoCard Abstract Class --- */
public abstract class UnoCard {
    // Attributes
    private final Color color; // Color of the card

    // Constructor
    public UnoCard(Color color) {
        this.color = color;
    }

    // Getters & Setters
    public Color getColor() {
        return color;
    }

    // Methods
    // Determines if this card can be played on top of another card.
    public abstract boolean canPlayCard(UnoCard topCard);

    // Defines what happens when the card is played
    public abstract void play();

    // Returns a string representation of the card,
    // useful for displaying in the console
    @Override
    public String toString() {
        return color + " " + getClass().getSimpleName();
    }
}
