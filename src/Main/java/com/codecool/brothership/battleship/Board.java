package com.codecool.brothership.battleship;

public class Board {
    private final Square[][] ocean;

    public Board(int size) {
        // TODO Implement filling board
        ocean = new Square[size][size];
        for (int y = 0; y < ocean.length; y++) {
            for (int x = 0; x < ocean[y].length; x++) {
                ocean[y][x] = new Square(x, y);
            }
        }
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
