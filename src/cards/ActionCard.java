package cards; // Project Organization

/* --- ActionCard Class --- */
// Represents a special action Uno Card (Skip, Reverse, Draw Two)
public class ActionCard extends UnoCard {
    // Enum
    // The specific action this card represents
    public enum ActionType {
        SKIP, REVERSE, DRAW_TWO
    }

    // Attributes
    // The action of this card
    private final ActionType action;

    // Constructor
    public ActionCard(Color color, ActionType action) {
        // Call the UnoCard constructor to set the color
        super(color);
        this.action = action;
    }

    // Getters & Setters
    // Returns the type of action this card performs
    public ActionType getAction() {
        return action;
    }

    // Methods
    // Determines if this ActionCard can be played on top of another UnoCard.
    @Override
    public boolean canPlayCard(UnoCard topCard) {
        // If both UnoCards are the same color, playable
        if (topCard.getColor() == this.getColor()) {
            return true;
        }

        // If both UnoCards are of the same action type, playable
        if (topCard instanceof ActionCard otherCard) {
            return this.action == otherCard.action;
        }

        // Otherwise, cannot play
        return false;
    }

    // Defines what happens when this card is played
    @Override
    public void play() {
        System.out.println("Played " + toString());

        // Action Switch
        switch (action) {
            case SKIP -> System.out.println("Effect: Next player is skipped!");
            case REVERSE -> System.out.println("Effect: Play order reversed!");
            case DRAW_TWO -> System.out.println("Effect: Next player draws to cards!");
        }
    }

    // Returns a string representation of the card (e.g. "Blue DRAW_TWO")
    @Override
    public String toString() {
        return getColor() + " " + action;
    }
}
