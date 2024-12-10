import java.util.Scanner;

public class UserPlayer extends Player{

	public UserPlayer(){
		super();
	}

	/**
	 * repeatedly ask user to place ship until ship is placed correctly
	 * @param shipType of ship to place
	 * @return String coordinates where the ship is going to be placed
	 */
	@Override
	protected String chooseBlocksToPlaceShip(ShipType shipType){
		oceanGrid.displayGrid();
		String userCoordinates;
		System.out.print("Please enter the position where you want to place a " + shipType.getName()
				+ " of length " + shipType.getLength() +": ");

		do {
			Scanner sc = new Scanner(System.in);
			userCoordinates = sc.next();
		}while (!(validUserCoordinates(userCoordinates)
				&& areUserCoordinatesWithinGrid(userCoordinates)
				&& isShipPlacedHorizontallyOrVertically(userCoordinates)
				&& hasShipCorrectLength(userCoordinates, shipType.getLength())
				&& isNotOverlapping(userCoordinates)));

		userCoordinates = switchCoordinatesIfNecessary(userCoordinates);

		return userCoordinates;
	}

	private String messageShipPlacement(ShipType shipType) {
		return "Please enter the position where you want to place a " + shipType.getName()
				+ " of length " + shipType.getLength() +": ";
	}

	/**
	 * checks if the coordinates is of valid format, e.g. "A2,A5"
	 * @return true if in correct format, else false and print message
	 */
	private boolean validUserCoordinates(String coordinates){
		if (isValidCoordinatesFormat(coordinates)){
			return true;
		} else {
			System.out.print("Remember that the format has to be some something like 'A2,A5'. Try again: ");
			return false;
		}
	}

	/**
	 * checks if the coordinates are within the grid
	 * @pre isValidCoordinatesFormat(coordinates) == true
	 * @return true if within the grid, else false and print message
	 */
	private boolean areUserCoordinatesWithinGrid(String coordinates){
		if (areCoordinatesWithinGrid(coordinates)){
			return true;
		} else {
			System.out.print("You can only place your ships within the grid. Try again: ");
			return false;
		}
	}

	/**
	 * checks if the ship with coordinates is placed horizontally or vertically
	 * @pre isValidCoordinatesFormat(coordinates) == true
	 * @return true if ship is placed horizontally or vertically, else false and print message
	 */
	private boolean isShipPlacedHorizontallyOrVertically(String coordinates){
		//check if ship is placed horizontally or vertically
		if (!(isPlacedHorizontally(coordinates) || isPlacedVertically(coordinates))){
			System.out.print("You have to place the ships either horizontally or vertically. Try again: ");
			return false;
		}
		return true;
	}

	/**
	 * checks if the ship with coordinates has length
	 * @pre isValidCoordinatesFormat(coordinates) == true
	 * @return true if ship is of specified length, else false and print message
	 */
	private boolean hasShipCorrectLength(String coordinates, int length){
		//check if length of ship is correct
		if (length != calculateLength(coordinates)) {
			System.out.print("You have to place a ship that matches the specified length, try again: ");
			return false;
		}
		return true;
	}

	/**
	 * checks if there is a ship on the coordinates already
	 * @pre isValidCoordinatesFormat(coordinates) == true
	 * @return true if ship there is no ship, else false and print message
	 */
	private boolean isNotOverlapping(String coordinates){
		coordinates = switchCoordinatesIfNecessary(coordinates);
		if (isOverlapping(coordinates)){
			System.out.print("You can't place overlapping ships. Try again: ");
			return false;
		}
		return true;
	}

	/**
	 * ask user repeatedly to enter a coordinate, until valid and not attacked yet
	 */
	@Override
	protected String chooseBlockToAttack() {
		String userCoordinate;
		System.out.print("Please enter the coordinate you want to attack (e.g. 'A2'): ");
		do {
			Scanner sc = new Scanner(System.in);
			userCoordinate = sc.next();
		} while (!validUserCoordinate(userCoordinate)
				|| !isUserCoordinateWithinGrid(userCoordinate)
				|| !wasNotAttackedYet(userCoordinate));
		return userCoordinate;
	}

	/**
	 * checks if the coordinate is of valid format, e.g. "A2"
	 * @return true if in correct format, else false and print message
	 */
	private boolean validUserCoordinate(String coordinate){
		if (isValidCoordinateFormat(coordinate)){
			return true;
		} else {
			System.out.print("Remember that the format has to be some something like 'A2'. Try again: ");
			return false;
		}
	}

	/**
	 * checks if the coordinate are within the grid
	 * @pre isValidCoordinateFormat(coordinate) == true
	 * @return true if within the grid, else false and print message
	 */
	private boolean isUserCoordinateWithinGrid(String coordinate){
		if (isCoordinateWithinGrid(coordinate)){
			return true;
		} else {
			System.out.print("You can only place your attack within the grid. Try again: ");
			return false;
		}
	}

	/**
	 * check if the coordinate was already attacked
	 * @pre isCoordinateWithinGrid(coordinate) == true
	 * @return true if not attacked yet, else false and print message
	 */
	private boolean wasNotAttackedYet(String coordinate){
		if (targetGrid.checkStatusOfBlock(coordinate) == null) {
			return true;
		} else {
			System.out.print("You already attacked this coordinate, try again: ");
			return false;
		}
	}

}