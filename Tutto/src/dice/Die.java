package main.java.com.tuttogame.dice;

import java.util.Iterator;
import java.util.Random;

public class Die implements Comparable<Die>, Iterable<Die> {
    DieSide dieSideUp;

    public Die() {
        dieSideUp = throwDice();
    }

    public DieSide getDiceSideUp() {
        return dieSideUp;
    }

    public DieSide throwDice(){
        return DieSide.values()[(int)(Math.random()* DieSide.values().length)];
    }

    public int getNumber() {
        return dieSideUp.getValue();
    }

    @Override
    public int compareTo(Die that) {
        return this.dieSideUp.getValue() - that.dieSideUp.getValue();
    }

    @Override
    public Iterator<Die> iterator() {
        return null;
    }
    @Override
    public boolean equals(Object other){
        if (this == other){
            return true;
        }
        if (!(other instanceof Die)){
            return false;
        }
        Die die = (Die) other;
        return dieSideUp.equals(die.dieSideUp);
    }
}

