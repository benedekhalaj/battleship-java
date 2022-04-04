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
    private static final int BOARD_SIZE = 10;
    private final BoardFactory boardFactory = new BoardFactory(BOARD_SIZE);
    private PlayerType player1Type;
    private PlayerType player2Type;
    private Board player1Board;
    private Board player2Board;
    private Player player1;
    private Player player2;

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

        display.printMessage("Placement phase...");
        placeShipsForPlayer(CurrentPlayer.PLAYER1);
        placeShipsForPlayer(CurrentPlayer.PLAYER2);

        display.printMessage("Shooting phase...");
        boolean isRunning = true;
        CurrentPlayer currentPlayer = null;
        while (isRunning) {
            currentPlayer = switchPlayer(currentPlayer);
            playRound(currentPlayer);
            if (hasWon(currentPlayer)) {
                isRunning = false;
            }
        }
        System.out.println(currentPlayer + " has won!");
    }

    private void playRound(CurrentPlayer currentPlayer) {
        // TODO players make moves.
        display.printInputMessage("Shoot: ");
        String userInput = input.getInput();
        display.printMessage(userInput);
    }

    private void placeShipsForPlayer(CurrentPlayer currentPlayer) {
        PlayerType playerType = (currentPlayer == CurrentPlayer.PLAYER1) ? player1Type : player2Type;
        Board board = null;
        List<Ship> ships = new ArrayList<>();
        ShipPlacementType placementType = getShipPlacementType();
        if (playerType == PlayerType.PLAYER && placementType == ShipPlacementType.MANUAL) {
            board = boardFactory.manualPlacement();
            for (ShipType shipType : ShipType.values()) {
                display.printBoard(board.getOcean());
                int shipLength = shipType.getLength();
                ShipDirection shipDirection = getShipDirection();
                Coordinates coordinates = getShipCoordinates(shipDirection, shipLength);
                Square[] squares = getShipSquares(coordinates, shipDirection, shipLength);
                Ship ship = new Ship(shipType, squares);
                ships.add(ship);
                board.addShip(ship);
            }
        } else {
            // TODO Implement random
            System.out.println("Random :)");
        }
        setShipsForPlayer(currentPlayer, board, ships);
    }

    private void setShipsForPlayer(CurrentPlayer currentPlayer, Board board, List<Ship> ships) {
        if (currentPlayer == CurrentPlayer.PLAYER1) {
            this.player1Board = board;
            this.player1 = new Player(ships);
        } else if (currentPlayer == CurrentPlayer.PLAYER2) {
            this.player2Board = board;
            this.player2 = new Player(ships);
        }
    }

    private Square[] getShipSquares(Coordinates coordinates, ShipDirection shipDirection, int shipLength) {
        // TODO Implement getting ShipSquares from arguments
        return new Square[shipLength];
    }

    private ShipPlacementType getShipPlacementType() {
        display.printMessage("Choose a placement type: manual or random?");
        ShipPlacementType shipPlacementType = null;
        boolean isValid = false;
        while (!isValid) {
            display.printInputMessage("Placement type (m/r): ");
            String userInput = input.getInput();
            if (!input.isPlacementTypeValid(userInput)) {
                display.printMessage("Invalid placement type!");
            } else {
                isValid = true;
                shipPlacementType = input.getPlacementType(userInput);
            }
        }
        return shipPlacementType;
    }

    private ShipDirection getShipDirection() {
        display.printMessage("Choose a ship direction: horizontal or vertical?");
        ShipDirection shipDirection = null;
        boolean isValid = false;
        while (!isValid) {
            display.printInputMessage("Direction (h/v): ");
            String userInput = input.getInput();
            if (!input.isDirectionValid(userInput)) {
                display.printMessage("Invalid direction!");
            } else {
                isValid = true;
                shipDirection = input.getDirection(userInput);
            }
        }
        return shipDirection;
    }

    private Coordinates getShipCoordinates(ShipDirection shipDirection, int shipLength) {
        display.printInputMessage("Choose a coordinate!");
        Coordinates coordinates = null;
        boolean isValid = false;
        while (!isValid) {
            display.printInputMessage("Coordinate: ");
            String userInput = input.getInput();
            if (!input.isCoordinatesValid(userInput)) {
                display.printMessage("Invalid coordinate format");
                continue;
            } else {
                coordinates = input.getCoordinates();
            }
            if (!isCoordinatesInRange(coordinates, shipDirection, shipLength)) {
                display.printMessage("Out of range!");
            } else if (!isCoordinatesEmpty(coordinates)) {
                display.printMessage("Fields not available!");
            } else {
                isValid = true;
            }
        }
        return coordinates;
    }

    private boolean isCoordinatesEmpty(Coordinates coordinates) {
        // TODO Implement ship placement validation
        // Needs board(s) for checking availability
        return false;
    }

    private boolean isCoordinatesInRange(Coordinates coordinates, ShipDirection shipDirection, int shipLength) {
        // TODO Implement In range check for singular and multiple fields
        return false;
    }

    private CurrentPlayer switchPlayer(CurrentPlayer currentPlayer) {
        if (currentPlayer == CurrentPlayer.PLAYER1) {
            return CurrentPlayer.PLAYER2;
        } else {
            return CurrentPlayer.PLAYER1;
        }
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

    private boolean hasWon(CurrentPlayer currentPlayer) {
        //TODO return true if the game has ended by rules
        return false;
    }
}
