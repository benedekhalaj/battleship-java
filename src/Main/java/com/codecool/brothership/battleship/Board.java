package com.codecool.brothership.battleship;

public class Board {
    private final Square[][] ocean;

    public Board(Square[][] ocean) {
        this.ocean = ocean;
    }

    public void addShip(Ship ship) {
        // TODO Implement method to add Ship to ocean from ship squares
    }

    public Square[][] getOcean() {
        return ocean;
    }

    public boolean isPlaceOk(Square square) {
        return true;
    }
}
