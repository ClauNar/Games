import java.util.Random;
import java.util.Scanner;

public class Game {
    private final UserPlayer userPlayer;
    private final ComputerPlayer computerPlayer;
    private final Random random;

    public Game(){
        userPlayer  = new UserPlayer();
        computerPlayer = new ComputerPlayer();
        random = new Random();
    }

    public void run()
    {
        System.out.println("Welcome to the BattleShip game!");

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
            System.out.println("No worries, here's how you play...");
            System.out.println("First place the ships: enter the coordinates you want to place your ships in the requested format.");
            System.out.println("The ships you will use in the game are:");
            for (ShipType ship : ShipType.values()) {
                System.out.println("- " + ship.getCount() + " x " + ship.getName() + " " + ship.getBlockSymbol() + " (" + ship.getLength() + " blocks)");
            }
            System.out.println("Then the actual game starts: attack the grid of the computer.\n"
                    + "When a ship is hit, you will see an 'x' on the coordinate attacked; otherwise, an 'o' for hitting the ocean.\n"
                    + "Once the ship is sunk, the abbreviation of the ship is displayed.");
        }

        // players have to place ships:
        System.out.println("You can place you ships now. Please write in this format: COORDINATE,COORDINATE , e.g. 'A2,A5'.");
        userPlayer.deployShips();
        computerPlayer.deployShips();

        //select randomly which player is starting the game
        Player shootingPlayer;
        Player receivingPlayer;

        userPlayer.displayBoard();

        if (random.nextBoolean()){
            System.out.println("You can begin the game.");
            shootingPlayer = userPlayer;
            receivingPlayer = computerPlayer;
        } else {
            System.out.println("The computer will start the game.");
            // wait for 2 seconds, to make it look like the computer is thinking a bit
            try {
                Thread.sleep(2000);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Now it's your turn.");
            shootingPlayer = computerPlayer;
            receivingPlayer = userPlayer;
        }

        boolean isGameOver = false;
        while (!isGameOver){ //roles are switches in the end of the loop
            shootingPlayer.attack(receivingPlayer);

            //print the board, if the computer was the shooting player
            if (shootingPlayer instanceof ComputerPlayer){
                userPlayer.displayBoard();
            }

            if (receivingPlayer.areAllShipsSunk()){
                System.out.println("Game over!");
                isGameOver = true;
            } else {
                //swap shootingPlayer and receivingPlayer
                Player temp = shootingPlayer;
                shootingPlayer = receivingPlayer;
                receivingPlayer = temp;
            }
        }

        // end of game
        // print who won and if computer won, print its board
        if (shootingPlayer instanceof UserPlayer){
            System.out.println("Congrats! You won the game :D");
        } else {
            System.out.println("Oh no, you lost! :/");
            System.out.println("FYI: This is the board of the computer: ");
            computerPlayer.displayBoard();
        }
    }
}
