package dev.lavertu.banshee.game;

import dev.lavertu.banshee.user.Player;

public class GameStats {

    private Player player1;
    private Player player2;
    private boolean gameOver;
    private boolean didForfeit;
    private Player forfeitPlayer;
    private Player winner;
    private Player loser;
    private Player turn;
    private Color player1Color = Color.BLACK;
    private Color player2Color = Color.WHITE;

    public GameStats() {

    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Color getPlayer1Color() {
        return player1Color;
    }

    public Color getPlayer2Color() {
        return player2Color;
    }
}
