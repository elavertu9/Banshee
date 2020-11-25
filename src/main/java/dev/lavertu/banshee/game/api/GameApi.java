package dev.lavertu.banshee.game.api;

import dev.lavertu.banshee.game.Game;
import dev.lavertu.banshee.user.User;
import dev.lavertu.banshee.utils.ServerCommandParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api")
public class GameApi {

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "") String name) {
        return String.format("Hello %s! The Banshee Server is up and running", name);
    }

    @PostMapping(value = "/create")
    public ResponseEntity createGame(
        @RequestParam String player1Username,
        @RequestParam String player2Username
    ) {
        User player1 = new User(player1Username);
        User player2 = new User(player2Username);
        Game game = new Game(player1, player2);
        return ResponseEntity.accepted().body(game.toString());
    }
}
