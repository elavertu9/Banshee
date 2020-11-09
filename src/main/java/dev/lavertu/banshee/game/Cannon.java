package dev.lavertu.banshee.game;

public class Cannon implements iPiece {

	private boolean isCaptured = false;
	private boolean isFaceUp = false;
	private static final int RANK = 4;
	private Color color;
	private Coordinate position;

	@Override
	public iPiece capture(Coordinate coordinate) {
		isCaptured = true;
		position = coordinate;
		return this;
	}

	@Override
	public void setPosition(Coordinate coordinate) {
		position = coordinate;
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
	public Coordinate getPosition() {
		return position;
	}
}
