package main.java.com.tuttogame.card;


import main.java.com.tuttogame.game.TurnResults;

public class FireworksCard extends AbstractCard {
    public FireworksCard(){
        super();
        rules.setSameCardAfterTuttoAndNoTuttoRequired(true);
        rules.setCanPlayerDecideToRollDiceAgain(false);
        rules.setKeepPointsAfterNullRoll(true);
    }

    @Override
    public String giveName() {
        return  "Fireworks Card";
    }

    @Override
    public String giveDescription() {
        return "You have to keep throwing the dice until you roll a null. After each roll,\n" +
                "you need to keep all valid single dice and triplets. If you accomplish a TUTTO,\n" +
                "you have to continue without revealing a new card. Your turn ends only when you\n" +
                "roll a null. However, you score all points you have rolled on this turn.";
    }

    @Override
    public String giveCardFront() {
        return "┌───────────┐\n" +
               "│F I R E  *.│\n" +
               "│'*. W O R K│\n" +
               "│.' /*,.  *+│\n" +
               "│:-,  *,:, -│\n" +
               "│ *:/* +| .;│\n" +
               "└───────────┘";
    }
    @Override
    public void calculatePointsForTutto(TurnResults turnResults) {}
}
