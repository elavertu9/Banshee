package dev.lavertu.banshee.game;

import dev.lavertu.banshee.exception.GameDoesNotContainUserException;
import dev.lavertu.banshee.exception.IllegalForfeitException;
import dev.lavertu.banshee.exception.IllegalGameOverException;
import dev.lavertu.banshee.user.User;

public class GameStats {

    private User player1;
    private User player2;
    private Color player1Color = Color.BLACK;
    private Color player2Color = Color.WHITE;
    private boolean gameOver = false;
    private boolean forfeited = false;
    private User winner;
    private User loser;
    private User forfeitPlayer;
    private User turn;

    public GameStats(User player1, User player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.turn = player1;
    }

    public void gameOver(User gameWinner) throws IllegalGameOverException {
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

    public void forfeitGame(User gameWinner) throws IllegalForfeitException {
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

    private User whoLost(User gameWinner) throws GameDoesNotContainUserException {
        String winnerId = gameWinner.getPlayerId();
        if(winnerId.equals(player1.getPlayerId())) {
            return player2;
        } else if(winnerId.equals(player2.getPlayerId())) {
            return player1;
        } else {
            throw new GameDoesNotContainUserException();
        }
    }

    public User getPlayer1() {
        return player1;
    }

    public User getPlayer2() {
        return player2;
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

    public User getWinner() {
        return winner;
    }

    public User getLoser() {
        return loser;
    }

    public User getForfeitPlayer() {
        return forfeitPlayer;
    }

    public User getTurn() {
        return turn;
    }
}