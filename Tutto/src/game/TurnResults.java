package main.java.com.tuttogame.game;

public class TurnResults {
    private boolean drawAnotherCard;
    private boolean rollDiceAgain;
    private int points;
    private int nrOfTutto;

    public void TurnResults(){
        resetButKeepPoints();
    }
    public void reset(){
        resetButKeepPoints();
        points = 0;
    }

    public void resetButKeepPoints(){
        drawAnotherCard = false;
        rollDiceAgain = true;
        nrOfTutto = 0;
    }

    public boolean isRollDiceAgain() {
        return rollDiceAgain;
    }

    public void setRollDiceAgain(boolean rollDiceAgain) {
        this.rollDiceAgain = rollDiceAgain;
    }

    public void achievedTutto(){
        nrOfTutto++;
    }

    public void setDrawAnotherCard(boolean drawAnotherCard) {
        this.drawAnotherCard = drawAnotherCard;
    }

    public boolean drawAnotherCard() {
        return drawAnotherCard;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void increasePoints(int points) {
        this.points += points;
    }

    public int getPoints() {
        return points;
    }

    public int getNrOfTutto() {
        return nrOfTutto;
    }
}
