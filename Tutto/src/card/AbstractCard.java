package main.java.com.tuttogame.card;

import main.java.com.tuttogame.dice.DiceInGame;
import main.java.com.tuttogame.game.TurnResults;
import main.java.com.tuttogame.game.UserInputHandler;

public abstract class AbstractCard {
    protected DiceInGame diceInGame;
    protected String name;
    protected String description;
    protected String cardFront;
    protected Rules rules;

    AbstractCard(){
        diceInGame = new DiceInGame();
        name = giveName();
        description = giveDescription();
        cardFront = giveCardFront();
        rules = new Rules();
    }
    public abstract String giveName();
    public abstract String giveDescription();
    public abstract String giveCardFront();

    public void showCard(){
        System.out.println("You drew following card: " + name);
        System.out.println(cardFront);
        System.out.println("Description: " + description + "\n");
    }

    public void executeCardEffect(TurnResults turnResults){
        while (turnResults.isRollDiceAgain()){

            // roll dice (that are still in the game)
            UserInputHandler.waitForUserPressingEnter("Press Enter to roll the dice...");
            diceInGame.activeDiceSet.throwDiceSet();

            //evaluate valid dice
            findValidDice();

            // if no valid dice, execute effectOfNullDiceRoll and end executeCardEffect
            if(diceInGame.validSinglets.diceCount() == 0 && diceInGame.validTripletDiceSet1.diceCount() == 0){
                effectOfNullDiceRoll(turnResults);
                return;
            }

            diceSelection(turnResults);

            //check if tutto has been reached
            if (diceInGame.activeDiceSet.diceCount() == 0){
                tuttoBehaviour(turnResults);
            } else {
                decideRollDiceAgainIfNotTutto(turnResults);
            }
            diceInGame.resetValidDice();
        }
    }

    public abstract void calculatePointsForTutto(TurnResults turnResults);

    protected void decideRollDiceAgainIfNotTutto(TurnResults turnResults){
        if (rules.canPlayerDecideToRollDiceAgain()){
            turnResults.setRollDiceAgain(UserInputHandler.askYesOrNo("Do you want to roll the dice again (else the turn ends)?"));
        } else {
            turnResults.setRollDiceAgain(true);
        }
    }

    protected void tuttoBehaviour(TurnResults turnResults){
        System.out.println("TUTTO!");
        turnResults.achievedTutto();
        turnResults.setRollDiceAgain(false);

        // for fireworks card
        if (rules.isSameCardAfterTuttoAndNoTuttoRequired() ||
                (rules.isTwoTuttoRequired() && turnResults.getNrOfTutto() > 2)){
            diceInGame.resetDiceInGame();
            turnResults.setRollDiceAgain(true);
            return;
        }

        calculatePointsForTutto(turnResults);

        // ask player if s:he wants to draw another card
        if (!rules.isTwoTuttoRequired()){
            turnResults.setDrawAnotherCard(UserInputHandler.askYesOrNo("Do you want to draw another card?"));
        }
    }

    protected void findValidDice(){
        // clear variables
        diceInGame.validTripletDiceSet1.clear();
        diceInGame.validTripletDiceSet2.clear();
        diceInGame.validSinglets.clear();

        diceInGame.tempActiveDiceSetWithoutValidDice.clear();
        diceInGame.tempActiveDiceSetWithoutValidDice.addDiceSet(diceInGame.activeDiceSet);


        //first find triplets then "singlets"
        diceInGame.findValidTriplets();
        diceInGame.findValidSinglets(1);
        diceInGame.findValidSinglets(5);
    }

    protected void diceSelection(TurnResults turnResults){
        if (rules.canPlayerDecideToRollDiceAgain()){
            selectValidPlayerChoiceDice(turnResults);
        }  else {
            selectAllValidDice(turnResults);
        }

        System.out.println("In this turn you earned "
                + turnResults.getPoints() + " points so far.");
    }

    protected void selectAllValidDice(TurnResults turnResults){
        System.out.println("All valid dice are selected.");
        int points = diceInGame.selectAllValidDice();
        turnResults.increasePoints(points);
    }

    protected void selectValidPlayerChoiceDice(TurnResults turnResults){
        System.out.println("Please choose the dice you want to put aside.");
        int points = diceInGame.showAndSelectValidDice();
        turnResults.increasePoints(points);
    }

    protected void effectOfNullDiceRoll(TurnResults turnResults){
        System.out.println("Oops, there are no valid dice...");
        if (!rules.getKeepPointsAfterNullRoll()){
            turnResults.setPoints(0);
        }
        turnResults.setDrawAnotherCard(false);
    }

}
