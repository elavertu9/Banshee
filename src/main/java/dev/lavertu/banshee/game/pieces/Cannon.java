package dev.lavertu.banshee.game.pieces;

import dev.lavertu.banshee.game.Color;

public class Cannon implements iPiece {

	private boolean isCaptured = false;
	private boolean isFaceUp = false;
	private static final int RANK = 4;
	private Color color;

	public Cannon(Color color) {
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
		return "Cannon";
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
