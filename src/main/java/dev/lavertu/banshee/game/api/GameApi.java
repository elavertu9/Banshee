package dev.lavertu.banshee.game.api;

import dev.lavertu.banshee.exception.api.UsernameNotFoundException;
import dev.lavertu.banshee.game.Game;
import dev.lavertu.banshee.services.GamesService;
import dev.lavertu.banshee.services.UsersService;
import dev.lavertu.banshee.user.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/api")
@RestController
public class GameApi {

    private static final Logger LOGGER = LogManager.getLogger(GameApi.class);

    UsersService usersService;
    GamesService gamesService;

    @Autowired
    public void setUserService(UsersService usersService) {
        this.usersService = usersService;
    }

    @Autowired
    public void setGamesService(GamesService gamesService) {
        this.gamesService = gamesService;
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "") String name) {
        return String.format("Hello %s! The Banshee Server is up and running", name);
    }

    // Convert to post mapping when ready
    @GetMapping(value = "/create")
    public ResponseEntity<String> createGame(
        @RequestParam String player1Username,
        @RequestParam String player2Username
    ) throws UsernameNotFoundException {
        User player1 = usersService.getUserByUsername(player1Username);
        User player2 = usersService.getUserByUsername(player2Username);
        if (player1 == null || player2 == null) {
            String missingUsername = (player1 == null) ? player1Username : player2Username;
            String failureMessage = String.format("Username %s not found. User must be created prior to playing.", missingUsername);
            throw new UsernameNotFoundException(failureMessage);
        }
        Game game = new Game(player1, player2);
        return ResponseEntity.accepted().body(game.toString());
    }

    @GetMapping(value = "/allgames", produces = "application/Json")
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> gameList = gamesService.getAllGames();
        return ResponseEntity.ok().body(gameList);
    }
}
