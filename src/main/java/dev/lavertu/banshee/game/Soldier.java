package dev.lavertu.banshee.game;

public class Soldier implements iPiece {

	private boolean isCaptured = false;
	private boolean isFaceUp = false;
	private int rank = 1;
	private Color color;
	private Coordinate position;

	@Override
	public iPiece capture(Coordinate coordinate) {
		isCaptured = true;

		return this;
	}

	@Override
	public void setPosition(Coordinate coordinate) {

	}

	@Override
	public boolean isCaptured() {
		return false;
	}

	@Override
	public boolean isFaceUp() {
		return false;
	}

	@Override
	public int getRank() {
		return 1;
	}

	@Override
	public Color getColor() {
		return null;
	}

	@Override
	public Coordinate getPosition() {
		return new Coordinate(0, 0);
	}
}
