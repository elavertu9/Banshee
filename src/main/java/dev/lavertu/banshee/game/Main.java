package dev.lavertu.banshee.game;

public class Main
{
    public static void commandParserTesting(Game game) {
        ServerCommandParser commandParser = new ServerCommandParser(game);
        commandParser.listen();
    }

    // This class is for testing purposes
    public static void main(String[] args)
    {
//    	GameBoard gb = new GameBoard();
//    	for(int i = 0; i < 4; i++){
//    	    for(int j = 0; j < 8; j++){
//    	        gb.addPiece(new Coordinate(i, j), new EmptyPiece());
//            }
//        }
//    	System.out.println(gb.toString());

        Player player1 = new Player("Evan");
        Player player2 = new Player("Alex");
        Game game = new Game(player1, player2);
        commandParserTesting(game);
    }
}
