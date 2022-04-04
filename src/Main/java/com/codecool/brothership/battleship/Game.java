package com.codecool.brothership.battleship;

import com.codecool.brothership.utilities.Display;
import com.codecool.brothership.utilities.Input;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Display display;
    private final Input input;
    private final GameMode gameMode;
    private final AiType aiType;
    private PlayerType player1Type;
    private PlayerType player2Type;

    public Game(Display display, Input input, GameMode gameMode, AiType aiType) {
        this.display = display;
        this.input = input;
        this.gameMode = gameMode;
        this.aiType = aiType;
        setPlayerTypes();
    }

    public Game(Display display, Input input, GameMode gameMode) {
        this.display = display;
        this.input = input;
        this.gameMode = gameMode;
        this.aiType = null;
        setPlayerTypes();
    }

    public void play() {
        // TODO game loop here
        Player player1 = placeShipsForPlayer(player1Type);
        Player player2 = placeShipsForPlayer(player2Type);
        boolean isRunning = true;
        CurrentPlayer currentPlayer = null;
        while (isRunning) {
            currentPlayer = switchPlayer(currentPlayer);
            playRound();
            isRunning = false;
        }
        System.out.println("playing in progress");
    }

    private CurrentPlayer switchPlayer(CurrentPlayer currentPlayer) {
        if (currentPlayer == CurrentPlayer.PLAYER1) {
            return CurrentPlayer.PLAYER2;
        } else {
            return CurrentPlayer.PLAYER1;
        }
    }

    private Player placeShipsForPlayer(PlayerType playerType) {
        List<Ship> ships;
        if (playerType == PlayerType.PLAYER) {
            ShipPlacementType placementType = input.getPlacementType();
            if (placementType == ShipPlacementType.MANUAL) {
                ships = placeShipsManually();
            } else {
                ships = placeShipsRandomly();
            }
        } else {
            ships = placeShipsRandomly();
        }
        return new Player(ships);
    }

    private List<Ship> placeShipsManually() {
        // TODO place ships manually in a loop
        return new ArrayList<>();
    }

    private List<Ship> placeShipsRandomly() {
        // TODO place ships randomly
        return new ArrayList<>();
    }

    private void setPlayerTypes() {
        switch (gameMode) {
            case P_VS_P:
                player1Type = PlayerType.PLAYER;
                player2Type = PlayerType.PLAYER;
                break;
            case P_VS_AI:
                player1Type = PlayerType.PLAYER;
                player2Type = PlayerType.AI;
                break;
            case AI_VS_AI:
                player1Type = PlayerType.AI;
                player2Type = PlayerType.AI;
        }
    }

    private void playRound() {
        // TODO players make moves.
    }

    private boolean hasWon() {
        //TODO return true if the game has ended by rules
        return false;
    }
}
