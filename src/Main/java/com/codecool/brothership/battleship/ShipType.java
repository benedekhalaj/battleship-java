package com.codecool.brothership.battleship;

public enum ShipType {
    DESTROYER(2),
    SUBMARINE(3),
    CRUISER(3),
    BATTLESHIP(4),
    CARRIER(5);

    private final int length;

    ShipType(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
