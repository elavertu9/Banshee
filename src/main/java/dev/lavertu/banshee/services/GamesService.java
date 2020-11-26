package dev.lavertu.banshee.services;

import dev.lavertu.banshee.repository.GamesRepository;
import dev.lavertu.banshee.game.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GamesService {

    private static final Logger LOGGER = LogManager.getLogger(GamesService.class);

    @Autowired
    private GamesRepository gamesRepository;

    public List<Game> getAllGames() {
        return gamesRepository.getAllGames();
    }
}
