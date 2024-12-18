package main.java.com.tuttogame.deck;

import main.java.com.tuttogame.card.*;

public class Deck {
    private final CardStack cardStack;
    private final int nrCloverleafCards;
    private final int nrFireworksCards;
    private final int nrStopCards;
    private final int nrStraightCards;
    private final int nrPulsMinusCards;
    private final int nrTimesTwoCards;
    private final int nrBonus200Cards;
    private final int nrBonus300Cards;
    private final int nrBonus400Cards;
    private final int nrBonus500Cards;
    private final int nrBonus600Cards;

    public Deck(int nrCloverleafCards, int nrFireworksCards, int nrStopCards, int nrStraightCards, int nrPulsMinusCards,
                int nrTimesTwoCards, int nrBonus200Cards, int nrBonus300Cards, int nrBonus400Cards, int nrBonus500Cards,
                int nrBonus600Cards){

        cardStack = new CardStack();

        this.nrCloverleafCards = nrCloverleafCards;
        this.nrFireworksCards = nrFireworksCards;
        this.nrStopCards = nrStopCards;
        this.nrStraightCards = nrStraightCards;
        this.nrPulsMinusCards = nrPulsMinusCards;
        this.nrTimesTwoCards = nrTimesTwoCards;
        this.nrBonus200Cards = nrBonus200Cards;
        this.nrBonus300Cards = nrBonus300Cards;
        this.nrBonus400Cards = nrBonus400Cards;
        this.nrBonus500Cards = nrBonus500Cards;
        this.nrBonus600Cards = nrBonus600Cards;

        fillDeck();
    }
    private void fillDeck(){
        // add cloverleaf cards
        for (int i = 0; i < nrCloverleafCards; i++){
            this.cardStack.push(new CloverleafCard());
        }

        // add firework cards
        for (int i = 0; i < nrFireworksCards; i++){
            this.cardStack.push(new FireworksCard());
        }

        // add stop cards
        for (int i = 0; i < nrStopCards; i++){
            this.cardStack.push(new StopCard());
        }

        // add straight cards
        for (int i = 0; i < nrStraightCards; i++){
            this.cardStack.push(new StraightCard());
        }

        // add plus minus cards
        for (int i = 0; i < nrPulsMinusCards; i++){
            this.cardStack.push(new PlusMinusCard());
        }

        // add x2 cards
        for (int i = 0; i < nrTimesTwoCards; i++){
            this.cardStack.push(new TimesTwoCard());
        }

        // add bonus 200 cards
        for (int i = 0; i < nrBonus200Cards; i++){
            this.cardStack.push(new BonusCard(200));
        }

        // add bonus 300 cards
        for (int i = 0; i < nrBonus300Cards; i++){
            this.cardStack.push(new BonusCard(300));
        }

        // add bonus 400 cards
        for (int i = 0; i < nrBonus400Cards; i++){
            this.cardStack.push(new BonusCard(400));
        }

        // add bonus 500 cards
        for (int i = 0; i < nrBonus500Cards; i++){
            this.cardStack.push(new BonusCard(500));
        }

        // add bonus 600 cards
        for (int i = 0; i < nrBonus600Cards; i++){
            this.cardStack.push(new BonusCard(600));
        }
        cardStack.shuffleCards();
    }

    public AbstractCard drawCard(){
        if (cardStack.isEmpty()){
            fillDeck();
        }

        return cardStack.pop();
    }
}
