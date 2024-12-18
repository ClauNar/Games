package main.java.com.tuttogame.player;

public class Player implements Comparable<Player> {
    private String name;
    private int points;

    public Player(String name){
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public void awardPoints(int points) {
        assert points > 0;
        this.points += points;
    }
    public void deductPoints(int points){
        assert points > 0;
        this.points -= points;
    }

    @Override
    public int compareTo(Player otherPlayer) {
        return  otherPlayer.getPoints() - this.getPoints();
    }
}
