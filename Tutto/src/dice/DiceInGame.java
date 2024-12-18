package main.java.com.tuttogame.dice;

import java.util.Scanner;
import java.util.regex.*;

public class DiceInGame {
    public DiceSet activeDiceSet;
    public DiceSet tempActiveDiceSetWithoutValidDice;
    public DiceSet validTripletDiceSet1;
    public DiceSet validTripletDiceSet2;
    public DiceSet validSinglets;
    public DiceSet savedDice;

    public DiceInGame(){
        activeDiceSet = new DiceSet(6);
        tempActiveDiceSetWithoutValidDice = new DiceSet(0);
        validTripletDiceSet1 = new DiceSet(0);
        validTripletDiceSet2 = new DiceSet(0);
        validSinglets = new DiceSet(0);
        savedDice = new DiceSet(0);
    }

    public void resetDiceInGame(){
        activeDiceSet = new DiceSet(6);
        validTripletDiceSet1.clear();
        validTripletDiceSet2.clear();
        validSinglets.clear();
        savedDice.clear();
    }

    public void resetValidDice(){
        validTripletDiceSet1.clear();
        validTripletDiceSet2.clear();
        validSinglets.clear();
    }

    public void findValidTriplets() {

        int tripletsFound = 0;

        tempActiveDiceSetWithoutValidDice.position = 0;
        while (tempActiveDiceSetWithoutValidDice.hasNext() && tripletsFound < 2) {
            Die die = tempActiveDiceSetWithoutValidDice.next();
            if (tempActiveDiceSetWithoutValidDice.countOccurenceDice(die) >= 3) {
                for (int i = 0; i < 3; i++) {
                    if (tripletsFound == 0) {
                        validTripletDiceSet1.addDice(die);
                    } else {
                        validTripletDiceSet2.addDice(die);
                    }
                    tempActiveDiceSetWithoutValidDice.removeDice(die.getDiceSideUp().getValue());
                }
                tripletsFound++;
            }
        }
    }

    public void findValidSinglets(int number){
        tempActiveDiceSetWithoutValidDice.position = 0;
        while (tempActiveDiceSetWithoutValidDice.hasNext()){
            Die die = tempActiveDiceSetWithoutValidDice.next();
            if (die.getDiceSideUp().getValue() == number){
                validSinglets.addDice(die);
            }
        }
    }

    public void findValidDiceForStraight() {
        validSinglets.clear(); // Clear any previous state
        activeDiceSet.position = 0;

        // Check all dice in the activeDiceSet
        while (activeDiceSet.hasNext()) {
            Die die = activeDiceSet.next();
            int value = die.getDiceSideUp().getValue();

            // Check if the die is already saved
            boolean isAlreadySaved = false;
            for (int i = 0; i < savedDice.diceCount(); i++) {
                if (savedDice.getDice(i).getDiceSideUp().getValue() == value) {
                    isAlreadySaved = true;
                    break;
                }
            }

            // Check if the die has already been selected in this turn
            boolean isAlreadySelected = false;
            for (int i = 0; i < validSinglets.diceCount(); i++) {
                if (validSinglets.getDice(i).getDiceSideUp().getValue() == value) {
                    isAlreadySelected = true;
                    break;
                }
            }

            // If the die is not already saved or selected, it is valid
            if (!isAlreadySaved && !isAlreadySelected) {
                validSinglets.addDice(die); // Add to validSinglets to reuse this structure
            }
        }

        System.out.println("Valid dice for straight: ");
        for (int i = 0; i < validSinglets.diceCount(); i++) {
            System.out.print(validSinglets.getDice(i).getDiceSideUp().getValue() + " ");
        }
        System.out.println();
    }


    public int selectAllValidDice(){
        int points = 0;

        //check if triplet
        if (validTripletDiceSet1.diceCount() != 0){
            points += validTripletDiceSet1.getDice(0).getDiceSideUp().getTripletPoints();
            activeDiceSet.removeDiceSet(validTripletDiceSet1);
            savedDice.addDiceSet(validTripletDiceSet1);
        }
        if (validTripletDiceSet2.diceCount() != 0){
            points += validTripletDiceSet2.getDice(0).getDiceSideUp().getTripletPoints();
            activeDiceSet.removeDiceSet(validTripletDiceSet2);
            savedDice.addDiceSet(validTripletDiceSet2);
        }

        //iterate through singlets
        for (int i = 0; i < validSinglets.diceCount(); i++){
            Die tempDie = validSinglets.getDice(i);
            points += tempDie.getDiceSideUp().getSinglePoints();
            activeDiceSet.removeDice(tempDie.getNumber());
            savedDice.addDice(tempDie);
        }

        resetValidDice();

        return points;
    }

    public int showAndSelectValidDice() {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Display valid options to the user
        System.out.println("Valid Dice to Select:");
        if (validTripletDiceSet1.diceCount() > 0) {
            System.out.println("Triplet: " + "3x" + validTripletDiceSet1.getDice(0).getDiceSideUp().getValue());
        }
        if (validTripletDiceSet2.diceCount() > 0) {
            System.out.println("Triplet: " + "3x" + validTripletDiceSet2.getDice(0).getDiceSideUp().getValue());
        }
        for (int i = 0; i < validSinglets.diceCount(); i++) {
            System.out.println("Singlet: " + validSinglets.getDice(i).getDiceSideUp().getValue());
        }

        // Step 2: Input loop for user selection
        while (true) {
            System.out.print("Enter your selection (e.g., '1, 3x2' for singlet 1 and triplet of 2): ");
            String input = scanner.nextLine();

            // Step 3: Parse and validate user input
            if (isValidSelection(input)) {
                int points = processUserSelection(input);
                System.out.println("Selection processed successfully!");
                return points;
            } else {
                System.out.println("Invalid selection. Please try again!");
            }
        }
    }

    // Helper method to validate user input
    private boolean isValidSelection(String input) {
        Pattern pattern = Pattern.compile("^(\\d|\\d+x\\d)(,\\s*(\\d|\\d+x\\d))*$"); // e.g., 1, 3x2
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) return false;

        String[] parts = input.split(",");
        for (String part : parts) {
            part = part.trim();

            if (part.contains("x")) { // Triplet selection
                String[] triplet = part.split("x");
                int count = Integer.parseInt(triplet[0]);
                int value = Integer.parseInt(triplet[1]);
                if (count == 3 && !isValidTriplet(value)) return false;
            } else { // Singlet selection
                int value = Integer.parseInt(part);
                if (!isValidSinglet(value)) return false;
            }
        }
        return true;
    }

    // Helper method to check if a value is a valid triplet
    private boolean isValidTriplet(int value) {
        return (validTripletDiceSet1.diceCount() > 0 && validTripletDiceSet1.getDice(0).getDiceSideUp().getValue() == value) ||
                (validTripletDiceSet2.diceCount() > 0 && validTripletDiceSet2.getDice(0).getDiceSideUp().getValue() == value);
    }

    // Helper method to check if a value is a valid singlet
    private boolean isValidSinglet(int value) {
        for (int i = 0; i < validSinglets.diceCount(); i++) {
            if (validSinglets.getDice(i).getDiceSideUp().getValue() == value) {
                return true;
            }
        }
        return false;
    }

    // Process the user selection
    private int processUserSelection(String input) {
        int points = 0;

        String[] parts = input.split(",");
        for (String part : parts) {
            part = part.trim();

            if (part.contains("x")) { // Process triplet selection
                String[] triplet = part.split("x");
                int value = Integer.parseInt(triplet[1]);
                if (validTripletDiceSet1.diceCount() > 0 && validTripletDiceSet1.getDice(0).getDiceSideUp().getValue() == value) {
                    points += validTripletDiceSet1.getDice(0).getDiceSideUp().getTripletPoints();
                    savedDice.addDiceSet(validTripletDiceSet1);
                    activeDiceSet.removeDiceSet(validTripletDiceSet1);
                } else if (validTripletDiceSet2.diceCount() > 0 && validTripletDiceSet2.getDice(0).getDiceSideUp().getValue() == value) {
                    points += validTripletDiceSet2.getDice(0).getDiceSideUp().getTripletPoints();
                    savedDice.addDiceSet(validTripletDiceSet2);
                    activeDiceSet.removeDiceSet(validTripletDiceSet2);
                }
            } else { // Process singlet selection
                int value = Integer.parseInt(part);
                for (int i = 0; i < validSinglets.diceCount(); i++) {
                    if (validSinglets.getDice(i).getDiceSideUp().getValue() == value) {
                        Die tempDie = validSinglets.getDice(i);
                        points += tempDie.getDiceSideUp().getSinglePoints();
                        savedDice.addDice(tempDie);
                        activeDiceSet.removeDice(tempDie.getNumber());
                        validSinglets.removeDice(value);
                        break;
                    }
                }
            }
        }
        return points;
    }

    public int selectAllValidDiceForStraight() {
        // Move all valid dice from validSinglets to savedDice
        savedDice.addDiceSet(validSinglets);

        // Remove those dice from activeDiceSet
        activeDiceSet.removeDiceSet(validSinglets);

        System.out.println("All valid dice for straight have been saved.");
        System.out.println("Remaining active dice: " + activeDiceSet.diceCount());
        System.out.println("Saved dice: " + savedDice.diceCount());

        return -1;
    }


}
