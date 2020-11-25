package dev.lavertu.banshee.game;

import dev.lavertu.banshee.exception.*;
import dev.lavertu.banshee.game.pieces.iPiece;

public class RuleEnforcer {

    private GameBoard gameBoard;

    public RuleEnforcer(GameBoard gameboard) {
        this.gameBoard = gameboard;
    }

    public boolean isValidCapture(Move move) throws CoordinateOutOfBoundsException, IllegalCaptureException, CaptureEmptySpaceException, SameColorException {
        if(coordinatesInBounds(move)) {
            if(!isPieceToEmpty(move)) {
                if(!isSameColor(move)) {
                    if((!isSameCoordinate(move.getFrom(), move.getTo())) && (areFaceUp(move)) && (!isEmptySpace(move.getFrom()) && !isEmptySpace(move.getTo())) && (!isGeneralCapturingSoldier(move)) && ((isSoldierCapturingGeneral(move)) || (canCapture(move))) ) {
                        return true;
                    } else {
                        throw new IllegalCaptureException();
                    }
                } else {
                    throw new SameColorException();
                }
            } else {
                throw new CaptureEmptySpaceException();
            }
        } else {
            throw new CoordinateOutOfBoundsException();
        }
    }

    public boolean isValidTravel(Move move) throws CoordinateOutOfBoundsException, SpaceOccupiedException {
        if(coordinatesInBounds(move)) {
            if(isPieceToEmpty(move)) {
                return true;
            } else {
                throw new SpaceOccupiedException();
            }
        } else {
            throw new CoordinateOutOfBoundsException();
        }
    }

    public boolean isValidFlip(Move move) throws CoordinateOutOfBoundsException, PieceFaceUpException {
        if(coordinatesInBounds(move)) {
            iPiece piece = gameBoard.pieceAt(move.getTo());
            if(!piece.getIsFaceUp()) {
                return true;
            } else {
                throw new PieceFaceUpException();
            }
        } else {
            throw new CoordinateOutOfBoundsException();
        }
    }

    private boolean coordinatesInBounds(Move move) {
        return (move.getFrom().getRow() < gameBoard.getRows() && move.getFrom().getRow() >= 0) && (move.getFrom().getColumn() < gameBoard.getCols() && move.getFrom().getColumn() >= 0) && (move.getTo().getRow() < gameBoard.getRows() && move.getTo().getRow() >= 0) && (move.getTo().getColumn() < gameBoard.getCols() && move.getTo().getColumn() >= 0);
    }

    public boolean isPieceToEmpty(Move move) {
        // piece can travel to empty
        return (gameBoard.pieceAt(move.getFrom()).getRank() > 0) && (isEmptySpace(move.getTo()));
    }

    private boolean isSameCoordinate(Coordinate fromCoordinate, Coordinate toCoordinate) {
        return (fromCoordinate.getRow() == toCoordinate.getRow()) && (fromCoordinate.getColumn() == toCoordinate.getColumn());
    }

    private boolean isEmptySpace(Coordinate coordinate) {
        return gameBoard.pieceAt(coordinate).getRank() == 0;
    }

    private boolean isGeneralCapturingSoldier(Move move) {
        // GENERAL(7) can not capture SOLDIER(1)
        return (gameBoard.pieceAt(move.getFrom()).getRank() == 7) && (gameBoard.pieceAt(move.getTo()).getRank() == 1);
    }

    private boolean isSoldierCapturingGeneral(Move move) {
        // SOLDIER(1) can capture GENERAL(7)
        return (gameBoard.pieceAt(move.getFrom()).getRank() == 1) && (gameBoard.pieceAt(move.getTo()).getRank() == 7);
    }

    private boolean isSameColor(Move move) {
        return gameBoard.pieceAt(move.getFrom()).getColor() == gameBoard.pieceAt(move.getTo()).getColor();
    }

    private boolean areFaceUp(Move move) {
        return gameBoard.pieceAt(move.getFrom()).getIsFaceUp() && gameBoard.pieceAt(move.getTo()).getIsFaceUp();
    }

    private boolean canCapture(Move move) {
        return gameBoard.pieceAt(move.getFrom()).compareTo(gameBoard.pieceAt(move.getTo())) >= 0;
    }
}
