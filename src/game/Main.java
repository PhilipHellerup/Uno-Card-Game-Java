package game; // Project Organization

/* --- Library --- */
import cards.*;

/* --- Main Class --- */
public class Main {
    public static void main(String[] args) {
        NumberCard red5 = new NumberCard(Color.RED, 5);
        NumberCard blue5 = new NumberCard(Color.BLUE, 5);
        NumberCard red2 = new NumberCard(Color.RED, 2);

        System.out.println(red5.canPlayCard(blue5)); // true (same number)
        System.out.println(red5.canPlayCard(red2));  // true (same color)
        System.out.println(red5.canPlayCard(new NumberCard(Color.GREEN, 9))); // false
    }
}
