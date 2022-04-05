package com.codecool.brothership.battleship;

public class ShipSquare extends Square{
    private ShipSquareStatus status;

    public ShipSquareStatus getStatus() {
        return status;
    }

    public ShipSquare(int x, int y) {
        super(x, y);
        this.status = ShipSquareStatus.SHIP;
    }

    public void changeStatus(ShipSquareStatus squareStatus) {
        this.status = squareStatus;
    }
}
