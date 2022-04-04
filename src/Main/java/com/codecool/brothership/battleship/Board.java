package com.codecool.brothership.battleship;

public class Board {
    private final Square[][] ocean;

    public Board(Square[][] ocean) {
        this.ocean = ocean;
    }

    public boolean isPlaceOk(Square square) {
        return true;
    }
}
