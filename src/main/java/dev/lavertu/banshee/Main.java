package dev.lavertu.banshee;

import dev.lavertu.banshee.game.Game;
import dev.lavertu.banshee.user.Player;
import dev.lavertu.banshee.utils.ServerCommandParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Main
{
    public static void commandParserTesting() {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Game game = new Game(player1, player2);
        System.out.println(game.printGameBoard());
        ServerCommandParser commandParser = new ServerCommandParser(game);
        commandParser.listen();
    }

    // This class is for testing purposes
    public static void main(String[] args) {
        //commandParserTesting();
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/api/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "") String name) {
        return String.format("Hello %s! The Banshee Server is up and running", name);
    }
}
