package com.codecool.brothership.battleship;

public class WaterSquare extends Square {
    private WaterSquareStatus status;

    public WaterSquare(int x, int y) {
        super(x, y);
        this.status = WaterSquareStatus.EMPTY;
    }

    public void changeStatus(WaterSquareStatus squareStatus) {
        this.status = squareStatus;
    }

    public WaterSquareStatus getStatus() {
        return status;
    }
}
