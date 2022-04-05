package com.codecool.brothership.battleship;

public class ShipSquare extends Square{
    private SquareStatus status;

    public SquareStatus getStatus() {
        return status;
    }

    public ShipSquare(int x, int y) {
        super(x, y);
        this.status = SquareStatus.SHIP;
    }

    public void changeStatus(SquareStatus squareStatus) {
        this.status = squareStatus;
    }
}
