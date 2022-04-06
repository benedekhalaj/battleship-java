package com.codecool.brothership.battleship;

public class WaterSquare extends Square {
    private WaterSquareStatus status;

    public WaterSquare(int x, int y) {
        super(x, y);
        this.status = WaterSquareStatus.EMPTY;
    }

    public WaterSquare(int x, int y, WaterSquareStatus status) {
        super(x, y);
        this.status = status;
    }

    public void changeStatus(WaterSquareStatus squareStatus) {
        this.status = squareStatus;
    }

    public WaterSquareStatus getStatus() {
        return status;
    }
}
