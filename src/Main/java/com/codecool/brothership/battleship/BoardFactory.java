package com.codecool.brothership.battleship;

import java.util.List;

public class BoardFactory {
    private final int size;

    public BoardFactory(int size) {
        this.size = size;
    }

    public Board randomPlacement() {
        // TODO Implement random placement
        Square[][] board = new Square[size][size];
        return new Board(board);
    }

    public Board manualPlacement() {
        // Return empty board so player can initialize it
        Square[][] board = new Square[size][size];
        return new Board(board);
    }
}
