package dev.lavertu.banshee.game;

import dev.lavertu.banshee.exception.GameDoesNotContainUserException;
import dev.lavertu.banshee.exception.IllegalForfeitException;
import dev.lavertu.banshee.exception.IllegalGameOverException;
import dev.lavertu.banshee.user.User;

public class GameStats {

    private User user1;
    private User user2;
    private Color user1Color = Color.BLACK;
    private Color user2Color = Color.WHITE;
    private boolean gameOver = false;
    private boolean forfeited = false;
    private User winner;
    private User loser;
    private User forfeitUser;
    private User turn;

    public GameStats(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.turn = user1;
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
            forfeitUser = whoLost(gameWinner);
        } catch(GameDoesNotContainUserException e) {
            gameOver = false;
            winner = null;
            forfeitUser = null;
            forfeited = false;
            e.printStackTrace();
            throw new IllegalForfeitException();
        }
    }

    private User whoLost(User gameWinner) throws GameDoesNotContainUserException {
        String winnerId = gameWinner.getUserId();
        if(winnerId.equals(user1.getUserId())) {
            return user2;
        } else if(winnerId.equals(user2.getUserId())) {
            return user1;
        } else {
            throw new GameDoesNotContainUserException();
        }
    }

    public Color getUser1Color() {
        return user1Color;
    }

    public Color getUser2Color() {
        return user2Color;
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

    public User getForfeitUser() {
        return forfeitUser;
    }

    public User getTurn() {
        return turn;
    }
}