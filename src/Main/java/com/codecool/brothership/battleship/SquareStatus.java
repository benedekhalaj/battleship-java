package com.codecool.brothership.battleship;

public enum SquareStatus {
    EMPTY("\uD83D\uDD35"),
    SHIP("⬜"),
    HIT("\uD83D\uDCA5"),
    SUNK("✖"),
    MISSED("⚫");

    private final String character;

    SquareStatus(String status) {
        this.character = status;
    }

    public String getCharacter() {
        return character;
    }
}
