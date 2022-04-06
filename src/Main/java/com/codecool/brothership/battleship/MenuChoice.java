package com.codecool.brothership.battleship;

public enum MenuChoice {
    PLAY("1"),
    EXIT("0");

    private final String inputId;

    MenuChoice(String inputId) {
        this.inputId = inputId;
    }

    public String getInputId() {
        return inputId;
    }
}
