package com.codecool.brothership.battleship;

public class Square extends Coordinate{
    private SquareStatus status;

    public Square(int x, int y, SquareStatus status) {
        super(x, y);
        this.status = status;
    }

    public Square(int x, int y) {
        super(x, y);
        this.status = SquareStatus.EMPTY;
    }

    public int getX() {
        return super.getX();
    }

    public int getY() {
        return super.getY();
    }

    public SquareStatus getStatus() {
        return status;
    }
}
