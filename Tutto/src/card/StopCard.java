package main.java.com.tuttogame.card;

import main.java.com.tuttogame.game.TurnResults;

public class StopCard extends AbstractCard {
    public StopCard(){
        super();
    }

    @Override
    public String giveName() {
        return "Stop Card";
    }

    @Override
    public String giveDescription() {
        return "Tough luck! You have to end your turn, and it's the next players turn.";
    }

    @Override
    public String giveCardFront() {
        return "┌───────────┐\n" +
               "│S T O P  x │\n" +
               "│ x  x  x  x│\n" +
               "│x  x  x  x │\n" +
               "│  x  x  x  │\n" +
               "│ x  S T O P│\n" +
               "└───────────┘";
    }

    @Override
    public void executeCardEffect(TurnResults turnResults){}
    @Override
    public void calculatePointsForTutto(TurnResults turnResults) {}
}
