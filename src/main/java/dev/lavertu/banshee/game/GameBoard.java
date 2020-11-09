package dev.lavertu.banshee.game;

import dev.lavertu.banshee.game.Cannon;
import dev.lavertu.banshee.game.Color;
import dev.lavertu.banshee.game.Coordinate;
import dev.lavertu.banshee.game.iPiece;

public class GameBoard {
	private iPiece[][] board;
	private static final int COLS = 8;
	private static final int ROWS = 4;

	public GameBoard(){
		this.board = new iPiece[ROWS][COLS];
	}

	public iPiece pieceAt(Coordinate coordinate){
		return board[coordinate.row][coordinate.col];
	}

	public void addPiece(Coordinate coordinate, iPiece piece){
		board[coordinate.row][coordinate.col] = piece;
	}

	public iPiece removePiece(Coordinate coordinate){
		iPiece piece = pieceAt(coordinate);
		addPiece(coordinate, new EmptyPiece());
		return piece;
	}

	public String toString(){
		String boardString = "- - - - - - - - - - - - - - - - -\n";
		for(int i = 0; i < 4; i++){
			boardString += "| ";
			for(int j = 0; j < 8; j++){
				if(pieceAt(new Coordinate(i, j)).isFaceUp()) {
					boardString += pieceAt(new Coordinate(i, j)).getRank();
					boardString += " | ";
				}
				else{
					boardString += "X | ";
				}
			}
			boardString += "\n";
		}
		boardString += "- - - - - - - - - - - - - - - - -\n";
		return boardString;
	}

}
