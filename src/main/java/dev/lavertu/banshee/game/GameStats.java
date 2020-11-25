package dev.lavertu.banshee.game;

import dev.lavertu.banshee.exception.GameDoesNotContainUserException;
import dev.lavertu.banshee.exception.IllegalForfeitException;
import dev.lavertu.banshee.exception.IllegalGameOverException;
import dev.lavertu.banshee.user.Player;

public class GameStats {

    private Player player1;
    private Player player2;
    private Color player1Color = Color.BLACK;
    private Color player2Color = Color.WHITE;
    private boolean gameOver = false;
    private boolean forfeited = false;
    private Player winner;
    private Player loser;
    private Player forfeitPlayer;
    private Player turn;

    public GameStats(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.turn = player1;
    }

    public void gameOver(Player gameWinner) throws IllegalGameOverException {
        try {
            this.gameOver = true;
            winner = gameWinner;
            loser = whoLost(gameWinner);
        } catch(GameDoesNotContainUserException e) {
            gameOver = false;
            winner = null;
            e.printStackTrace();
            throw new IllegalGameOverException();
        }
    }

    public void forfeitGame(Player gameWinner) throws IllegalForfeitException {
        try {
            this.gameOver = true;
            forfeited = true;
            winner = gameWinner;
            loser = whoLost(gameWinner);
            forfeitPlayer = whoLost(gameWinner);
        } catch(GameDoesNotContainUserException e) {
            gameOver = false;
            winner = null;
            forfeitPlayer = null;
            forfeited = false;
            e.printStackTrace();
            throw new IllegalForfeitException();
        }
    }

    private Player whoLost(Player gameWinner) throws GameDoesNotContainUserException {
        String winnerId = gameWinner.getPlayerId();
        if(winnerId.equals(player1.getPlayerId())) {
            return player2;
        } else if(winnerId.equals(player2.getPlayerId())) {
            return player1;
        } else {
            throw new GameDoesNotContainUserException();
        }
    }

    public Color getPlayer1Color() {
        return player1Color;
    }

    public Color getPlayer2Color() {
        return player2Color;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isForfeited() {
        return forfeited;
    }

    public Player getWinner() {
        return winner;
    }

    public Player getLoser() {
        return loser;
    }

    public Player getForfeitPlayer() {
        return forfeitPlayer;
    }

    public Player getTurn() {
        return turn;
    }
}
