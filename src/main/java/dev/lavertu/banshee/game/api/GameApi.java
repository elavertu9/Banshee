package dev.lavertu.banshee.game.api;

import dev.lavertu.banshee.exception.api.*;
import dev.lavertu.banshee.game.Game;
import dev.lavertu.banshee.services.GamesService;
import dev.lavertu.banshee.services.UsersService;
import dev.lavertu.banshee.user.User;
import dev.lavertu.banshee.utils.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;


//@RequestMapping("/api/game")
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

    // TODO - Move this to separate front end controller? Since it's not really part of the API
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "") String name) {
        return String.format("Hello %s! The Banshee Server is up and running", name);
    }

    @PostMapping(value = "/api/game/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Game> createGame(@RequestBody Map<String, String> payload) throws GameMappingException, EntityNotFoundException, ValidationException {
        Validator.validateCreateGameRequest(payload, usersService);
        String player1Username = payload.get("player1Username");
        String player2Username = payload.get("player2Username");
        User player1 = usersService.getUserByUsername(player1Username);
        User player2 = usersService.getUserByUsername(player2Username);
        Game game = gamesService.createGame(new Game(player1, player2));
        return ResponseEntity.ok().body(game);
    }

    @GetMapping(value = "/api/game/allgames", produces = "application/Json")
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> gameList = gamesService.getAllGames();
        return ResponseEntity.ok().body(gameList);
    }

    @GetMapping(value = "/api/game/allactivegames", produces = "application/Json")
    public ResponseEntity<List<Game>> getAllActiveGames() {
        List<Game> gameList = gamesService.getAllActiveGames();
        return ResponseEntity.ok().body(gameList);
    }

    @GetMapping(value = "/api/game/id/{gameId}", produces = "application/Json")
    public ResponseEntity<Game> getGameByGameId(@PathVariable UUID gameId) {
        Game game = gamesService.getGameByGameId(gameId);
        return ResponseEntity.ok().body(game);
    }

    @GetMapping(value = "/api/game/player1Id/{player1Id}", produces = "application/Json")
    public ResponseEntity<List<Game>> getGamesByPlayer1Id(@PathVariable UUID player1Id) throws EntityNotFoundException {
        Validator.validateUserByUserId(player1Id, usersService);
        List<Game> games = gamesService.getGamesByPlayer1Id(player1Id);
        return ResponseEntity.ok().body(games);
    }

    @GetMapping(value = "/api/game/player2Id/{player2Id}", produces = "application/Json")
    public ResponseEntity<List<Game>> getGamesByPlayer2Id(@PathVariable UUID player2Id) throws EntityNotFoundException {
        Validator.validateUserByUserId(player2Id, usersService);
        List<Game> games = gamesService.getGamesByPlayer2Id(player2Id);
        return ResponseEntity.ok().body(games);
    }
}
