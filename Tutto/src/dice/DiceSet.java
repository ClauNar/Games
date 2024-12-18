package main.java.com.tuttogame.dice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DiceSet implements Iterator<Die> {
    ArrayList<Die> dieSet;
    int position;

    public DiceSet(int size){
        dieSet = new ArrayList<>();
        for (int i = 0; i < size; i++){
            Die die = new Die();
            dieSet.add(die);
        }
        position = 0;
    }

    public void sortDice(){
        Collections.sort(dieSet);
    }

    public void addDice(Die die){
        dieSet.add(die);
    }

    public Die getDice(int i){
        return dieSet.get(i);
    }

    public void throwDiceSet(){
        for (Die die : dieSet){
            die.dieSideUp = die.throwDice();
        }
        sortDice();
        display();
    }

    public int countOccurenceDice(Die dieToCheck){
        int occurenceCount = 0;
        for (Die die : dieSet){
            if (die.getNumber() == dieToCheck.getNumber()){
                occurenceCount++;
            }
        }
        return occurenceCount;
    }

    public int diceCount(){
        return dieSet.size();
    }

    public void clear(){
        while (dieSet.size()>0){
            dieSet.remove(0);
        }
    }

    public Die removeDice(int number){
        for (int i = 0; i < dieSet.size() ; i++) {
            if (dieSet.get(i).getNumber()== number) {
                return dieSet.remove(i);
            }
        }
        return null;
    }

    public void addDiceSet(DiceSet diceSet){
        for(int i=0;i<diceSet.diceCount();i++){
            addDice(diceSet.getDice(i));
        }
    }

    public void removeDiceSet(DiceSet diceSetRemove){
        for(int i=0;i<diceSetRemove.diceCount();i++){
            removeDice(diceSetRemove.getDice(i).getNumber());
        }
    }

    public void display(){
        String println0 = "";
        String println1 = "";
        String println2 = "";
        String println3 = "";
        String println4 = "";

        for (Die die : dieSet){
            println0 += "   " + die.dieSideUp.line1;
            println1 += "   " + die.dieSideUp.line2;
            println2 += "   " + die.dieSideUp.line3;
            println3 += "   " + die.dieSideUp.line4;
            println4 += "   " + die.dieSideUp.line5;
        }
        System.out.println(println0);
        System.out.println(println1);
        System.out.println(println2);
        System.out.println(println3);
        System.out.println(println4);
    }

    @Override
    public boolean hasNext() {
        if (position >= dieSet.size() || dieSet.get(position) == null){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Die next() {
        Die tempDie = dieSet.get(position);
        position++;
        return tempDie;
    }
}
