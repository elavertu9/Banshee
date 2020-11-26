package dev.lavertu.banshee;

import dev.lavertu.banshee.game.Game;
import dev.lavertu.banshee.user.User;
import dev.lavertu.banshee.utils.ServerCommandParser;
import org.springframework.boot.SpringApplication; // Do not delete even if not used
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class Main
{
    public static void commandParserTesting() {
        User player1 = new User(UUID.randomUUID(), "Player1", "Player", "One", "player1@gmail.com");
        User player2 = new User(UUID.randomUUID(), "Player2", "Player", "Two", "player2@gmail.com");
        Game game = new Game(player1, player2);
        System.out.println(game.toString());
        ServerCommandParser commandParser = new ServerCommandParser(game);
        commandParser.listen();
    }

    // This class is for testing purposes
    public static void main(String[] args) {
//        commandParserTesting();
        SpringApplication.run(Main.class, args);
    }
}
