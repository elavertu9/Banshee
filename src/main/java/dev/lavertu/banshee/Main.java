package dev.lavertu.banshee;

import dev.lavertu.banshee.game.Game;
import dev.lavertu.banshee.user.User;
import dev.lavertu.banshee.utils.ServerCommandParser;
import org.springframework.boot.SpringApplication; // Do not delete even if not used
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void commandParserTesting() {
        User user1 = new User("User 1");
        User user2 = new User("User 2");
        Game game = new Game(user1, user2);
        System.out.println(game.toString());
        ServerCommandParser commandParser = new ServerCommandParser(game);
        commandParser.listen();
    }

    // This class is for testing purposes
    public static void main(String[] args) {
        commandParserTesting();
        //SpringApplication.run(Main.class, args);
    }
}
