# Game of Life - 2 Player Version

## Overview
Welcome to the **2-Player Game of Life**, inspired by Conway's Game of Life! This is a console-based game where two players alternate turns in a competitive twist on the classic cellular automaton.

For more information about Conway's Game of Life, see [this Wikipedia article](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life).

---

## How to Play It?

1. Clone the repository or download the code.
2. Open a terminal or your preferred IDE.
3. Compile and run the `Main` class.

---

## Rules
At the start of the game:
- The grid is initialized with a symmetric configuration of live cells for both players.

### **Gameplay**:
- Players alternate turns. On each turn, the active player must:
1. Select and kill one of the opponent’s cells.
2. Select an empty cell to bring to life with their own symbol.

### **Generation Evolution**:
After each turn:
- The grid evolves by one generation, following the rules of Conway's Game of Life:
- **Live Cells**:
    - Stay alive if they have 2 or 3 live neighbors.
    - Otherwise, they die (due to underpopulation or overpopulation).
- **Dead Cells**:
    - Come to life if they have exactly 3 live neighbors.
    - The cell takes the symbol of the majority of its neighbors.
    - (A tie is impossible since exactly 3 neighbors are required to bring a cell to life.)

### **Winning the Game**:
- A player is eliminated when they have no live cells remaining.
- The game ends when one or both players have no live cells left.

---

## Game Structure

### Classes
`Main`: Entry point of the game. Initializes and runs the `Game` class.

`Game`: Manages gameplay:
- Handles player turns.
- Alternates play until one or both players are eliminated.
- Explains the rules if needed.

`Player`: Represents the players, holding their symbols and names.

`Grid`: Manages the game board:
- Tracks the current state of each cell (alive or empty).
- Calculates neighbors and determines the next generation based on the rules.

`Position`: Encapsulates row-column coordinates on the grid.

`Occupacy` (Enum): Represents the state of a cell:
- `EMPTY`, `Player1`, or `Player2`.

---

## Screenshots
1. **Game Setup**:
   Players set up their symbols and names.
   ![setup.png](screenshots%2Fsetup.png)

2. **Gameplay**:
   Players alternate turns to eliminate opponent cells and occupy new ones.
   ![gameplay.png](screenshots%2Fgameplay.png)

3. **Game Over**:
   The game announces the winner or declares a tie.
   ![game_over.png](screenshots%2Fgame_over.png)

