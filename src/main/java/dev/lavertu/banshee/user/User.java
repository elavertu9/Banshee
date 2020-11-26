package dev.lavertu.banshee.user;

import dev.lavertu.banshee.game.pieces.iPiece;
import java.util.ArrayList;
import java.util.UUID;

public class User {

    private String name;
    private String userId;
    private ArrayList<iPiece> myPieces;
    private UserStats userStats;

    public User(String name) {
        this.name = name;
        this.userId = UUID.randomUUID().toString(); // temporary until DB
        this.userStats = new UserStats();
    }

    public String getName() {
        return name;
    }

    public ArrayList<iPiece> getMyPieces() {
        return myPieces;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "User: \n\t" + userId + "\n\t" + name;
    }
}
