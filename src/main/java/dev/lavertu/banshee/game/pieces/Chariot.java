package dev.lavertu.banshee.game.pieces;

import dev.lavertu.banshee.game.Color;

public class Chariot implements iPiece {

	private boolean isCaptured = false;
	private boolean isFaceUp = false;
	private static final int RANK = 6;
	private Color color;

	public Chariot(Color color) {
		this.color = color;
	}

	@Override
	public iPiece capture() {
		isCaptured = true;
		return this;
	}

	@Override
	public boolean isCaptured() {
		return isCaptured;
	}

	@Override
	public boolean isFaceUp() {
		return isFaceUp;
	}

	@Override
	public int getRank() {
		return RANK;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void flipPiece() {
		isFaceUp = true;
	}

	@Override
	public boolean getIsFaceUp() {
		return isFaceUp;
	}

	@Override
	public String getPieceName() {
		return "Chariot";
	}

	@Override
	public String toString() {
		return getPieceName() + "(" + RANK + ")\n\tColor: " + color + "\n\tisCaptured: " + isCaptured + "\n\tisFaceUp: " + isFaceUp + "\n";
	}

	@Override
	public int compareTo(iPiece piece) {
		return this.getRank() - piece.getRank();
	}
}
