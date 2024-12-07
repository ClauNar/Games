import java.util.Random;

public class ComputerPlayer extends Player{
    private final Random random;
	public ComputerPlayer(){
        super();
        random=new Random();
    }
    /**
     * computer generates random ship placements until there is no overlap with already placed ships:
     *   - choose randomly if ship should be placed vertically or horizontally
     * @return String coordinate where the ship is going to be placed
     */
    @Override
    protected String chooseBlocksToPlaceShip(ShipType shipType){
        String coordinates;
        String startCoordinate;
        String endCoordinate;
        do {
            if (random.nextBoolean()){ //place ship vertically
                //select random letter for start coordinate (within bounds of grid):
                char randomLetter = randomLetter(oceanGrid.getHorizontalLength());
                //select random number for start coordinate, such that the ship can be places within the grid
                int randomNumber = randomNumber(oceanGrid.getVerticalLength() - shipType.getLength());
                startCoordinate = randomLetter+Integer.toString(randomNumber);
                endCoordinate = randomLetter+Integer.toString(randomNumber + shipType.getLength() -1);
            }
            else { //place ship horizontally
                //select random letter for start coordinate, such that the ship can be placed within the grid:
                char randomLetter = randomLetter(oceanGrid.getHorizontalLength() - shipType.getLength());
                //select random number for start coordinate (within bounds of grid)
                int randomNumber = randomNumber(oceanGrid.getVerticalLength());
                char lastLetter = (char) ((int) randomLetter + shipType.getLength() -1);
                startCoordinate = randomLetter+Integer.toString(randomNumber);
                endCoordinate = lastLetter+Integer.toString(randomNumber);
            }
            coordinates = startCoordinate + "," + endCoordinate;
        }while(isOverlapping(coordinates));
        return coordinates;
    }

    /**
     * generate random letter of grid
     */
    private char randomLetter(int bound){
        return (char) (random.nextInt(bound - 1) + (int) 'A');
    }

    /**
     * generate random number of grid
     */
    private int randomNumber(int bound){
        return random.nextInt(bound - 1);
    }

    /**
     * generate a random coordinate for attack
     * if block is already been attacked, then new coordinateOfAttack is generated
     * @return String coordinate that is going to be attacked
     */
    @Override
    protected String chooseBlockToAttack(){
        String coordinateOfAttack;
        do{
            char randomLetter =  randomLetter(oceanGrid.getHorizontalLength());
            int randomNumber = randomNumber(oceanGrid.getVerticalLength());
            coordinateOfAttack = randomLetter+Integer.toString(randomNumber);
        }while (targetGrid.checkStatusOfBlock(coordinateOfAttack) != null);

        return coordinateOfAttack;
    }
}