package dev.lavertu.banshee.user;

public class UserStats {

    private int wins;
    private int loses;
    private int gamesPlayed;

    public UserStats() {
        // Can make DB calls once configured
        this.wins = 0;
        this.loses = 0;
        this.gamesPlayed = 0;
    }

    public int getWins() {
        return wins;
    }

    public int getLoses() {
        return loses;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }
}
