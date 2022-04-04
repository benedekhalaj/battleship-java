package com.codecool.brothership.battleship;

import com.codecool.brothership.utilities.Display;
import com.codecool.brothership.utilities.Input;

public class Game {
    private final Display display;
    private final Input input;
    private final GameMode gameMode;
    private final AiType aiType;

    public Game(Display display, Input input, GameMode gameMode, AiType aiType) {
        this.display = display;
        this.input = input;
        this.gameMode = gameMode;
        this.aiType = aiType;
    }

    public Game(Display display, Input input, GameMode gameMode) {
        this.display = display;
        this.input = input;
        this.gameMode = gameMode;
        this.aiType = null;
    }

    public void play() {
        // TODO game loop here
        System.out.println("playing in progress");
    }

    private void playRound() {
        // TODO players make moves.
    }

    private boolean hasWon() {
        //TODO return true if the game has ended by rules
        return false;
    }
}
