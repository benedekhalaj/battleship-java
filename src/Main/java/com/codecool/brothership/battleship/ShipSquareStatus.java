package com.codecool.brothership.battleship;

public enum ShipSquareStatus {
    SHIP("\uD83C\uDFC0"),
    HIT("\uD83D\uDCA5"),
    SUNK("✖");

    private final String character;

    ShipSquareStatus(String character) {
        this.character = character;
    }

    public String getCharacter() {
        return character;
    }
}
