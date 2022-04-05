package com.codecool.brothership.battleship;

public class Board {
    private final Square[][] ocean;

    public Board(int size) {
        ocean = new Square[size][size];
        for (int y = 0; y < ocean.length; y++) {
            for (int x = 0; x < ocean[y].length; x++) {
                ocean[y][x] = new Square(x, y);
            }
        }
    }

    public void addShip(Ship ship) {
        for (Square square : ship.getCoordinates()) {
            int x = square.getX();
            int y = square.getY();
            ocean[y][x] = square;
        }
    }

    public Square[][] getOcean() {
        return ocean;
    }

    public boolean isPlaceOk(Square square) {
        return true;
    }
}
