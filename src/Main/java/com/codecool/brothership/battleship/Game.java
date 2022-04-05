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
        // Could refactor this into an object
        final PlayerType[] playerTypes = getPlayerTypes();
        final PlayerType playerType1 = playerTypes[0];
        final PlayerType playerType2 = playerTypes[1];
        final Player player1 = new Player(PlayerId.PLAYER1, playerType1);
        final Player player2 = new Player(PlayerId.PLAYER2, playerType2);
        final Board board1 = new Board(BOARD_SIZE);
        final Board board2 = new Board(BOARD_SIZE);

        display.printMessage("Placement phase...");
        placeShipsForPlayer(player1, board1);
        placeShipsForPlayer(player2, board2);

        display.printMessage("Shooting phase...");
        boolean isRunning = true;
        Player currentPlayer = null;
        while (isRunning) {
            currentPlayer = (currentPlayer == null || currentPlayer.getId() == PlayerId.PLAYER2) ? player1 : player2;
            playRound(currentPlayer);
            if (hasWon(currentPlayer)) {
                isRunning = false;
            }
        }
    }

    private void playRound(Player player) {
        // TODO players make moves.
        display.printInputMessage("Shoot: ");
        String userInput = input.getInput();
        display.printMessage(userInput);
    }

    private void placeShipsForPlayer(Player player, Board board) {
        ShipPlacementType placementType = getShipPlacementType();
        if (player.getType() == PlayerType.HUMAN && placementType == ShipPlacementType.MANUAL) {
            for (ShipType shipType : ShipType.values()) {
                display.printBoard(board.getOcean());
                int shipLength = shipType.getLength();
                ShipDirection shipDirection = getShipDirection();
                Coordinate[] coordinates = getShipCoordinates(shipDirection, shipLength, player.getShips());
                Square[] squares = getShipSquares(coordinates, shipDirection, shipLength);
                Ship ship = new Ship(shipType, squares);
                player.addShip(ship);
                board.addShip(ship);
            }
        } else {
            // TODO Implement random
            System.out.println("Random :)");
        }
    }

    private Square[] getShipSquares(Coordinate[] coordinates, ShipDirection shipDirection, int shipLength) {
        // TODO Implement getting ShipSquares from arguments
        return new Square[shipLength];
    }

    private ShipPlacementType getShipPlacementType() {
        display.printMessage("Choose a placement type: manual or random?");
        ShipPlacementType shipPlacementType = null;
        boolean isValid = false;
        while (!isValid) {
            display.printInputMessage("Placement type Manual(m)/Random(r): ");
            //TODO Should we write a method, in input, what returns true if placement type is valid?
            String userInput = input.getInput();// Should we write a method, in input, what returns true if placement type is valid?
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

    private Coordinate[] getShipCoordinates(ShipDirection shipDirection, int shipLength, List<Ship> ships) {
        display.printInputMessage("Choose a coordinate!");
        Square[] squares = null;
        boolean isValid = false;
        while (!isValid) {
            display.printInputMessage("Coordinate: ");
            String userInput = input.getInput();
            if (!input.isCoordinatesValid(userInput)) {
                display.printMessage("Invalid coordinate format");
                continue;
            }
            Coordinate starterCoordinate = convertInputToCoordinate(userInput);
            if (isCoordinatesValid(starterCoordinate, shipDirection, shipLength, ships)) {
                squares = createShipSquares(starterCoordinate, shipDirection, shipLength);
                isValid = true;
            }
        }
        return squares;
    }

    private Square[] createShipSquares(Coordinate coordinate, ShipDirection shipDirection, int shipLength) {
        Square[] coordinates = new Square[shipLength];
        int x = coordinate.getX();
        int y = coordinate.getY();
        boolean isIncrementX = shipDirection.getDirection().equals(ShipDirection.HORIZONTAL.getDirection());
        for (int i = 0; i < shipLength; i++) {
            if (isIncrementX){
                x++;
            } else {
                y++;
            }
            Square square = new Square(x, y, SquareStatus.SHIP);
            coordinates[i] = square;
        }
        return coordinates;
    }

    private Coordinate convertInputToCoordinate(String userInput) {
        int asciiUpperLetterNum = 65;
        int x = asciiUpperLetterNum - (int) userInput.toUpperCase().charAt(0);
        int y = Integer.parseInt(userInput.substring(1));
        return new Coordinate(x, y);
    }

    private boolean isCoordinatesValid(Coordinate starterCoordinate, ShipDirection shipDirection, int shipLength, List<Ship> ships) {
        // TODO Implement In range check for singular and multiple fields
        int starterX = starterCoordinate.getX();
        int starterY = starterCoordinate.getY();
        boolean isIncrementX = shipDirection.getDirection().equals(ShipDirection.HORIZONTAL.getDirection());
        for (int i = 0; i < shipLength; i++) {
            if (isIncrementX){
                starterX++;
            } else {
                starterY++;
            }
            if (isCoordinateInBoard(starterX, starterY)) {
                display.printMessage("Coordinate is out of battlefield");
                return false;
            }
            for (Ship ship : ships) {
                if (!isNeighbourSquaresEmpty(starterX, starterY)) {
                    display.printMessage("Fields not available!");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isCoordinateInBoard(int starterX, int starterY) {
        return starterX >= BOARD_SIZE || starterY >= BOARD_SIZE;
    }

    private boolean isNeighbourSquaresEmpty(int starterX, int starterY) {
        return true;
    }

    private PlayerType[] getPlayerTypes() {
        PlayerType player1Type = null;
        PlayerType player2Type = null;
        switch (gameMode) {
            case P_VS_P:
                player1Type = PlayerType.HUMAN;
                player2Type = PlayerType.HUMAN;
                break;
            case P_VS_AI:
                player1Type = PlayerType.HUMAN;
                player2Type = PlayerType.AI;
                break;
            case AI_VS_AI:
                player1Type = PlayerType.AI;
                player2Type = PlayerType.AI;
        }
        return new PlayerType[]{player1Type, player2Type};
    }

    private boolean hasWon(Player player) {
        //TODO return true if the game has ended by rules
        return false;
    }
}
