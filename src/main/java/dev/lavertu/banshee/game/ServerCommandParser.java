package dev.lavertu.banshee.game;

import java.util.Scanner;

public class ServerCommandParser {

    private Scanner parser;
    private Game game;

    public ServerCommandParser(Game game) {
        this.parser = new Scanner(System.in);
        this.game = game;
    }

    public void listen() {
        String input;
        while(true) {
            input = getParser().nextLine();
            handleInput(input);
        }
    }

    private void handleInput(String input) {
        System.out.println(input);
    }

    public Scanner getParser() {
        return parser;
    }

    private void closeParser() {
        getParser().close();
    }

    public Game getGame() {
        return game;
    }
}
