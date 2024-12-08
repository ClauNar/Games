package grid;

import exceptions.InvalidCoordinateException;
import org.junit.jupiter.api.Test;
import player.Player;

import static org.junit.jupiter.api.Assertions.*;


class PositionTest {

    Position position = new Position(9, 10);

    @Test
    void getColumnId() {
        assertEquals(9, position.getColumnId());
    }

    @Test
    void getRowId() {
        assertEquals(10, position.getRowId());
    }
    
    @Test
    void falsePosition() throws InvalidCoordinateException {
        Position position1 = new Position("z");
    }

    @Test
    void testNotEquals() {
        Position position1 = new Position(10,10);
        Position position2 = new Position(9,10);

        boolean equal = position1.equals(position2);
        assertFalse(equal);
    }

    @Test
    void testNotEquals2() {
        Position position1 = new Position(10,10);
        Position position2 = new Position(10,9);

        boolean equal = position1.equals(position2);
        assertFalse(equal);
    }


    @Test
    void testEquals() {
        Position position1 = new Position(10,10);
        Position position2 = new Position(10,10);

        boolean equal = position1.equals(position2);
        assertTrue(equal);
    }

    @Test
    void testEquals2() {
        Position position1 = new Position(10,10);

        boolean equal = position1.equals(position1);
        assertTrue(equal);
    }

    @Test
    void testNull() {
        Position position1 = new Position(10,10);

        boolean equal = position1.equals(null);
        assertFalse(equal);
    }

    @Test
    void testDifferentClass() {
        Position position1 = new Position(10,10);
        Player player1 = new Player("player1", Occupacy.Player1);

        boolean equal = position1.equals(player1);
        assertFalse(equal);
    }



    @Test
    void testHashCode() {
        Position position1 = new Position(10,10);
        Position position2 = new Position(10,10);

        int hashCode1 = position1.hashCode();
        int hashCode2 = position2.hashCode();

        assertTrue(hashCode1 == hashCode2);
    }
}
