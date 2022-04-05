package com.codecool.brothership.battleship;

public enum WaterSquareStatus {
    EMPTY("\uD83D\uDD35"),
    MISSED("⚫");

    private final String character;

    WaterSquareStatus(String status) {
        this.character = status;
    }

    public String getCharacter() {
        return character;
    }
}
