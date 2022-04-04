package com.codecool.brothership.battleship;

import java.util.List;

public class BoardFactory {
    private final int size;

    public BoardFactory(int size) {
        this.size = size;
    }

    public Board randomPlacement() {
        Square[][] board = new Square[size][size];
        return new Board(board);
    }

    public Board manualPlacement(List<Ship> ships) {
        Square[][] board = new Square[size][size];
        return new Board(board);
    }
}
