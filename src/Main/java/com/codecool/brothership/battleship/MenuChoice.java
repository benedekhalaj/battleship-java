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

    public static MenuChoice getMenuChoiceByUserInput(String userInput) {
        for (MenuChoice menuChoice : values()) {
            if (menuChoice.inputId.equals(userInput)) {
                return menuChoice;
            }
        }
        return null;
    }
}
