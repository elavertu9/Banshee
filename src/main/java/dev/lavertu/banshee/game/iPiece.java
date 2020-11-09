package dev.lavertu.banshee.game;

public interface iPiece{

	public iPiece capture(Coordinate c);
	public void move(Coordinate c);
	public boolean isCaptured();
	public boolean isFaceUp();
	public int getRank();
	public Color getColor();
	public Coordinate getPosition();
	public String toString();

}