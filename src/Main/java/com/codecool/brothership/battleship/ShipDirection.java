package com.codecool.brothership.battleship;

public enum ShipDirection {
    HORIZONTAL("h"),
    VERTICAL("v");

    private final String direction;

    ShipDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}
