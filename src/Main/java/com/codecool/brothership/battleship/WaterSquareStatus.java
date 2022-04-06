package com.codecool.brothership.battleship;

public enum WaterSquareStatus {
    EMPTY("\uD83D\uDD35"),
    SHIP_PLACE(),
    MISSED("⚫");

    private final String character;

    WaterSquareStatus(String status) {
        this.character = status;
    }

    WaterSquareStatus() {
        this.character = "";
    }

    public String getCharacter() {
        return character;
    }
}
