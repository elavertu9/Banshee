package dev.lavertu.banshee.repository;

import dev.lavertu.banshee.game.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class GamesRepository {
    private static final Logger LOGGER = LogManager.getLogger(GamesRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    public List<Game> getAllGames() {
        String hql = "SELECT g FROM Game as g";
        TypedQuery<Game> query = entityManager.createQuery(hql, Game.class);
        return query.getResultList();
    }

    public void saveGame(Game game) {
        entityManager.persist(game);
    }
}
