package com.codecool.brothership.battleship;

public enum GameMode {
    P_VS_P("1"),
    P_VS_AI("2"),
    AI_VS_AI("3");

    private final String inputId;

    GameMode(String inputId) {
        this.inputId = inputId;
    }

    public String getInputId() {
        return inputId;
    }
}
