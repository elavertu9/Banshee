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
        Player player1 = new Player("Evan");
        Player player2 = new Player("Paul");

        Game game = new Game(player1, player2);

        System.out.println(game.printGameBoard());

        commandParserTesting(game);
    }
}
