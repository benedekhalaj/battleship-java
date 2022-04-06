package com.codecool.brothership.battleship;

import com.codecool.brothership.utilities.Display;
import com.codecool.brothership.utilities.Input;

public class Battleship {
    private final Display display = new Display();
    private final Input input = new Input();

    public void run() {
        // TODO this loop runs the game
        boolean isInputValid = false;
        MenuChoice menuChoice = null;
        display.printMenu();
        while (!isInputValid) {
            String userInput = input.getInput();
            menuChoice = MenuChoice.getMenuChoiceByUserInput(userInput);
            if (menuChoice != null) {
                isInputValid = true;
            } else {
                display.printMessage("Invalid input!");
            }
        }
        switch (menuChoice) {
            case PLAY:
                GameMode gameMode = getGameMode();
                Game game = new Game(display, input, gameMode);
                game.play();
                break;
            case EXIT:
                System.exit(0);
                break;
        }
    }

    private GameMode getGameMode() {
        boolean isInputValid = false;
        GameMode gameMode = null;
        while (!isInputValid) {
            display.printGameModes();
            String userInput = input.getInput();
            gameMode = GameMode.getGameModeByUserInput(userInput);
            if (gameMode != null) {
                isInputValid = true;
            } else {
                display.printMessage("Invalid input!");
            }
        }
        return gameMode;
    }
}
