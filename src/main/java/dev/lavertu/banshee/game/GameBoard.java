package dev.lavertu.banshee.game;

import dev.lavertu.banshee.game.pieces.*;

import java.util.Stack;
import java.util.Collections;

public class GameBoard {
	private iPiece[][] board;
	private static final int COLS = 8;
	private static final int ROWS = 4;
	private Stack<iPiece> pieces;


	public GameBoard(){
		this.board = new iPiece[ROWS][COLS];
		this.pieces = new Stack<>();
		populatePieces();
		shuffleDeck();
		init();
	}

	private void populatePieces() {
		// 1 General, 2 Chariot, 2 Horse, 2 Cannon, 2 Advisor, 2 Elephant, 5 Soldier
		populateWhite();
		populateBlack();
	}

	private void populateWhite() {
		General whiteGeneral = new General(Color.WHITE);
		Chariot whiteChariot1 = new Chariot(Color.WHITE);
		Chariot whiteChariot2 = new Chariot(Color.WHITE);
		Horse whiteHorse1 = new Horse(Color.WHITE);
		Horse whiteHorse2 = new Horse(Color.WHITE);
		Cannon whiteCannon1 = new Cannon(Color.WHITE);
		Cannon whiteCannon2 = new Cannon(Color.WHITE);
		Advisor whiteAdvisor1 = new Advisor(Color.WHITE);
		Advisor whiteAdvisor2 = new Advisor(Color.WHITE);
		Elephant whiteElephant1 = new Elephant(Color.WHITE);
		Elephant whiteElephant2 = new Elephant(Color.WHITE);
		Soldier whiteSoldier1 = new Soldier(Color.WHITE);
		Soldier whiteSoldier2 = new Soldier(Color.WHITE);
		Soldier whiteSoldier3 = new Soldier(Color.WHITE);
		Soldier whiteSoldier4 = new Soldier(Color.WHITE);
		Soldier whiteSoldier5 = new Soldier(Color.WHITE);
		pieces.push(whiteGeneral);
		pieces.push(whiteChariot1);
		pieces.push(whiteChariot2);
		pieces.push(whiteHorse1);
		pieces.push(whiteHorse2);
		pieces.push(whiteCannon1);
		pieces.push(whiteCannon2);
		pieces.push(whiteAdvisor1);
		pieces.push(whiteAdvisor2);
		pieces.push(whiteElephant1);
		pieces.push(whiteElephant2);
		pieces.push(whiteSoldier1);
		pieces.push(whiteSoldier2);
		pieces.push(whiteSoldier3);
		pieces.push(whiteSoldier4);
		pieces.push(whiteSoldier5);
	}

	private void populateBlack() {
		General blackGeneral = new General(Color.BLACK);
		Chariot blackChariot1 = new Chariot(Color.BLACK);
		Chariot blackChariot2 = new Chariot(Color.BLACK);
		Horse blackHorse1 = new Horse(Color.BLACK);
		Horse blackHorse2 = new Horse(Color.BLACK);
		Cannon blackCannon1 = new Cannon(Color.BLACK);
		Cannon blackCannon2 = new Cannon(Color.BLACK);
		Advisor blackAdvisor1 = new Advisor(Color.BLACK);
		Advisor blackAdvisor2 = new Advisor(Color.BLACK);
		Elephant blackElephant1 = new Elephant(Color.BLACK);
		Elephant blackElephant2 = new Elephant(Color.BLACK);
		Soldier blackSoldier1 = new Soldier(Color.BLACK);
		Soldier blackSoldier2 = new Soldier(Color.BLACK);
		Soldier blackSoldier3 = new Soldier(Color.BLACK);
		Soldier blackSoldier4 = new Soldier(Color.BLACK);
		Soldier blackSoldier5 = new Soldier(Color.BLACK);
		pieces.push(blackGeneral);
		pieces.push(blackChariot1);
		pieces.push(blackChariot2);
		pieces.push(blackHorse1);
		pieces.push(blackHorse2);
		pieces.push(blackCannon1);
		pieces.push(blackCannon2);
		pieces.push(blackAdvisor1);
		pieces.push(blackAdvisor2);
		pieces.push(blackElephant1);
		pieces.push(blackElephant2);
		pieces.push(blackSoldier1);
		pieces.push(blackSoldier2);
		pieces.push(blackSoldier3);
		pieces.push(blackSoldier4);
		pieces.push(blackSoldier5);
	}

	private void shuffleDeck() {
		Collections.shuffle(pieces);
		Collections.shuffle(pieces);
	}

	private void init() {
		for(int row = 0; row < 4; row++) {
			for(int col = 0; col < 8; col++) {
				addPiece(new Coordinate(row, col), pieces.pop());
			}
		}
	}

	public iPiece pieceAt(Coordinate coordinate){
		return board[coordinate.getRow()][coordinate.getColumn()];
	}

	public void addPiece(Coordinate coordinate, iPiece piece){
		board[coordinate.getRow()][coordinate.getColumn()] = piece;
	}

	public void swapPieces(Coordinate fromCoordinate, Coordinate toCoordinate) {
		iPiece temp = pieceAt(toCoordinate);
		addPiece(toCoordinate, pieceAt(fromCoordinate));
		addPiece(fromCoordinate, temp);
	}

	public iPiece removePiece(Coordinate coordinate){
		iPiece piece = pieceAt(coordinate);
		addPiece(coordinate, new EmptyPiece());
		return piece;
	}

	public String toString(){
		String boardString = "- - - - - - - - - - - - - - - - -\n";
		for(int row = 0; row < 4; row++){
			boardString += "| ";
			for(int col = 0; col < 8; col++){
				if(pieceAt(new Coordinate(row, col)).isFaceUp()) {
					iPiece current = pieceAt(new Coordinate(row, col));
					String color = "";
					if(current.getColor() == Color.WHITE) {
						color = "W";
					}

					if(current.getColor() == Color.BLACK) {
						color = "B";
					}
					boardString += current.getRank() + color;
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
