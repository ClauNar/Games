# Battleship Game

## Overview
Welcome to the Battleship Game! 

This is a console-based game where you can challenge the computer to a classic game of Battleship. Strategically place your ships and attack the enemy grid to sink all of their ships before they sink yours.

---

## How to Play It?
1. Clone the repository or download the code.
2. Open a terminal or your preferred IDE.
3. Compile and run the `Main` class.

### **Using Terminal**
- **Compile the files:**
  ```
  javac -d out *.java
  ```
- **Run the program:**
  ```
  java -cp out Main
  ```


---

## Rules
At the start of the game, you will place your ships by entering the coordinates in the format `COORDINATE,COORDINATE` (e.g., `A2,A5`). 

The ships you will use are:
- 1 x `Carrier (C)`: 6 blocks
- 2 x `Battleship (B)`: 4 blocks
- 3 x `Submarine (S)`: 3 blocks
- 4 x `Patrol Boat (P)` - 2 blocks

After placement, the game alternates turns:
- Your Turn: Attack the computer's grid by specifying a coordinate (e.g., `B4`).
- Computer's Turn: The computer will attack your grid randomly.

Attack Outcomes:
  - `x`: Hit a ship.
  - `o`: Miss (hit the ocean).
  - Once a ship is sunk, its abbreviation (`C`, `B`, `S`, `P`) is displayed on the grid. 

The game ends when one player's fleet is completely sunk.

---

## Game Structure
### Classes
`Main`: Entry point of the game. It initializes and runs the `Game` class.

`Game`: Manages the flow of the game:
- Players deploy their ships.
- Randomly selects the starting player.
- Alternates turns until one player wins.

`Player` (Abstract Class): Base class for the players:
- Handles grid setup, ship placement, and attack logic.
- Ensures input validation and coordinate management.

`UserPlayer` (Extends `Player`): Handles user input for placing ships and attacking the computer grid.

`ComputerPlayer` (Extends `Player`): Automates ship placement and attack logic using randomization while ensuring valid moves.

`Grid`: Represents the game board:
- Displays the ocean and target grids.
- Updates and checks block statuses.

`ShipType` (Enum): Defines the ships used in the game:
- Name, count, length, and symbol.

`BlockSymbol` (Enum): Represents the symbols (`x`, `o`, `C`, `B`, `S`, `P`) used on the grid.

### Interactions
- The `Main` class invokes the `Game` class, which manages interactions between `UserPlayer` and `ComputerPlayer`.
- Both players use the `Grid` class for their ocean and target grids.
- Ship placements and attacks are validated and updated across the grids.

---

## Screenshots
Below are some screenshots showcasing the gameplay:

First, if the user requires it, the game provides a brief overview of the rules:
![rules.png](screenshots%2Frules.png)

Then, the user places their ships on the grid one at a time. 
Each placement must adhere to the following constraints:
- Ships must be placed in the correct format and length.
- Placement must be either horizontal or vertical.
- Ships must stay within the grid boundaries and must not overlap.

![placement.png](screenshots%2Fplacement.png)

The game begins, with either the user or the computer randomly selected to make the first move:
![begin_game.png](screenshots%2Fbegin_game.png)

When all of one player’s ships are sunk, the game declares the winner and displays the result:
![game_over.png](screenshots%2Fgame_over.png)