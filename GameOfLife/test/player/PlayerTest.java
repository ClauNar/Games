package player;

import grid.Occupacy;
import grid.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class PlayerTest {

    Player player = new Player("testPlayer", Occupacy.Player1);

    @Test
    void getName() {
        assertEquals("testPlayer", player.getName());
    }

    @Test
    void getOccupacy() {
        assertEquals(Occupacy.Player1, player.getOccupacy());
    }

    @Test
    void testEquals() {
        Player player1 = new Player("player1", Occupacy.Player1);
        Player player2 = new Player("player1", Occupacy.Player1);

        boolean equal = player1.equals(player2);
        assertTrue(equal);

    }

    @Test
    void testNotEquals() {
        Player player1 = new Player("player1", Occupacy.Player1);
        Player player2 = new Player("player2", Occupacy.Player2);

        boolean equal = player1.equals(player2);
        assertFalse(equal);

    }

    @Test
    void testEquals2() {
        Player player1 = new Player("player1", Occupacy.Player1);
        Player player2 = new Player("player1", Occupacy.Player2);

        boolean equal = player1.equals(player2);
        assertTrue(equal);

    }

    @Test
    void testNull() {
        Player player1 = new Player("player1", Occupacy.Player1);
        boolean equal = player1.equals(null);
        assertFalse(equal);

    }

    @Test
    void testEqualsSelf() {
        Player player1 = new Player("player1", Occupacy.Player1);
        boolean equal = player1.equals(player1);
        assertTrue(equal);

    }

    @Test
    void testHashCode() {
        Player player1 = new Player("player1", Occupacy.Player1);
        Player player2 = new Player("player1", Occupacy.Player1);

        int hashCode1 = player1.hashCode();
        int hashCode2 = player2.hashCode();

        assertTrue(hashCode1 == hashCode2);

    }
}
