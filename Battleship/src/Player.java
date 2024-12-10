import java.util.ArrayList;
import static java.lang.Math.abs;

public abstract class Player{
	protected Grid targetGrid;
	protected Grid oceanGrid;
	protected ArrayList<Ship> fleet;
	protected String coordinateRegex;
	protected String coordinateDelimiterSymbol;

	public Player(){
		this.targetGrid = new Grid();
		this.oceanGrid = new Grid();
		this.fleet = new ArrayList<>();
		this.coordinateRegex = "[A-Z][0-9]+";
		this.coordinateDelimiterSymbol = ",";
	}

	/**
	 * Player has to decide where to attack
	 * @return String of block to attack
	 */
	protected abstract String chooseBlockToAttack();

	/**
	 * Player has to decide where to place a ship
	 * @param shipType of ship to place
	 * @return String of coordinates to attack
	 */
	protected abstract String chooseBlocksToPlaceShip(ShipType shipType);

	/**
	 * deploy ships:
	 * - iterate over all ShipTypes and ask the Player to place as many ships as
	 *   specified for ShipType (ShipType.getCount())
	 * - add the ships to the fleet
	 * - for each ship, update the coordinateList
	 */
	public void deployShips(){
		for (ShipType shipType: ShipType.values()){
			for (int i = 0; i < shipType.getCount(); i++){
				String shipCoordinates;

				shipCoordinates = chooseBlocksToPlaceShip(shipType);
				ArrayList<String> shipCoordinateList = getCoordinateList(shipCoordinates);

				//add ship to fleet
				Ship ship = new Ship(shipType);
				fleet.add(ship);

				for (String shipCoordinate : shipCoordinateList){
					ship.addCoordinatesToCoordinateList(shipCoordinate);
					oceanGrid.updateStatusOfBlock(shipCoordinate,shipType.getBlockSymbol());
				}
			}
		}
	}

	/**
	 * Player attacks receiving Player:
	 * - ask coordinateOfAttack from Player
	 * - choose the BlockSymbol that will be placed in the targetGrid of the shootingPlayer
	 *   and the oceanGrid of the receivingPlayer:
	 *    - if there is no ship placed on the oceanGrid of the receivingPlayer, put 'o'
	 *    - if there is a ship:
	 *        - decrease the number of blocks hidden for the ship
	 *        - if the ship is sunk now:
	 *            - update all coordinated to the BlockSymbol of the ship in
	 *              targetGrid of Player and oceanGrid of receivingPlayer
	 *        - else: put an 'x' in the targetGrid of the Player and the oceanGrid of the receivingPlayer
	 * @pre receivingPlayer != null
	 * @param receivingPlayer player that is attacked
	 */
	public void attack(Player receivingPlayer) {
		String coordinateOfAttack;

		coordinateOfAttack = chooseBlockToAttack();

		if (receivingPlayer.oceanGrid.checkStatusOfBlock(coordinateOfAttack)==null){
			targetGrid.updateStatusOfBlock(coordinateOfAttack, BlockSymbol.o);
			receivingPlayer.oceanGrid.updateStatusOfBlock(coordinateOfAttack, BlockSymbol.o);
		}else {
			Ship shipOnCoordinateOfAttack = receivingPlayer.getShipWithCoordinate(coordinateOfAttack);
			shipOnCoordinateOfAttack.decreaseNumberOfBlocksHiddenby1();
			if (shipOnCoordinateOfAttack.getNumberOfBlocksHidden() == 0){
				for (String shipCoordinate: shipOnCoordinateOfAttack.getCoordinateList()){
					targetGrid.updateStatusOfBlock(shipCoordinate, shipOnCoordinateOfAttack.shipType.getBlockSymbol());
					receivingPlayer.oceanGrid.updateStatusOfBlock(shipCoordinate, shipOnCoordinateOfAttack.shipType.getBlockSymbol());
				}
			} else { //there are still blocks hidden from the same ship
				targetGrid.updateStatusOfBlock(coordinateOfAttack, BlockSymbol.x);
				receivingPlayer.oceanGrid.updateStatusOfBlock(coordinateOfAttack, BlockSymbol.x);
			}
		}
	}
	/**
	 * check if coordinate is of valid format
	 * @pre coordinates != null
	 * @return true if of valid format, else false
	 */
	protected boolean isValidCoordinateFormat(String coordinate){
		return coordinate.matches("^"+this.coordinateRegex+"$");
	}

	/**
	 * check if coordinates are of valid format
	 * @pre coordinates != null
	 * @return true if format is valid, else false
	 */
	protected boolean isValidCoordinatesFormat(String coordinates){
		return coordinates.matches("^"+this.coordinateRegex + this.coordinateDelimiterSymbol + this.coordinateRegex+"$");

	}

	/**
	 * checks if all ships in the fleet of the Player has been sunk
	 * @return true if all ships are sunk, else false
	 */
	public boolean areAllShipsSunk(){
		for (Ship ship: fleet){
			if (ship.getNumberOfBlocksHidden() != 0){
				return false;
			}
		}
		return true;
	}

	/**
	 * extracts single coordinates from COORDINATE,COORDINATE string
	 * @pre isValidCoordinatesFormat(coordinates) == true
	 * @return list with all coordinates, e.g. for "A2,A5" return "A2","A3","A4","A5"
	 */
	protected ArrayList<String> getCoordinateList(String coordinates){

		ArrayList<String> coordinateList = new ArrayList<>();

		coordinateList.add(firstCoordinate(coordinates));

		for (int i = 1; i < calculateLength(coordinates); i++){
			String middleCoordinate;
			if(isPlacedVertically(coordinates)){
				middleCoordinate = letterFirstCoordinate(coordinates) +
						Integer.toString(numberFirstCoordinate(coordinates)+i);
			} else {
				middleCoordinate = (char) ((int) letterFirstCoordinate(coordinates) + i) +
						Integer.toString(numberFirstCoordinate(coordinates));
			}
			coordinateList.add(middleCoordinate);
		}
		return coordinateList;
	}

	/**
	 * searching for ship on coordinate
	 * @pre isValidCoordinateFormat(coordinate) == true
	 * @return ship with coordinate in coordinateList, or null if not found
	 */
	public Ship getShipWithCoordinate(String coordinate){
		for (Ship ship : fleet){
			for (String shipCoordinate : ship.getCoordinateList()){
				if (coordinate.equals(shipCoordinate)){
					return ship;
				}
			}
		}
		return null;
	}

	/**
	 * checks if there is already another ship on any of the coordinates
	 * @pre isValidCoordinatesFormat(coordinates) == true
	 * @return true, if there is another ship, else return false
	 */
	protected boolean isOverlapping(String coordinates){
		ArrayList<String> coordinateList = getCoordinateList(coordinates);
		for (String shipCoordinate : coordinateList){
			if (oceanGrid.checkStatusOfBlock(shipCoordinate)!=null){
				return true;
			}
		}
		return false;
	}

	/**
	 * checks if the ship on coordinates is horizontally placed
	 * @pre isValidCoordinatesFormat(coordinates) == true
	 * @return true if placed horizontally, else false
	 */
	protected boolean isPlacedHorizontally(String coordinates){
		return numberFirstCoordinate(coordinates) == numberLastCoordinate(coordinates);
	}

	/**
	 * checks if the ship on coordinates is vertically placed
	 * @pre isValidCoordinatesFormat(coordinates) == true
	 * @return true if placed vertically, else false
	 */
	protected boolean isPlacedVertically(String coordinates){
		return letterFirstCoordinate(coordinates) == letterLastCoordinate(coordinates);
	}

	/**
	 * searches for the index of the coordinateDelimiterSymbol
	 * @pre isValidCoordinatesFormat(coordinates) == true
	 * @return int index of the coordinateDelimiterSymbol
	 */
	protected int indexDelimiterSymbol(String coordinates){
		return coordinates.indexOf(this.coordinateDelimiterSymbol);
	}

	/**
	 * extracts the first coordinate from coordinates String like COORDINATE,COORDINATE
	 * @pre isValidCoordinatesFormat(coordinates) == true
	 * @return String e.g. "B0" for "B0,B1"
	 */
	protected String firstCoordinate(String coordinates){
		return coordinates.substring(0, indexDelimiterSymbol(coordinates));
	}
	/**
	 * extracts the second coordinate from coordinates String like COORDINATE,COORDINATE
	 * @pre isValidCoordinatesFormat(coordinates) == true
	 * @return String e.g. "B1" for "B0,B1"
	 */
	protected String lastCoordinate(String coordinates){
		return coordinates.substring(indexDelimiterSymbol(coordinates)+1);
	}

	/**
	 * extracts the letter of the first coordinate from coordinates String like COORDINATE,COORDINATE
	 * @pre isValidCoordinateFormat(coordinate) == true
	 * @return char e.g. "A" for "A0,B0"
	 */
	protected char letterFirstCoordinate(String coordinates){
		return firstCoordinate(coordinates).charAt(0);
	}
	/**
	 * extracts the letter of the second coordinate from coordinates String like COORDINATE,COORDINATE
	 * @pre isValidCoordinateFormat(coordinate) == true
	 * @return char e.g. "B" for "A0,B0"
	 */
	protected char letterLastCoordinate(String coordinates){
		return lastCoordinate(coordinates).charAt(0);
	}
	/**
	 * extracts the number of the first coordinate from coordinates String like COORDINATE,COORDINATE
	 * @pre isValidCoordinateFormat(coordinate) == true
	 * @return int e.g. 0 for "A0,A1"
	 */
	protected int numberFirstCoordinate(String coordinates){
		return Integer.parseInt(firstCoordinate(coordinates).substring(1));
	}
	/**
	 * extracts the number of the first coordinate from coordinates String like COORDINATE,COORDINATE
	 * @pre isValidCoordinateFormat(coordinate) == true
	 * @return int e.g. 0 for "A0,A1"
	 */
	protected int numberLastCoordinate(String coordinates){
		return Integer.parseInt(lastCoordinate(coordinates).substring(1));
	}

	/**
	 * calculates length between two coordinates
	 * @pre isValidCoordinatesFormat(coordinates) == true
	 * @return int length (>0)
	 */
	protected int calculateLength(String coordinates){
		int lengthCoordinates;
		//calculate length, if ship is placed horizontally
		if (isPlacedHorizontally(coordinates)){
			lengthCoordinates = abs((int) letterLastCoordinate(coordinates) - letterFirstCoordinate(coordinates)) + 1;
		}else { //calculate length, if ship is placed vertically
			lengthCoordinates = abs(numberLastCoordinate(coordinates) - numberFirstCoordinate(coordinates)) + 1;
		}
		return lengthCoordinates;
	}

	/**
	 * switch coordinates, if end coordinate is more in the top-left corner then start coordinate:
	 * 	 - if the letter of the first coordinate is 'higher' than the one of the second coordinate
	 * 	   or if the number of the first coordinate is bigger than the one of the second coordinate:
	 * 		 then switch the coordinates
	 * @pre isValidCoordinatesFormat(coordinates) == true
	 */
	protected String switchCoordinatesIfNecessary(String coordinates){
		if (letterFirstCoordinate(coordinates) > letterLastCoordinate(coordinates) ||
				numberFirstCoordinate(coordinates) > numberLastCoordinate(coordinates)){
			coordinates = lastCoordinate(coordinates) + ","
					+ firstCoordinate(coordinates);
		}
		return coordinates;
	}

	/**
	 * checks whether the coordinate is in the grid
	 * @pre isValidCoordinateFormat(coordinate) == true
	 * @return true if the coordinate is in the grid, else return false
	 */
	protected boolean isCoordinateWithinGrid(String coordinate){
		// coordinateChar in the range of A and last letter of grid
		if (isValidCoordinateFormat(coordinate)) {
			char coordinateChar = coordinate.charAt(0);
			int coordinateNumber = Integer.parseInt(coordinate.substring(1));
			if (coordinateChar < 'A' || coordinateChar > (char) ((int) 'A' + oceanGrid.getHorizontalLength() - 1)
					|| coordinateNumber < 0 || coordinateNumber > oceanGrid.getVerticalLength() - 1) {
				return false;
			} else {
				return true;
			}
		} else return false;
	}

	/**
	 * checks if coordinates are within the grid
	 * @pre isValidCoordinatesFormat(coordinates)
	 * @return true if coordinates are within grid, else false
	 */
	protected boolean areCoordinatesWithinGrid(String coordinates){
		if (!isCoordinateWithinGrid(firstCoordinate(coordinates))
				|| !isCoordinateWithinGrid(lastCoordinate(coordinates))){
			return false;
		}
		return true;
	}

	/**
	 * print both targetGrid and oceanGrid on the console
	 */
	public void displayBoard() {
		System.out.println("===== TARGET GRID =====");
		targetGrid.displayGrid();
		System.out.println("\n===== OCEAN  GRID =====");
		oceanGrid.displayGrid();
	}
}
