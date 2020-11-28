package dev.lavertu.banshee.repository;

import dev.lavertu.banshee.game.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public class GamesRepository {
//    private static final Logger LOGGER = LogManager.getLogger(GamesRepository.class);
    private static final Logger LOGGER = LoggerFactory.getLogger(GamesRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    public List<Game> getAllGames() {
        String hql = "SELECT g FROM Game as g";
        TypedQuery<Game> query = entityManager.createQuery(hql, Game.class);
        return query.getResultList();
    }

    public List<Game> getAllActiveGames() {
        String hql = "SELECT g FROM Game as g WHERE g.finished = FALSE";
        TypedQuery<Game> query = entityManager.createQuery(hql, Game.class);
        return query.getResultList();
    }

    public Game getGameByGameId(UUID gameId) {
        String hql = "SELECT g FROM Game g WHERE g.gameId = :gameId";
        TypedQuery<Game> query = entityManager.createQuery(hql, Game.class);
        query.setParameter("gameId", gameId);
        List<Game> gameList = query.getResultList();
        return (gameList == null || gameList.isEmpty()) ? null : gameList.get(0);
    }

    public List<Game> getGamesByPlayer1Id(UUID player1Id) {
        String hql = "SELECT g FROM Game g WHERE g.player1.userId = :player1Id";
        TypedQuery<Game> query = entityManager.createQuery(hql, Game.class);
        query.setParameter("player1Id", player1Id);
        return query.getResultList();
    }

    public List<Game> getGamesByPlayer2Id(UUID player2Id) {
        String hql = "SELECT g FROM Game g WHERE g.player2.userId = :player2Id";
        TypedQuery<Game> query = entityManager.createQuery(hql, Game.class);
        query.setParameter("player2Id", player2Id);
        return query.getResultList();
    }

    public void saveGame(Game game) {
        entityManager.persist(game);
    }
}
