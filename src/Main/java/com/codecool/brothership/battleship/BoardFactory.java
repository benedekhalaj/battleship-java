package com.codecool.brothership.battleship;

public class BoardFactory {

    public static Board[][] randomPlacement(int rows, int cols) {
        return new Board[rows][cols];
    }

    public static Board[][] manualPlacement(int rows, int cols) {
        return new Board[rows][cols];
    }

}
