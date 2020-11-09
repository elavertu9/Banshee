package dev.lavertu.banshee.game;

public class Coordinate { 

	int row, col;
	
	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return this.row;
	}

	public int getCol() {
		return this.col;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public String toString(){
		return "Row: " + getRow() + " Col: " + getCol();
	}
}
