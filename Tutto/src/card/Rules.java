package main.java.com.tuttogame.card;

public class Rules {
    private boolean sameCardAfterTuttoAndNoTuttoRequired;
    private boolean twoTuttoRequired;
    private boolean allowPlayerToChooseDice;
    private boolean canPlayerDecideToRollDiceAgain;
    private boolean keepPointsAfterNullRoll;

    public Rules() {
        sameCardAfterTuttoAndNoTuttoRequired = false; //true for fireworks
        twoTuttoRequired = false; //true for plus/minus and cloverleaf
        allowPlayerToChooseDice = true; //false for fireworks
        canPlayerDecideToRollDiceAgain = true; // false for fireworks
        keepPointsAfterNullRoll = false; //true for cloverleaf, fireworks
    }

    public boolean canPlayerDecideToRollDiceAgain() {
        return canPlayerDecideToRollDiceAgain;
    }

    public void setCanPlayerDecideToRollDiceAgain(boolean canPlayerDecideToRollDiceAgain) {
        this.canPlayerDecideToRollDiceAgain = canPlayerDecideToRollDiceAgain;
    }

    public boolean getKeepPointsAfterNullRoll() {
        return keepPointsAfterNullRoll;
    }

    public void setKeepPointsAfterNullRoll(boolean keepPointsAfterNullRoll) {
        this.keepPointsAfterNullRoll = keepPointsAfterNullRoll;
    }


    public boolean isSameCardAfterTuttoAndNoTuttoRequired() {
        return sameCardAfterTuttoAndNoTuttoRequired;
    }

    public boolean isTwoTuttoRequired() {
        return twoTuttoRequired;
    }

    public void setSameCardAfterTuttoAndNoTuttoRequired(boolean sameCardAfterTuttoAndNoTuttoRequired) {
        this.sameCardAfterTuttoAndNoTuttoRequired = sameCardAfterTuttoAndNoTuttoRequired;
    }

    public void setTwoTuttoRequired(boolean twoTuttoRequired) {
        this.twoTuttoRequired = twoTuttoRequired;
    }

    public boolean isPlayerAllowedToChooseDice() {
        return allowPlayerToChooseDice;
    }

    public void setAllowPlayerToChooseDice(boolean allowPlayerToChooseDice) {
        this.allowPlayerToChooseDice = allowPlayerToChooseDice;
    }
}
