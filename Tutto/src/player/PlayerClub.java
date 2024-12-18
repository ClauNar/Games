package main.java.com.tuttogame.player;

import java.util.ArrayList;

public class PlayerClub {
    private final ArrayList<Player> players;

    public PlayerClub() {
        players = new ArrayList<>();
    }

    public void addPlayer(Player player){
        players.add(player);
        sortPlayersAccoringToName();
    }

    public boolean existsPlayerName(String name){
        for(Player player : players){
            if (player.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public Player getPlayer(int i){
        return players.get(i);
    }

    private void sortPlayersAccoringToName(){
        players.sort((a,b) -> a.getName().compareTo(b.getName()));
    }

    public boolean hasAPlayerReachedPoints(int points){
        for (Player player : players){
            if (player.getPoints() > points){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Player> getWinningPlayers() {
        ArrayList<Player> winners = new ArrayList<>();
        int maxPoints = players.get(0).getPoints(); // Initialize with the first player's points

        for (Player player : players) {
            if (player.getPoints() > maxPoints) {
                winners.clear(); // A new high score; clear the previous winners
                maxPoints = player.getPoints();
                winners.add(player);
            } else if (player.getPoints() == maxPoints) {
                winners.add(player); // Add player with same max points
            }
        }
        return winners;
    }

}
