package main.java.com.tuttogame.game;

import main.java.com.tuttogame.card.AbstractCard;
import main.java.com.tuttogame.card.CloverleafCard;
import main.java.com.tuttogame.card.PlusMinusCard;
import main.java.com.tuttogame.card.StopCard;
import main.java.com.tuttogame.deck.Deck;
import main.java.com.tuttogame.player.Player;
import main.java.com.tuttogame.player.PlayerClub;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Player playingPlayer;
    private final PlayerClub playerClub;
    private int numberOfPlayers;
    private int pointsToWin;
    private final Deck deck;
    private AbstractCard currentCard;
    public Game(){
        playerClub = new PlayerClub();

        deck = new Deck(1,5,10,5,5,
                5,5,5,5,5,5);

    }

    public void setUp(){
        pointsToWin = askUsersPointsToWin();
        numberOfPlayers = askUsersNumberOfPlayers();

        for (int i = 1; i <= numberOfPlayers; i++) {
            Player player = new Player(askUserName(i));
            playerClub.addPlayer(player);
        }
    }

    public void testSetUp(){
        pointsToWin = 1200;
        numberOfPlayers = 2;
        Player player1 = new Player("testPlayer1");
        playerClub.addPlayer(player1);
        Player player2 = new Player("testPlayer2");
        playerClub.addPlayer(player2);
    }

    private int askUsersPointsToWin() {
        System.out.print("Please enter the necessary points to win (e.g. 6000): ");
        Scanner sc = new Scanner(System.in);
        while(true) {
            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                if (value>0) {
                    return value;
                }
            }
            System.out.print("Please enter a positive integer: ");
        }
    }

    private int askUsersNumberOfPlayers() {
        System.out.print("Please enter the number of players (between 2 and 4 are allowed): ");
        Scanner sc = new Scanner(System.in);
        while(true) {
            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                if (value>=2 && value<=4) {
                    return value;
                }
            }
            System.out.print("Please enter 2,3 or 4: ");
        }
    }

    private String askUserName(int i) {
        System.out.print("Please enter the name of player " + (i) + ": ");
        Scanner sc = new Scanner(System.in);

        while (true) {
            String name = sc.nextLine();
            if (!playerClub.existsPlayerName(name)) {
                return name;
            }
            System.out.print("Please choose a different name: ");
        }
    }

    public void run(){
        int turnCount = 0;
        TurnResults turnResults = new TurnResults();
        while (!isGameOver(turnCount, turnResults)){
            turnResults.reset();
            pickNextPlayer(turnCount);
            turn(turnResults);
            turnCount++;
        }
    }

    private void pickNextPlayer(int turnCount){
        // decide who is playing this turn
        playingPlayer = playerClub.getPlayer(turnCount % numberOfPlayers);
        System.out.println("It's " + playingPlayer.getName() + "'s turn.");
        if (playingPlayer.getPoints() != 0) {
            System.out.println("You currently have "
                    + playingPlayer.getPoints() + " points.");
        }

    }

    private void promptUserToDrawCard() {
        System.out.print("Press Enter to draw a card...");
        Scanner sc = new Scanner(System.in);
        sc.nextLine(); // Wait for the user to press Enter
    }


    private void turn(TurnResults turnResults){
        //draw a card and execute card effect
        do {
            turnResults.resetButKeepPoints();
            promptUserToDrawCard();
            currentCard = deck.drawCard();
            currentCard.showCard();
            currentCard.executeCardEffect(turnResults);
        } while (turnResults.drawAnotherCard());

        distributePoints(turnResults);

        //display the earned points for the player
        if (turnResults.getPoints() == 0){
            if (!(currentCard instanceof StopCard)){
                System.out.println("Unfortunately, you have not earned any points in this turn.");
            }
        } else {
            System.out.println(playingPlayer.getName() + ", your turn has ended. You earned " +
                    turnResults.getPoints() + " in this turn, your total score is " + playingPlayer.getPoints() + ".");
        }
    }

    private boolean isGameOver(int turnCount, TurnResults turnResults){
        if (playerClub.hasAPlayerReachedPoints(pointsToWin) && havePlayerSameNumberOfTurns(turnCount)){
            return true;
        } else if (currentCard instanceof CloverleafCard && turnResults.getNrOfTutto() == 2) {
            return true;
        }
        return false;
    }
    private boolean havePlayerSameNumberOfTurns(int turnCount){
        return turnCount % numberOfPlayers == 0;
    }

     private void distributePoints(TurnResults turnResults){
         playingPlayer.awardPoints(turnResults.getPoints());

         if (currentCard instanceof PlusMinusCard && turnResults.getNrOfTutto() == 2) {
             ArrayList<Player> winningPlayers = playerClub.getWinningPlayers();
             for (Player winningPlayer : winningPlayers) {
                 if (winningPlayer != playingPlayer) {
                     winningPlayer.deductPoints(1000);
                 }
             }
         }
     }
    
    public void awardCeremony(){
    }
}
