package player;

import grid.Occupacy;

import java.util.Objects;

public class Player {
    private final Occupacy occupacy;
    private final String name;
    public Player(String pName, Occupacy pOccupacy){
        name = pName;
        occupacy = pOccupacy;
    }

    public String getName() {
        return name;
    }

    public Occupacy getOccupacy() {
        return occupacy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player otherPlayer = (Player) o;
        String otherPlayerName = otherPlayer.getName();
        return this.name.equals(otherPlayerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
