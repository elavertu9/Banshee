package dev.lavertu.banshee.game.api;

import dev.lavertu.banshee.exception.CoordinateOutOfBoundsException;
import dev.lavertu.banshee.exception.GameOverException;
import dev.lavertu.banshee.exception.IllegalMoveException;
import dev.lavertu.banshee.exception.api.*;
import dev.lavertu.banshee.game.Game;
import dev.lavertu.banshee.game.Move;
import dev.lavertu.banshee.services.GamesService;
import dev.lavertu.banshee.services.UsersService;
import dev.lavertu.banshee.user.User;
import dev.lavertu.banshee.utils.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Game> createGame(@RequestBody Map<String, String> payload) throws EntityNotFoundException, ValidationException {
        Validator.validateCreateGameRequest(payload, usersService);
        User user1 = usersService.getUserByUsername(payload.get("user1Username"));
        User user2 = usersService.getUserByUsername(payload.get("user2Username"));
        Game game = gamesService.createGame(new Game(user1, user2));
        return ResponseEntity.ok().body(game);
    }

    @PostMapping(value = "/api/game/move/{gameId}", produces = "application/json")
    public ResponseEntity<Game> makeMove(@PathVariable UUID gameId, @Valid @RequestBody Move move) throws GameOverException, IllegalMoveException, CoordinateOutOfBoundsException {
        Game game = gamesService.getGameByGameId(gameId);
        gamesService.makeMove(game, move);
        usersService.saveUser(game.getUser1());
        usersService.saveUser(game.getUser2());
        return ResponseEntity.ok().body(null);
    }

    @GetMapping(value = "/api/game/allGames", produces = "application/Json")
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> gameList = gamesService.getAllGames();
        return ResponseEntity.ok().body(gameList);
    }

    @GetMapping(value = "/api/game/allActiveGames", produces = "application/Json")
    public ResponseEntity<List<Game>> getAllActiveGames() {
        List<Game> gameList = gamesService.getAllActiveGames();
        return ResponseEntity.ok().body(gameList);
    }

    @GetMapping(value = "/api/game/gameId/{gameId}", produces = "application/Json")
    public ResponseEntity<Game> getGameByGameId(@PathVariable UUID gameId) {
        Game game = gamesService.getGameByGameId(gameId);
        return ResponseEntity.ok().body(game);
    }

    @GetMapping(value = "/api/game/userId/{userId}", produces = "application/Json")
    public ResponseEntity<List<Game>> getGamesByUserId(@PathVariable UUID userId) throws EntityNotFoundException {
        Validator.validateUserByUserId(userId, usersService);
        List<Game> games = gamesService.getGamesByUserId(userId);
        return ResponseEntity.ok().body(games);
    }
}
