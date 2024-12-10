public class Grid {
    private final int horizontalLength;
    private final int verticalLength;
    private final BlockSymbol[][] block2DList;

    /**
     * generate an empty 10*10 grid
     */
    public Grid() {
        this.horizontalLength = 10; //MAX 26 (= nr letters in alphabet)
        this.verticalLength = 10; //MAX 99 (so that it is still printed correctly)
        this.block2DList = new BlockSymbol[horizontalLength][verticalLength];
    }
    /**
     * @return horizontalLength as Int
     */
    public int getHorizontalLength() {
        return horizontalLength;
    }

    /**
     * @return verticalLength as an int
     */
    public int getVerticalLength() {
        return verticalLength;
    }

    /**
     * create the x-axis labels with letters
     */
    private void xAxisLetters() {
        System.out.print("   ");
        for (int i = 0; i < horizontalLength; i++) {
            System.out.print((char) ('A' + i) + " ");
        }
        System.out.println();
    }

    /**
     * create the "+-" line
     */
    private void xAxisPlusMinus() {
        System.out.print("  ");
        for (int i = 0; i < horizontalLength; i++) {
            System.out.print("+-");
        }
        System.out.println("+ ");
    }

    /**
     * create a single line from the grid depending on how big the grid was initialized
     * the y-axis label with numbers is included in this
     */
    private void gridLine(int number) {
        String singleLine = "";
        for (int i = 0; i < horizontalLength; i++) {
            char characterToPrint;
            if (block2DList[i][number]==null){
                characterToPrint = ' ';
            }else {
                characterToPrint = block2DList[i][number].getCharRepresentation();
            }
            singleLine += "|" + characterToPrint;
        }
        if (number < 10){
            System.out.print(" ");
        }
        System.out.printf("%d" + singleLine + "|" + "%d\n", number, number);
    }

    /**
     * display the grid with all its elements
     */
    public void displayGrid() {
        //label x-axis with letters and y-axis with numbers
        xAxisLetters();
        xAxisPlusMinus();
        for (int i = 0; i < verticalLength; i++) {
            gridLine(i);
        }
        xAxisPlusMinus();
        xAxisLetters();
    }

    /**
     * @return the x-coordinate as a character
     */
    public int getXCoordinate(String coordinate) {
        return coordinate.charAt(0) - 'A';
    }

    /**
     * 
     * @return the y-coordinate as an int
     */
    private int getYCoordinate(String coordinate) {
        String yCoordinate = coordinate.substring(1);
        return Integer.parseInt(yCoordinate);
    }

    /**
     * check the coordinate on the block2DList
     * @return the specific symbol on the given coordinate
     */
    public BlockSymbol checkStatusOfBlock(String coordinate) {
        // check block2D list and return value
        return block2DList[getXCoordinate(coordinate)][getYCoordinate(coordinate)];
    }

    /**
     * updates the block to the right symbol from the BlockSymbol list
     */
    public void updateStatusOfBlock(String coordinate, BlockSymbol symbol) {
        block2DList[getXCoordinate(coordinate)][getYCoordinate(coordinate)] = symbol;
    }
}
