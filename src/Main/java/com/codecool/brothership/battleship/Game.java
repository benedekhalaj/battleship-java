package com.codecool.brothership.battleship;

public class Game {
    private final GameMode gameMode;
    private final AiType aiType;

    public Game(GameMode gameMode, AiType aiType) {
        this.gameMode = gameMode;
        this.aiType = aiType;
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
