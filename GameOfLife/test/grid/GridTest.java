package grid;

import helper.StandardInputStream;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



class GridTest {

    private PrintStream console = null;
    private ByteArrayOutputStream bytes = null;
    Grid grid = new Grid(15, 15);


    @Before
    public void init() {
        bytes = new ByteArrayOutputStream();
        console = System.out;
        System.setOut(new PrintStream(bytes));
    }
    
    
    @Test
    void printTest() {
        grid.printGrid();
        assertEquals(4, grid.nrAliveCellsPlayer1());
        assertEquals(4, grid.nrAliveCellsPlayer2());
    }

    @Test
    void getColumns() {
        assertEquals(15,grid.getColumns());
    }

    @Test
    void getRows() {
        assertEquals(15,grid.getRows());
    }

    @After
    public void tearDown() {
        System.setOut(console);
    }
}
