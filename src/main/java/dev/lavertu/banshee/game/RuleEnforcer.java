package dev.lavertu.banshee.game;

import dev.lavertu.banshee.exception.*;
import dev.lavertu.banshee.game.pieces.iPiece;

public class RuleEnforcer {

    private Game game;

    public RuleEnforcer(Game game) {
        this.game = game;
    }

    public boolean isValidCapture(Move move) throws CoordinateOutOfBoundsException, GameOverException, IllegalMoveException {
        String errorMessage = "";
        if(!game.getFinished()) {
            if(coordinatesInBounds(move)) {
                if(isValidHop(move)) {
                    if(!isPieceToEmpty(move)) {
                        if(!isSameColor(move)) {
                            if((!isSameCoordinate(move.getFrom(), move.getTo())) && (areFaceUp(move)) && (!isEmptySpace(move.getFrom()) && !isEmptySpace(move.getTo())) && (!isGeneralCapturingSoldier(move)) && ((isSoldierCapturingGeneral(move)) || (canCapture(move))) ) {
                                return true;
                            } else {
                                errorMessage = "Illegal capture attempted \n" + move.toString();
                            }
                        } else {
                            errorMessage = "Cannot capture piece of same color \n" + move.toString();
                        }
                    } else {
                        errorMessage = "Cannot capture an empty space \n" + move.toString();
                    }
                } else {
                    errorMessage = "Move Coordinates indicate an invalid hop \n" + move.toString();
                }
            } else {
                throw new CoordinateOutOfBoundsException(move.toString());
            }
        } else {
            throw new GameOverException();
        }

        throw new IllegalMoveException(errorMessage);
    }

    public boolean isValidTravel(Move move) throws CoordinateOutOfBoundsException, GameOverException, IllegalMoveException {
        if(!game.getFinished()) {
            if(coordinatesInBounds(move)) {
                if(isValidHop(move)) {
                    if(isPieceToEmpty(move)) {
                        return true;
                    } else {
                        errorMessage = "Space occupied when attempting to travel \n" + move.toString();
                    }
                } else {
                    errorMessage = "Move Coordinates indicate an invalid hop \n" + move.toString();
                }
            } else {
                throw new CoordinateOutOfBoundsException(move.toString());
            }
        } else {
            throw new GameOverException();
        }

        throw new IllegalMoveException(errorMessage);
    }

    public boolean isValidFlip(Move move) throws CoordinateOutOfBoundsException, GameOverException, IllegalMoveException {
        if(!game.getFinished()) {
            if(coordinatesInBounds(move)) {
                iPiece piece = game.getGameBoard().pieceAt(move.getTo());
                if(!piece.getIsFaceUp()) {
                    return true;
                } else {
                    throw new IllegalMoveException("Piece already face up \n" + move.toString());
                }
            } else {
                throw new CoordinateOutOfBoundsException(move.toString());
            }
        } else {
            throw new GameOverException();
        }
    }

    private boolean coordinatesInBounds(Move move) {
        return (move.getFrom().getRow() < game.getGameBoard().getRows() && move.getFrom().getRow() >= 0) && (move.getFrom().getColumn() < game.getGameBoard().getCols() && move.getFrom().getColumn() >= 0) && (move.getTo().getRow() < game.getGameBoard().getRows() && move.getTo().getRow() >= 0) && (move.getTo().getColumn() < game.getGameBoard().getCols() && move.getTo().getColumn() >= 0);
    }

    public boolean isPieceToEmpty(Move move) {
        // piece can travel to empty
        return (game.getGameBoard().pieceAt(move.getFrom()).getRank() > 0) && (isEmptySpace(move.getTo()));
    }

    private boolean isSameCoordinate(Coordinate fromCoordinate, Coordinate toCoordinate) {
        return (fromCoordinate.getRow() == toCoordinate.getRow()) && (fromCoordinate.getColumn() == toCoordinate.getColumn());
    }

    private boolean isEmptySpace(Coordinate coordinate) {
        return game.getGameBoard().pieceAt(coordinate).getRank() == 0;
    }

    private boolean isGeneralCapturingSoldier(Move move) {
        // GENERAL(7) can not capture SOLDIER(1)
        return (game.getGameBoard().pieceAt(move.getFrom()).getRank() == 7) && (game.getGameBoard().pieceAt(move.getTo()).getRank() == 1);
    }

    private boolean isSoldierCapturingGeneral(Move move) {
        // SOLDIER(1) can capture GENERAL(7)
        return (game.getGameBoard().pieceAt(move.getFrom()).getRank() == 1) && (game.getGameBoard().pieceAt(move.getTo()).getRank() == 7);
    }

    private boolean isSameColor(Move move) {
        return game.getGameBoard().pieceAt(move.getFrom()).getColor() == game.getGameBoard().pieceAt(move.getTo()).getColor();
    }

    private boolean areFaceUp(Move move) {
        return game.getGameBoard().pieceAt(move.getFrom()).getIsFaceUp() && game.getGameBoard().pieceAt(move.getTo()).getIsFaceUp();
    }

    private boolean canCapture(Move move) {
        return game.getGameBoard().pieceAt(move.getFrom()).compareTo(game.getGameBoard().pieceAt(move.getTo())) >= 0;
    }

    private boolean isValidHop(Move move) {
        int horizontalDelta = move.getFrom().getRow() > move.getTo().getRow() ? move.getFrom().getRow() - move.getTo().getRow() : move.getTo().getRow() - move.getFrom().getRow();
        int verticalDelta = move.getFrom().getColumn() > move.getTo().getColumn() ? move.getFrom().getColumn() - move.getTo().getColumn() : move.getTo().getColumn() - move.getFrom().getColumn();
        if((horizontalDelta != verticalDelta) && (horizontalDelta == 1 || verticalDelta == 1)) {
            return isVertical(move) || isHorizontal(move);
        } else {
            return false;
        }
    }

    private boolean isVertical(Move move) {
        return move.getFrom().getColumn() == move.getTo().getColumn();
    }

    private boolean isHorizontal(Move move) {
        return move.getFrom().getRow() == move.getTo().getRow();
    }
}
