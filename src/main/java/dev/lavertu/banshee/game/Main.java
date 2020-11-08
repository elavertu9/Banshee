package dev.lavertu.banshee.game;
import java.util.*;

public class Main
{
    // This class is for testing purposes
    public static void main(String[] args)
    {
        int whitePieces = 16;
        int blackPieces =16;
        Random r = new Random();

    	GameBoard gb = new GameBoard();
    	for(int i = 0; i < 4; i++){
    	    for(int j = 0; j < 8; j++){
    	        Coordinate c = new Coordinate(i, j);
                if((r.nextInt() % 2 == 0 && whitePieces > 0) || blackPieces == 0){
                    whitePieces--;

                }
            }
        }
    }
}
