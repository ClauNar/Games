package main.java.com.tuttogame.card;

import main.java.com.tuttogame.game.TurnResults;

public class TimesTwoCard extends AbstractCard{
    @Override
    public String giveName() {
        return "x2 Card";
    }

    @Override
    public String giveDescription() {
        return "If you accomplish a “TUTTO”, all points you have rolled so far on this turn are\n" +
                "doubled. If you stop and have not accomplished a “TUTTO”, you score only the\n" +
                "points rolled.";
    }

    @Override
    public String giveCardFront() {
        return  "┌───────────┐\n" +
                "│2x         │\n" +
                "│           │\n" +
                "│     2x    │\n" +
                "│           │\n" +
                "│         2x│\n" +
                "└───────────┘";
    }
    @Override
    public void calculatePointsForTutto(TurnResults turnResults) {
    }
}
