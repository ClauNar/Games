package main.java.com.tuttogame.card;

import main.java.com.tuttogame.game.TurnResults;

public class CloverleafCard extends AbstractCard {

    public CloverleafCard(){
        super();
    }

    @Override
    public String giveName() {
        return "Cloverleaf Card";
    }

    @Override
    public String giveDescription() {
        return "You have to try to accomplish a TUTTO twice in a row on this turn and \n" +
                "may not stop before you do. If you roll a null, you don’t score any points. But\n" +
                "if you succeed, the game ends immediately, and you win – no matter what score\n" +
                "you have!";
    }

    @Override
    public String giveCardFront() {
        return "┌───────────┐\n" +
               "│ (')       │\n" +
               "│(_|_)      │\n" +
               "│  |        │\n" +
               "│   clover  │\n" +
               "│       leaf│\n" +
               "└───────────┘";
    }

    @Override
    public void calculatePointsForTutto(TurnResults turnResults) {

    }
}
