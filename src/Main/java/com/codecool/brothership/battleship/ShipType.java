package com.codecool.brothership.battleship;

public enum ShipType {
    CARRIER(2),
    CRUISER(3),
    BATTLESHIP(4),
    DESTROYER(5);

    private final int length;

    ShipType(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
