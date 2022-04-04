package com.codecool.brothership.battleship;

public class Square {
    private final int x;
    private final int y;
    private SquareStatus status;

    public Square(int x, int y, SquareStatus status) {
        this.x = x;
        this.y = y;
        this.status = status;
    }

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
        this.status = SquareStatus.EMPTY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public SquareStatus getStatus() {
        return status;
    }
}
