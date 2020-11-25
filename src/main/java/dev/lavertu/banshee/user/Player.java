package dev.lavertu.banshee.user;

import dev.lavertu.banshee.game.pieces.iPiece;

import java.util.ArrayList;

public class Player {

    private String name;
    private String playerId;
    private ArrayList<iPiece> myPieces;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<iPiece> getMyPieces() {
        return myPieces;
    }
}