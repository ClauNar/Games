package main.java.com.tuttogame.card;

import main.java.com.tuttogame.game.TurnResults;

public class StraightCard extends AbstractCard{

    public StraightCard(){
        super();
        rules.setSameCardAfterTuttoAndNoTuttoRequired(true);
        rules.setCanPlayerDecideToRollDiceAgain(false);
    }

    @Override
    public String giveName() {
        return "Straight Card";
    }

    @Override
    public String giveDescription() {
        return "Attention! This card changes the rules for valid dice. You have to try to\n" +
                "accomplish a “Straight” and may not stop before you do. A “Straight” consists of all\n" +
                "six numbers . As usual, you have to keep at least one valid die\n" +
                "after each roll. In this case, a valid die is a die that shows a number that you have not\n" +
                "yet put aside. If the roll doesn’t contain any valid die, it counts as a null and you don’t\n" +
                "score any points. But if you accomplish a “Straight”, you score 2,000 points for it. \n" +
                "A “Straight” is considered a TUTTO – consequently, you may continue if you want.";
    }

    @Override
    public String giveCardFront() {
        return  "┌───────────┐\n" +
                "│-Straight- │\n" +
                "│           │\n" +
                "│1 2 3 4 5 6│\n" +
                "│           │\n" +
                "│ -Straight-│\n" +
                "└───────────┘";
    }
    @Override
    public void calculatePointsForTutto(TurnResults turnResults) {
        turnResults.setPoints(2000);
    }

    @Override
    protected void selectValidPlayerChoiceDice(TurnResults turnResults){
        int points = diceInGame.selectAllValidDiceForStraight();
        turnResults.increasePoints(points);
    }

    @Override
    protected void findValidDice(){
        diceInGame.validSinglets.clear();
        diceInGame.findValidDiceForStraight();
    }
}