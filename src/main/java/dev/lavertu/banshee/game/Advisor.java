package dev.lavertu.banshee.game;

public class Advisor implements iPiece{

	@Override
	public iPiece capture(Coordinate c) {
		return null;
	}

	@Override
	public void move(Coordinate c) {

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
		return 0;
	}

	@Override
	public Color getColor() {
		return null;
	}

	@Override
	public Coordinate getPosition() {
		return null;
	}
}
