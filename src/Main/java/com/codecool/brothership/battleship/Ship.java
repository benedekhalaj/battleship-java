package com.codecool.brothership.battleship;

public class Ship {
    private final ShipType type;
    private final Square[] coordinates;

    public Ship(ShipType type, Square[] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public ShipType getType() {
        return type;
    }

    public Square[] getCoordinates() {
        return coordinates;
    }
}
