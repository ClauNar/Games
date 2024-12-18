package main.java.com.tuttogame.card;

import main.java.com.tuttogame.game.TurnResults;

public class PlusMinusCard extends AbstractCard{

    @Override
    public String giveName() {
        return "Puls Minus Card";
    }

    @Override
    public String giveDescription() {
        return "You must try to accomplish a TUTTO and may not stop before\n" +
                "you do. If you roll a null, you don’t score any points. But if you succeed, you score\n" +
                "exactly 1,000 points, irrespective of the number of points you have rolled. Besides\n" +
                "this, the leading player has 1,000 points deducted.\n" +
                "If more than one player is leading with the same number of points, each of them\n" +
                "has 1,000 points deducted. Nevertheless, you, as the player who is currently rolling\n" +
                "the dice, score 1,000 points only once. If it is the leading player who reveals this\n" +
                "card, naturally he doesn’t have to deduct any points from his score when he\n" +
                "accomplishes a TUTTO.";
    }

    @Override
    public String giveCardFront() {
        return "┌───────────┐\n" +
               "│P L U S + +│\n" +
               "│+ + + + + +│\n" +
               "│    +  -   │\n" +
               "│- - - - - -│\n" +
               "│- M I N U S│\n" +
               "└───────────┘";
    }

    @Override
    public void calculatePointsForTutto(TurnResults turnResults) {

    }
}
