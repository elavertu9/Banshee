package dev.lavertu.banshee.game.api;

import dev.lavertu.banshee.game.Game;
import dev.lavertu.banshee.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api")
@RestController
public class GameApi {

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "") String name) {
        return String.format("Hello %s! The Banshee Server is up and running", name);
    }

    @PostMapping(value = "/create")
    public ResponseEntity createGame(
        @RequestParam String user1Username,
        @RequestParam String user2Username
    ) {
        User user1 = new User(user1Username);
        User user2 = new User(user2Username);
        Game game = new Game(user1, user2);
        return ResponseEntity.accepted().body(game.toString());
    }
}
