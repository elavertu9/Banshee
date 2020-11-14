package dev.lavertu.banshee.game;

import dev.lavertu.banshee.game.exception.*;

public class Game {

    private Player player1;
    private Player player2;
    private GameBoard gameBoard;
    private String gameId;
    private boolean gameOver = false;
    private boolean playerForfeit = false;
    private Player forfeitPlayer;
    private Player turn;
    private Color player1Color = Color.BLACK;
    private Color player2Color = Color.WHITE;
    private RuleEnforcer ruleEnforcer;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.turn = player1;
        this.gameBoard = new GameBoard();
        this.ruleEnforcer = new RuleEnforcer(gameBoard);
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
        } catch(CoordinateOutOfBoundsException | IllegalCaptureException e) {
            e.printStackTrace();
            throw new CaptureException();
        }
    }

    private void performTravel(Move move) throws TravelException {
        try {
            if(ruleEnforcer.isValidTravel(move)) {
                gameBoard.swapPieces(move.getFrom(), move.getTo());
            }
        } catch(CoordinateOutOfBoundsException | SpaceOccupiedException e) {
            e.printStackTrace();
            throw new TravelException();
        }
    }

    private void performFlip(Move move) throws FlipException {
        try {
            if(ruleEnforcer.isValidFlip(move)) {
                gameBoard.pieceAt(move.getTo()).flipPiece();
            }
        } catch(CoordinateOutOfBoundsException | PieceFaceUpException e) {
            e.printStackTrace();
            throw new FlipException();
        }
    }

    public void flipAll() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 8; j++) {
                gameBoard.pieceAt(new Coordinate(i, j)).flipPiece();
            }
        }
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

    public String printGameBoard() {
        return getPlayer1().getName() + " - " + getPlayer1Color() + "\n" + getPlayer2().getName() + " - " + getPlayer2Color() + "\n" + gameBoard.toString();
    }
}
