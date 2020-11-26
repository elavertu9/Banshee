//package dev.lavertu.banshee.repository;
//
//import dev.lavertu.banshee.game.Game;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.UUID;
//
//@Repository
//public interface GamesRepository extends JpaRepository<GamesRepository, UUID> {
//
//    @Query(
//            "SELECT g " +
//            "FROM games g " +
//            "WHERE LOWER(g.game_id) = LOWER(:username)"
//    )
//    Game getGameById(@Param("gameId") String gameId);
//}
