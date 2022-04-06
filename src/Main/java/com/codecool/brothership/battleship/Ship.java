package com.codecool.brothership.battleship;

public class Ship {
    private final ShipType type;
    private final ShipSquare[] squares;

    public Ship(ShipType type, ShipSquare[] squares) {
        this.type = type;
        this.squares = squares;
    }

    public ShipType getType() {
        return type;
    }

    public ShipSquare[] getSquares() {
        return squares;
    }
}
