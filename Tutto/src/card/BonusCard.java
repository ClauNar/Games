package main.java.com.tuttogame.card;

import main.java.com.tuttogame.game.TurnResults;

public class BonusCard extends AbstractCard {
    protected int bonus;

    public BonusCard(int bonus){
        super();
        this.bonus = bonus;
        name = giveName();
        description = giveDescription();
        cardFront = giveCardFront();
    }

    @Override
    public String giveName() {
        return "Bonus " + Integer.toString(this.bonus) + " Card";
    }

    @Override
    public String giveDescription() {
        return "If you accomplish a TUTTO, you get the bonus points indicated on\n" +
                "the card in addition to the points you have rolled. If you stop and have not\n" +
                "accomplished a TUTTO, you score only the points rolled without getting the bonus.";
    }

    @Override
    public String giveCardFront() {
        return  "┌───────────┐\n" +
                "│ B O N U S │\n" +
                "│   *****   │\n" +
                "│    "+ Integer.toString(this.bonus) +"    │\n" +
                "│   *****   │\n" +
                "│ B O N U S │\n" +
                "└───────────┘";
    }

    @Override
    public void calculatePointsForTutto(TurnResults turnResults) {
        turnResults.increasePoints(bonus);
    }
}
