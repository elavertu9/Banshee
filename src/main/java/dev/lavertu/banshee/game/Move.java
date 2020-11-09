package dev.lavertu.banshee.game;

public class Move {

	private Coordinate to;
	private Coordinate from;
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

	public String toString() {
		return moveType + ": from (" + from.getRow() + ", "
				+ from.getColumn() + ") to (" + to.getRow() + ", "
				+ to.getColumn() + ")\n";
	}
}
