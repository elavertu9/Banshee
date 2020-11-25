package dev.lavertu.banshee.user;

import dev.lavertu.banshee.game.pieces.iPiece;
import java.util.ArrayList;
import java.util.UUID;

public class Player {

    private String name;
    private String playerId;
    private ArrayList<iPiece> myPieces;
    private PlayerStats playerStats;

    public Player(String name) {
        this.name = name;
        this.playerId = UUID.randomUUID().toString(); // temporary until DB
        this.playerStats = new PlayerStats();
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
