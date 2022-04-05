package com.codecool.brothership.battleship;

public class Ship {
    private final ShipType type;
    private final Square[] squares;

    public Ship(ShipType type, Square[] squares) {
        this.type = type;
        this.squares = squares;
    }

    public ShipType getType() {
        return type;
    }

    public Square[] getSquares() {
        return squares;
    }
}
