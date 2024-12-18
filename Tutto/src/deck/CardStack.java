package main.java.com.tuttogame.deck;

import main.java.com.tuttogame.card.AbstractCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CardStack implements Iterable<AbstractCard> {
    private final List<AbstractCard> cards;

    public CardStack() {
        cards = new ArrayList<>();
    }

    /**
     * Pushes card onto the stack.
     * @param card The card to push.
     * @pre card != null;
     * @pre !cards.contains(card)
     */
    public void push(AbstractCard card) {
        assert card != null && !cards.contains(card);
        cards.add(card);
    }

    /**
     * Removes the card on top of the stack and returns it.
     * @return The card on top of the stack.
     * @pre !isEmpty()
     */
    public AbstractCard pop() {
        assert !isEmpty();
        return cards.remove(cards.size()-1);
    }

    /**
     * @return True if and only if the stack has no cards in it.
     */
    public boolean isEmpty() {
        return cards.size() == 0;
    }

    public void shuffleCards(){
        Collections.shuffle(cards);
    }

    @Override
    public Iterator<AbstractCard> iterator() {
        return cards.iterator();
    }
}
