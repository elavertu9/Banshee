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

	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	@Override
	public String toString() {
		return "Row: " + getRow() + " Col: " + getColumn();
	}
}
