package dev.lavertu.banshee.game;

public class Coordinate { 

	private int row;
	private int column;
	
	public Coordinate(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	@Override
	public String toString() {
		return "Row: " + getRow() + " Col: " + getColumn();
	}
}
