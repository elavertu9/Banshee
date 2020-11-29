package dev.lavertu.banshee.game;

import javax.validation.constraints.NotNull;

public class Move {

	@NotNull
	private Coordinate to;
	@NotNull
	private Coordinate from;
	@NotNull
	private MoveType moveType;

	public Move(Coordinate from, Coordinate to, MoveType moveType) {
		this.to = to;
		this.from = from;
		this.moveType = moveType;
	}

	public Coordinate getTo() {
		return to;
	}

	public Coordinate getFrom() {
		return from;
	}

	public MoveType getMoveType() {
		return moveType;
	}

	@Override
	public String toString() {
		return moveType + ": from (" + from.getRow() + ", "
				+ from.getColumn() + ") to (" + to.getRow() + ", "
				+ to.getColumn() + ")\n";
	}
}
