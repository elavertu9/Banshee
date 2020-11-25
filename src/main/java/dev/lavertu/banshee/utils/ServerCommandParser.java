package dev.lavertu.banshee.utils;

import dev.lavertu.banshee.exception.IllegalMoveException;
import dev.lavertu.banshee.game.*;

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
            try {
                input = parser.nextLine();
                handleInput(input);
                System.out.println(game.printGameBoard());
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(String input) throws IllegalMoveException {
        String[] inputArray = input.split(" ");
        if(inputArray.length > 0) {
            String command = inputArray[0].toLowerCase();
            if(command.equals("flip") && inputArray.length == 2) {
                int[] coordinates = convertCoordinates(inputArray[1]);
                Move move = new Move(new Coordinate(coordinates[0], coordinates[1]), new Coordinate(coordinates[0], coordinates[1]), MoveType.FLIP);
                game.makeMove(move);
            } else if(command.equals("travel") && inputArray.length == 3) {
                int[] fromCoordinates = convertCoordinates(inputArray[1]);
                int[] toCoordinates = convertCoordinates(inputArray[2]);
                Move move = new Move(new Coordinate(fromCoordinates[0], fromCoordinates[1]), new Coordinate(toCoordinates[0], toCoordinates[1]), MoveType.TRAVEL);
                game.makeMove(move);
            } else if(command.equals("capture") && inputArray.length == 3) {
                int[] fromCoordinates = convertCoordinates(inputArray[1]);
                int[] toCoordinates = convertCoordinates(inputArray[2]);
                Move move = new Move(new Coordinate(fromCoordinates[0], fromCoordinates[1]), new Coordinate(toCoordinates[0], toCoordinates[1]), MoveType.CAPTURE);
                game.makeMove(move);
            } else if(command.equals("help")) {
                printUsage();
            } else if(command.equals("exit")) {
                closeParser();
                System.exit(0);
            } else if(command.equals("allflip")) {
                game.flipAll();
            } else {
                printUsage();
            }
        }
    }

    private int[] convertCoordinates(String coordinates) {
        String[] coordinateArray = coordinates.split(",");
        int[] converted = new int[2];
        converted[0] = Integer.parseInt(coordinateArray[0]);
        converted[1] = Integer.parseInt(coordinateArray[1]);
        return converted;
    }
    private void closeParser() {
        parser.close();
    }

    public Game getGame() {
        return game;
    }

    private void printUsage() {
        System.out.println("\nUsage: \n\tflip <row,col>\n\tcapture <from.row,from.col> <to.row,to.col>\n\ttravel <from.row,from.col> <to.row,to.col>\n\tallflip\n");
    }
}
