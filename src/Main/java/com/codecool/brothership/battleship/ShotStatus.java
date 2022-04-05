package com.codecool.brothership.battleship;

public enum ShotStatus {
    HIT("Hit!"),
    SINK("Hit and Sink!"),
    MISS("Miss!"),
    INVALID("Invalid shot! Try again!");

    private final String message;

    ShotStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
