package dev.lavertu.banshee.user;

import dev.lavertu.banshee.game.pieces.iPiece;
import java.util.ArrayList;
import java.util.UUID;

public class User {

    private String name;
    private String playerId;
    private ArrayList<iPiece> myPieces;
    private UserStats playerStats;

    public User(String name) {
        this.name = name;
        this.playerId = UUID.randomUUID().toString(); // temporary until DB
        this.playerStats = new UserStats();
    }

    public String getName() {
        return name;
    }

    public ArrayList<iPiece> getMyPieces() {
        return myPieces;
    }

    public String getPlayerId() {
        return playerId;
    }

    @Override
    public String toString() {
        return "Player: \n\t" + playerId + "\n\t" + name;
    }
}
