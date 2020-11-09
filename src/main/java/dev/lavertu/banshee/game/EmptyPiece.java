package dev.lavertu.banshee.game;

import dev.lavertu.banshee.game.Color;
import dev.lavertu.banshee.game.Coordinate;

public class EmptyPiece implements iPiece{
	private boolean isCaptured = false;
	private boolean isFaceUp = false;
	private int rank = 0;
	private Color color = Color.NEUTRAL;
	private Coordinate position;

	@Override
	public iPiece capture(Coordinate c) {
		return this;
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
