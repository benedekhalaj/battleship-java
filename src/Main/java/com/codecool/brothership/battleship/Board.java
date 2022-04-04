package com.codecool.brothership.battleship;

public class Board {
    private final Square[][] ocean;

    public Board(int rows, int cols) {
        this.ocean = new Square[rows][cols];
    }

    public boolean isPlaceOk(Square square) {
        return true;
    }
}
