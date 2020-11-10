package dev.lavertu.banshee.game;

import dev.lavertu.banshee.game.exception.IllegalMoveException;

public class Game {

    private Player player1;
    private Player player2;
    private GameBoard gameBoard;
    private String gameId;
    private boolean gameOver = false;
    private boolean playerForfeit = false;
    private Player forfeitPlayer;
    private Player turn;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.turn = player1;
        this.gameBoard = new GameBoard();
    }

    public void makeMove(Move move) throws IllegalMoveException {
        if(move.getMoveType() == MoveType.CAPTURE) {
            performCapture(move);
        }else if(move.getMoveType() == MoveType.TRAVEL) {
            performTravel(move);
        } else if(move.getMoveType() == MoveType.FLIP) {
            performFlip(move);
        } else {
            throw new IllegalMoveException();
        }
    }

    public void performCapture(Move move) {

    }

    public void performTravel(Move move) {

    }

    public void performFlip(Move move) {

    }

    public String printGameBoard() {
        return gameBoard.toString();
    }
}
