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
        // Manual or Random CHECK
        // For every ship: CHECK
        // get Direction: Vertical or Horizontal CHECK
        // -validate Direction CHECK
        // get Coordinates: e.g. B7 CHECK
        // -validate Coordinates: regex, in range CHECK
        // -check if can place ships: is every piece within board, not colliding CHECK
        // get Square objects e.g.[3,6; 3,7; 3,8;],
        // get Ship(Square object)
        // ships.add(Ship object)
        // Board.addShip(Ship object)
        // TODO game loop here
        ShipPlacementType placementType = getShipPlacementType();
        List<Ship> ships = new ArrayList<>();
        if (placementType == ShipPlacementType.MANUAL) {
            for (ShipType shipType : ShipType.values()) {
                int shipLength = shipType.getLength();
                ShipDirection shipDirection = getShipDirection();
                Coordinates coordinates = getShipCoordinates(shipDirection, shipLength);
                Square[] squares = getShipSquares(coordinates, shipDirection, shipLength);
                Ship ship = new Ship(shipType, squares);
                ships.add(ship);

            }
        } else {
            System.out.println("Random :)");
        }

        List<Ship> player1Ships = new ArrayList<>();
        boolean isRunning = true;
        CurrentPlayer currentPlayer = null;
        while (isRunning) {
            currentPlayer = switchPlayer(currentPlayer);
            playRound();
            isRunning = false;
        }
        System.out.println("playing in progress");
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
            if (!coordinatesInRange(coordinates, shipDirection, shipLength)) {
                display.printMessage("Out of range!");
            } else if (!coordinatesAvailable(coordinates)) {
                display.printMessage("Fields not available!");
            } else {
                isValid = true;
            }
        }
        return coordinates;
    }

    private boolean coordinatesAvailable(Coordinates coordinates) {
        // TODO Implement ship placement validation
        // Needs board(s) for checking availability
        return false;
    }

    private boolean coordinatesInRange(Coordinates coordinates, ShipDirection shipDirection, int shipLength) {
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
