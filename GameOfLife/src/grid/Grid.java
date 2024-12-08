package grid;

import java.util.*;

public class Grid {
    private Map<Position, Occupacy> cells;
    private Map<Position, Occupacy> cellsNextGen;
    private final int rows;
    private final int columns;

    public Grid(int pRows, int pColumns){
        rows = pRows;
        columns = pColumns;

        cells = new Hashtable<>();
        cellsNextGen = new Hashtable<>();

        // initialize empty cells
        initializeEmptyCells(cells);
        initializeEmptyCells(cellsNextGen);

        // initialize starting positions
        initializeStartingPositions();
    }

    private void initializeEmptyCells(Map<Position, Occupacy> cells){
        for (int currentRow = 0; currentRow < rows; currentRow++) {
            for (int currentColumn = 0; currentColumn < columns; currentColumn++) {
                Position currentPosition = new Position(currentColumn, currentRow);
                cells.put(currentPosition, Occupacy.EMPTY);
            }
        }
    }

    private void initializeStartingPositions(){
        int startingShift = 2;
        for (int x = -1; x < 1; x++) {
            for (int y = -1; y < 1; y++) {
                // Player 1 positions
                Position player1Position = new Position(columns / 2 + x, rows / 2 - startingShift + y);
                cells.put(player1Position, Occupacy.Player1);

                // Player 2 positions
                Position player2Position = new Position(columns / 2 + x, rows / 2 + startingShift + y);
                cells.put(player2Position, Occupacy.Player2);
            }
        }

    }

    public List<Position> getNeighbourPositions(Position position) {
        List<Position> neighbours = new ArrayList<>();
        for (int x = position.getColumnId() - 1; x <= position.getColumnId() + 1; x++) {
            for (int y = position.getRowId() - 1; y <= position.getRowId() + 1; y++) {
                Position neighbour = new Position(x, y);
                if (!neighbour.equals(position) && cells.containsKey(neighbour)) {
                    neighbours.add(neighbour);
                }
            }
        }
        return neighbours;
    }

    public void setOccupacy(Position position, Occupacy occupacy) {
        cells.put(position, occupacy);
    }

    public Occupacy getOccupacy(Position position) {
        return cells.getOrDefault(position, Occupacy.EMPTY);
    }

    public void advanceAGeneration() {
        // Clear the next generation map
        cellsNextGen.clear();

        // Iterate through all positions in the grid
        for (Map.Entry<Position, Occupacy> entry : cells.entrySet()) {
            Position position = entry.getKey();
            Occupacy currentOccupacy = entry.getValue();

            // Count the alive neighbors
            int aliveNeighboursPlayer1 = 0;
            int aliveNeighboursPlayer2 = 0;
            for (Position neighbor : getNeighbourPositions(position)) {
                Occupacy neighborOccupacy = cells.getOrDefault(neighbor, Occupacy.EMPTY);
                if (neighborOccupacy == Occupacy.Player1) {
                    aliveNeighboursPlayer1++;
                } else if (neighborOccupacy == Occupacy.Player2) {
                    aliveNeighboursPlayer2++;
                }
            }

            int totalAliveNeighbours = aliveNeighboursPlayer1 + aliveNeighboursPlayer2;

            // Determine the next state for the current position
            Occupacy nextOccupacy = Occupacy.EMPTY;
            if (currentOccupacy != Occupacy.EMPTY) {
                // Alive cell stays alive if it has 2 or 3 neighbors
                if (totalAliveNeighbours == 2 || totalAliveNeighbours == 3) {
                    nextOccupacy = currentOccupacy;
                }
            } else {
                // Dead cell becomes alive if exactly 3 neighbors
                if (totalAliveNeighbours == 3) {
                    nextOccupacy = aliveNeighboursPlayer1 > aliveNeighboursPlayer2 ? Occupacy.Player1 : Occupacy.Player2;
                }
            }

            // Update the next generation map
            cellsNextGen.put(position, nextOccupacy);
        }

        // Replace the current generation with the next generation
        cells.clear();
        cells.putAll(cellsNextGen);
    }


    public void advanceAGeneration_2() {
        // Clear the next generation map
        cellsNextGen.clear();

        // Iterate through all cells in the current generation
        for (Map.Entry<Position, Occupacy> entry : cells.entrySet()) {
            Position position = entry.getKey();
            Occupacy currentOccupacy = entry.getValue();

            // Count alive neighbors
            int aliveNeighboursPlayer1 = 0;
            int aliveNeighboursPlayer2 = 0;
            for (Position neighbor : getNeighbourPositions(position)) {
                Occupacy neighborOccupacy = cells.getOrDefault(neighbor, Occupacy.EMPTY);
                if (neighborOccupacy == Occupacy.Player1) {
                    aliveNeighboursPlayer1++;
                } else if (neighborOccupacy == Occupacy.Player2) {
                    aliveNeighboursPlayer2++;
                }
            }

            // Determine the next state for this position
            Occupacy nextOccupacy = Occupacy.EMPTY;
            if (currentOccupacy != Occupacy.EMPTY) {
                // Rules for live cells
                int totalAliveNeighbours = aliveNeighboursPlayer1 + aliveNeighboursPlayer2;
                if (totalAliveNeighbours == 2 || totalAliveNeighbours == 3) {
                    nextOccupacy = currentOccupacy;
                }
            } else {
                // Rules for dead cells
                if (aliveNeighboursPlayer1 + aliveNeighboursPlayer2 == 3) {
                    nextOccupacy = aliveNeighboursPlayer1 > aliveNeighboursPlayer2 ? Occupacy.Player1 : Occupacy.Player2;
                }
            }

            // Update the next generation map
            cellsNextGen.put(position, nextOccupacy);
        }

        // Replace current generation with the next generation
        cells = cellsNextGen;
    }


    public void printGrid() {
        xAxis();
        for(int currentRow=0; currentRow<columns; currentRow++) {
            yAxis(currentRow);
            for (int currentColumn = 0; currentColumn < rows; currentColumn++) {
                Position currentPosition = new Position(currentColumn,currentRow);
                System.out.print(" "+ getOccupacy(currentPosition).getSymbol() + " │");
            }
            System.out.println();
        }
        xAxis();
    }

    private void yAxis(int row){
        if (row < 10){
            System.out.print(row + "  │");
        } else {
            System.out.print(row + " │");
        }
    }

    private void xAxis() {
        System.out.print("     ");
        for (int i = 0; i < columns; i++) {
            if (i < 10){
                System.out.print(i + "   ");
            } else {
                System.out.print(i + "  ");
            }
        }
        System.out.println();
    }

    public int nrAliveCellsPlayer1() {
        int counterAliveCellsPlayer1 = 0;
        for (Map.Entry<Position, Occupacy> entry : cells.entrySet()) {
            if (entry.getValue() == Occupacy.Player1) {
                counterAliveCellsPlayer1++;
            }
        }
        return counterAliveCellsPlayer1;
    }

    public int nrAliveCellsPlayer2() {
        int counterAliveCellsPlayer2 = 0;
        for (Map.Entry<Position, Occupacy> entry : cells.entrySet()) {
            if (entry.getValue() == Occupacy.Player2) {
                counterAliveCellsPlayer2++;
            }
        }
        return counterAliveCellsPlayer2;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
}