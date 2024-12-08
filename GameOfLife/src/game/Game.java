package game;

import exceptions.InvalidCoordinateException;
import grid.Grid;
import grid.Occupacy;
import grid.Position;
import player.Player;

import java.util.Scanner;

public class Game {
    private final Grid grid;
    private final Player player1;
    private final Player player2;
    private static Game uniqueInstance;

    /**
     * private constructor because of singleton pattern
     * game is initialized
     */
    private Game(){
        grid = new Grid(15, 15);

        System.out.println("Welcome to the Game of Life!");

        player1 = createPlayer(1, null);
        player2 = createPlayer(2, player1.getName());
    }

    /**
     * method to return the singleton game
     * @return game instance
     */
    public static Game getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new Game();
        }
        return uniqueInstance;
    }

    /*
    * method to create the Players
     */
    private Player createPlayer(int playerNumber, String otherPlayerName) {
        String playerName;
        do {
            playerName = receiveName(playerNumber);
        } while (playerName.equals(otherPlayerName));

        char playerSymbol;
        do {
            playerSymbol = receiveSymbol(playerName);
        } while (otherPlayerName != null && Occupacy.Player1.getSymbol() == playerSymbol);

        Occupacy occupacy = playerNumber == 1 ? Occupacy.Player1 : Occupacy.Player2;
        occupacy.setSymbol(playerSymbol);

        return new Player(playerName, occupacy);
    }

    /**
     * player1 and 2 take alternative turns until someone does not have any alive cells anymore
     */
    public void run(){
        // explain the rules
        explainRules();

        // Print the starting grid
        System.out.println("Starting Grid");
        grid.printGrid();

        Player activePlayer = player1;
        Player passivePlayer = player2;

        int turn = 1;

        // take turns until (at least) one player does not occupy any cells anymore
        do {
            // the users are informed about the remaining alive cells
            System.out.println(grid.nrAliveCellsPlayer1() + " cells alive for " + player1.getName());
            System.out.println(grid.nrAliveCellsPlayer2() + " cells alive for " + player2.getName());
            System.out.println("Turn " + turn + ": " + activePlayer.getName() + ", its your turn");

            //active player has to select an empty cell to occupy and kill a cell from the opponent
            selectEnemyCell(passivePlayer);
            selectNewCell(activePlayer);
            grid.printGrid();

            //advance a generation
            grid.advanceAGeneration();
            System.out.println("After one generation: ");
            grid.printGrid();

            //switch active and passive player
            Player temp = activePlayer;
            activePlayer = passivePlayer;
            passivePlayer = temp;

        }while (!isGameOver());

        // when the game is over print the winner
        System.out.println("game over");

        if (grid.nrAliveCellsPlayer1() == 0 && grid.nrAliveCellsPlayer2() == 0 ){
            System.out.println("It's a tie.");
        } else if (grid.nrAliveCellsPlayer2() == 0) {
            System.out.println("Player 1 won");
        } else {
            System.out.println("Player 2 won.");
        }
    }

    private void explainRules() {
        // explain the rules if necessary
        System.out.print("Do you know how to play the game? (y/n) ");
        Scanner sc = new Scanner(System.in);
        String userAnswer;
        do {
            userAnswer = sc.next();
            if (!userAnswer.equals("y") && !userAnswer.equals("n")) {
                System.out.print("Please enter 'y' or 'n': ");
            }
        } while (!userAnswer.equals("y") && !userAnswer.equals("n"));

        if (userAnswer.equals("y")) {
            System.out.println("Great! Let's get started!");
        } else {
            // Explain the rules
            System.out.println("\nNo worries, here's how you play...");
            System.out.println("=== Rules of the Game ===");
            System.out.println("1. The board consists of cells that can either be empty or occupied by a player.");
            System.out.println("2. The live cells come in two symbols, one for each player.");
            System.out.println("3. On each turn:");
            System.out.println("   - The active player must select an enemy cell to kill.");
            System.out.println("   - The active player must select an empty cell to occupy.");
            System.out.println("4. After each player's turn, the game advances by one generation.");
            System.out.println("   - Live cells survive if they have 2 or 3 alive neighbors.");
            System.out.println("   - Dead cells come to life if they have exactly 3 alive neighbors, taking on the majority color.");
            System.out.println("5. The game ends when one player has no more live cells left.");
            System.out.println("Let's get started!");
        }
    }

    /**
     * ask the user for a position input
     * valid input is e.g. "10,15" where the first value is the column and the second the row
     * @param message to be printed for the user
     * @return the coordinate specified by the user
     */
    private Position getUserPosition(String message){
        String userPositionString = "";
        while (!isValidUserPosition(userPositionString)){
            System.out.print(message);
            Scanner sc = new Scanner(System.in);
            userPositionString = sc.next();
        }
        Position userPosition;
        try {
            userPosition = new Position(userPositionString);
        } catch (InvalidCoordinateException e) {
            throw new RuntimeException(e);
        }
        return userPosition;
    }

    /**
     * Step 1: ask the user to select an empty cell
     * Step 2: change the selected cell to be occupied by the active player
     * @param activePlayer the player making the move
     */
    private void selectNewCell(Player activePlayer) {
        Position userPosition;

        // Prompt until the user selects an empty cell
        userPosition = getUserPosition("Please select an empty cell (format: 5,5 (column,row): ");
        while(grid.getOccupacy(userPosition) != Occupacy.EMPTY){
            userPosition = getUserPosition("This cell is not empty, try again: ");
        }

        // Change the occupancy of the selected cell to the active player's symbol
        grid.setOccupacy(userPosition, activePlayer.getOccupacy());
    }

    /**
     * Step 1: ask the user to select a cell occupied by the opponent
     * Step 2: change the selected cell to not be occupied anymore
     * @param passivePlayer the player whose cell will be targeted
     */
    private void selectEnemyCell(Player passivePlayer) {
        Position userPosition;

        // Prompt until the user selects a cell occupied by the passive player
        userPosition = getUserPosition("Please select a cell occupied by your opponent ("
                + passivePlayer.getOccupacy().getSymbol() + ") (format: 5,5 (column,row): ");
        while (grid.getOccupacy(userPosition) != passivePlayer.getOccupacy()){
            userPosition = getUserPosition("The cell you choose is not occupied by your oppontent ("
                    + passivePlayer.getOccupacy().getSymbol() + "), try again: ");
        }

        // Change the occupancy of the selected cell to empty
        grid.setOccupacy(userPosition, Occupacy.EMPTY);
    }

    /**
     * checks if the user position is of valid format and if the coordinate is within the grid
     * @param positionString
     * @return
     */
    private boolean isValidUserPosition(String positionString){
        Position position;
        try {
            position = new Position(positionString);
        } catch (InvalidCoordinateException e) {
            return false;
        }
        // check if position is within grid
        return position.getColumnId() >= 0
                && position.getColumnId() <= grid.getColumns() - 1
                && position.getRowId() >= 0
                && position.getRowId() <= grid.getRows() - 1;
    }

    /**
     * checks if there are live cells left
     * @return
     */
    private boolean isGameOver(){
        return grid.nrAliveCellsPlayer1() == 0 || grid.nrAliveCellsPlayer2() == 0;
    }

    /**
     * ask the user for the name
     * @param playerNr
     * @return
     */
    private String receiveName(int playerNr) {
        System.out.print("Player " + playerNr + ": Please enter your name: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * ask the user for the symbol
     * @param name
     * @return
     */
    private char receiveSymbol(String name){
        System.out.print(name + ": please enter a symbol that you want to use: ");
        Scanner sc = new Scanner(System.in);
        return sc.next().charAt(0);
    }
}