package dev.lavertu.banshee.game.api;

import dev.lavertu.banshee.exception.api.GameMappingException;
import dev.lavertu.banshee.exception.api.UsernameNotFoundException;
import dev.lavertu.banshee.exception.api.ValidationException;
import dev.lavertu.banshee.game.Game;
import dev.lavertu.banshee.services.GamesService;
import dev.lavertu.banshee.services.UsersService;
import dev.lavertu.banshee.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@RequestMapping("/api")
@RestController
public class GameApi {

//    private static final Logger LOGGER = LogManager.getLogger(GameApi.class);
    private static final Logger LOGGER = LoggerFactory.getLogger(GameApi.class);

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

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Game> createGame(@RequestBody Map<String, String> payload) throws GameMappingException, UsernameNotFoundException, ValidationException {
        String player1Username = payload.get("player1Username");
        String player2Username = payload.get("player2Username");
        if (player1Username == null || player2Username == null) {
            String missingUsername = (player1Username == null) ? "player 1" : "player 2";
            String failureMessage = String.format("Username for %s not provided. 2 vaild usernames are required to play the game.", missingUsername);
            throw new ValidationException(failureMessage);
        }
        User player1 = usersService.getUserByUsername(player1Username);
        User player2 = usersService.getUserByUsername(player2Username);
        if (player1 == null || player2 == null) {
            String missingUsername = (player1 == null) ? player1Username : player2Username;
            String failureMessage = String.format("Username %s not found. User must be created prior to playing.", missingUsername);
            throw new UsernameNotFoundException(failureMessage);
        }
        Game game = gamesService.createGame(new Game(player1, player2));
        return ResponseEntity.ok().body(game);
    }

    @GetMapping(value = "/allgames", produces = "application/Json")
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> gameList = gamesService.getAllGames();
        return ResponseEntity.ok().body(gameList);
    }

    @GetMapping(value = "/id/{gameId}", produces = "application/Json")
    public ResponseEntity<Game> getGameByGameId(@PathVariable UUID gameId) {
        Game game = gamesService.getGameByGameId(gameId);
        return ResponseEntity.ok().body(game);
    }

    @GetMapping(value = "/player1Id/{player1Id}", produces = "application/Json")
    public ResponseEntity<List<Game>> getGamesByPlayer1Id(@PathVariable UUID player1Id) {
        List<Game> games = gamesService.getGamesByPlayer1Id(player1Id);
        return ResponseEntity.ok().body(games);
    }

    @GetMapping(value = "/player2Id/{player2Id}", produces = "application/Json")
    public ResponseEntity<List<Game>> getGamesByPlayer2Id(@PathVariable UUID player2Id) {
        List<Game> games = gamesService.getGamesByPlayer2Id(player2Id);
        return ResponseEntity.ok().body(games);
    }
}
