package dev.lavertu.banshee.services;

import dev.lavertu.banshee.repository.GamesRepository;
import dev.lavertu.banshee.game.Game;
//import org.apache.logging.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GamesService {

//    private static final Logger LOGGER = LogManager.getLogger(GamesService.class);
    private static final Logger LOGGER = LoggerFactory.getLogger(GamesService.class);

    @Autowired
    private GamesRepository gamesRepository;

    public Game createGame(Game game) {
        game.createUserId();
        gamesRepository.saveGame(game);
        return game;
    }

    public List<Game> getAllGames() {
        return gamesRepository.getAllGames();
    }

    public List<Game> getAllActiveGames() {
        return gamesRepository.getAllActiveGames();
    }


    public Game getGameByGameId(UUID gameId) {
        return gamesRepository.getGameByGameId(gameId);
    }

    public List<Game> getGamesByPlayer1Id(UUID player1Id) {
        return gamesRepository.getGamesByPlayer1Id(player1Id);
    }

    public List<Game> getGamesByPlayer2Id(UUID player2Id) {
        return gamesRepository.getGamesByPlayer2Id(player2Id);
    }
}
