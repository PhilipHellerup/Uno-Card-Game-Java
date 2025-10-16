package cards; // Project Organization

/* --- ActionCard Class --- */
// Represents a Wild or Wild Draw Four Card
public class WildCard extends UnoCard {
    // Enum
    public enum WildType {
        NORMAL,   // Regular Wild Card
        DRAW_FOUR // Wild Draw Four Card
    }

    // Attributes
    // The specific type of this WildCard
    private final WildType type;

    // Constructor
    public WildCard(WildType type) {
        // Wild cards have no color before being played
        super(Color.NONE);
        this.type = type;
    }

    // Getters & Setters
    // Returns the type of the wildcard
    public WildType getType() {
        return type;
    }

    // Methods
    // Determines if this WildCard can be played on top of another UnoCard.
    @Override
    public boolean canPlayCard(UnoCard topCard) {
        return true; // Wild Cards can always be played
    }

    // Defines what happens when this card is played
    @Override
    public void play() {
        System.out.println("Played " + type + " card!");

        // Type Switch
        switch (type) {
            case NORMAL -> System.out.println("Effect: Choose a new color!");
            case DRAW_FOUR -> System.out.println("Effect: Choose a color and next player draws four cards!");
        }
    }

    // Returns a string representation of the card (e.g. "WILD DRAW_FOUR")
    @Override
    public String toString() {
        return "WILD " + type;
    }
}
