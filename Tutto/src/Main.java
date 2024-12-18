package main.java.com.tuttogame;
import main.java.com.tuttogame.game.Game;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n**********************************");
        System.out.println("*** Welcome to the Tutto game! ***");
        System.out.println("**********************************\n");

        Game tutto = new Game();
        tutto.testSetUP();
        tutto.run();
        tutto.awardCeremony();
    }
}