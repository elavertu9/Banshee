package dev.lavertu.banshee.game;
import java.util.*;

public class Main
{
    // This class is for testing purposes
    public static void main(String[] args)
    {
    	GameBoard gb = new GameBoard();
    	for(int i = 0; i < 4; i++){
    	    for(int j = 0; j < 8; j++){
    	        gb.addPiece(new Coordinate(i, j), new EmptyPiece());
            }
        }
    	System.out.println(gb.toString());
    }
}
