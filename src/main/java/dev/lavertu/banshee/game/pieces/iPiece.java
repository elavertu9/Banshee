package dev.lavertu.banshee.game.pieces;

import dev.lavertu.banshee.game.Color;

public interface iPiece extends Comparable<iPiece> {

	public iPiece capture();

	public boolean isCaptured();

	public boolean isFaceUp();

	public int getRank();

	public Color getColor();

	public void flipPiece();

	public boolean getIsFaceUp();

	public String getPieceName();

	public String toString();

	public int compareTo(iPiece piece);
}