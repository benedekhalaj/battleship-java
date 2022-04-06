package com.codecool.brothership.battleship;

import java.util.*;

public class BoardFactory {

    public Board[][] randomPlacement(int rows, int cols) {
        return new Board[rows][cols];
    }

    public List<WaterSquare> manualPlacement(int size, List<Ship> ships) {
        List<WaterSquare> ocean = new ArrayList<>();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (isSquareEmpty(x, y, ships)) {
                    ocean.add(new WaterSquare(x, y));
                }
            }
        }
        return ocean;
    }
    private boolean isSquareEmpty(int x, int y, List<Ship> ships) {
        for (Ship ship : ships) {
            for (Square square : ship.getSquares()) {
                if (square.getX() == x && square.getY() == y) {
                    return false;
                }
            }
        }
        return true;
    }
}