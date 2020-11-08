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
		return new Cannon(); // placeholder piece
	}

	public void addPiece(iPiece p){

	}

	public iPiece removePiece(iPiece p){
		return new Cannon(); // placeholder piece
	}

	public String toString(){
		return "This is the board.";
	}

}
