package dev.lavertu.banshee.game;

import dev.lavertu.banshee.exception.*;
import dev.lavertu.banshee.user.User;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;


//@Entity
//@Table(name = "Game")
public class Game implements Serializable {

//    @Id
    private UUID gameId;
    private User player1;
    private User player2;
    private GameBoard gameBoard;
    private GameStats gameStats;
    private RuleEnforcer ruleEnforcer;

    public Game(User player1, User player2) {
        this(UUID.randomUUID(), player1, player2);
    }

    public Game(UUID gameId, User player1, User player2) {
        this.gameId = gameId;
        this.player1 = player1;
        this.player2 = player2;
        this.gameStats = new GameStats(player1, player2);
        this.gameBoard = new GameBoard();
        this.ruleEnforcer = new RuleEnforcer(this);
    }

    public void flipAll() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 8; j++) {
                gameBoard.pieceAt(new Coordinate(i, j)).flipPiece();
            }
        }
    }

    public void makeMove(Move move) throws IllegalMoveException {
        try {
            if(move.getMoveType() == MoveType.CAPTURE) {
                performCapture(move);
            }else if(move.getMoveType() == MoveType.TRAVEL) {
                performTravel(move);
            } else if(move.getMoveType() == MoveType.FLIP) {
                performFlip(move);
            } else {
                throw new IllegalMoveException();
            }
        } catch(CaptureException | TravelException | FlipException e) {
            e.printStackTrace();
            throw new IllegalMoveException();
        }
    }

    private void performCapture(Move move) throws CaptureException {
        try {
            if(ruleEnforcer.isValidCapture(move)) {
                gameBoard.removePiece(move.getTo());
                gameBoard.swapPieces(move.getFrom(), move.getTo());
            }
        } catch(CoordinateOutOfBoundsException | IllegalCaptureException | CaptureEmptySpaceException | SameColorException | GameOverException | IllegalHopException e) {
            e.printStackTrace();
            throw new CaptureException();
        }
    }

    private void performTravel(Move move) throws TravelException {
        try {
            if(ruleEnforcer.isValidTravel(move)) {
                gameBoard.swapPieces(move.getFrom(), move.getTo());
            }
        } catch(CoordinateOutOfBoundsException | SpaceOccupiedException | GameOverException | IllegalHopException e) {
            e.printStackTrace();
            throw new TravelException();
        }
    }

    private void performFlip(Move move) throws FlipException {
        try {
            if(ruleEnforcer.isValidFlip(move)) {
                gameBoard.pieceAt(move.getTo()).flipPiece();
            }
        } catch(CoordinateOutOfBoundsException | PieceFaceUpException | GameOverException e) {
            e.printStackTrace();
            throw new FlipException();
        }
    }

    public User getPlayer1() {
        return player1;
    }

    public User getPlayer2() {
        return player2;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public GameStats getGameStats() {
        return gameStats;
    }

    @Override
    public String toString() {
        return player1.getName() + " - " + gameStats.getPlayer1Color() + "\n" + player2.getName() + " - " + gameStats.getPlayer2Color() + "\n" + gameBoard.toString();
    }
}
