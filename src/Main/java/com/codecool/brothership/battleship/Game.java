package com.codecool.brothership.battleship;

import com.codecool.brothership.utilities.Display;
import com.codecool.brothership.utilities.Input;
import com.codecool.brothership.utilities.Validator;

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
        final PlayerType[] playerTypes = getPlayerTypes();
        final PlayerType playerType1 = playerTypes[0];
        final PlayerType playerType2 = playerTypes[1];
        final Player player1 = createPlayer(PlayerId.PLAYER1, playerType1);
        final Player player2 = createPlayer(PlayerId.PLAYER2, playerType2);

        display.printMessage("Placement phase...");
        placeShipsForPlayer(player1);
        placeShipsForPlayer(player2);

        display.printMessage("Shooting phase...");
        boolean isRunning = true;
        Player currentPlayer = null;
        Player opponentPlayer = null;
        while (isRunning) {
            currentPlayer = (currentPlayer == null || currentPlayer.getId() == PlayerId.PLAYER2) ? player1 : player2;
            opponentPlayer = (opponentPlayer == null || currentPlayer.getId() == PlayerId.PLAYER1) ? player2 : player1;
            playRound(currentPlayer, opponentPlayer);
            boolean hasWon = !isPlayerAlive(opponentPlayer);
            if (hasWon) {
                isRunning = false;
            }
        }
        display.printMessage(currentPlayer.getId().toString() + " has won the game!");
    }

    private Player createPlayer(PlayerId playerId, PlayerType playerType) {
        if (playerType == PlayerType.HUMAN) {
            return new Player(playerId, playerType);
        } else {
            return new ComputerPlayer(playerId, aiType);
        }
    }

    private void playRound(Player player, Player opponent) {
        // TODO Display board, current player, etc.
        boolean isValid = false;
        while (!isValid) {
            display.printBattlefield(player.getBoardRows(), opponent.getBoardRows(), BOARD_SIZE);
            Coordinate shotCoordinate = (player.getType() == PlayerType.HUMAN) ? getShotCoordinate(): player.shoot();
            ShotStatus shotStatus = opponent.getShotStatus(shotCoordinate);
            display.printMessage(shotStatus.getMessage());
            if (shotStatus != ShotStatus.INVALID) {
                isValid = true;
            }
        }
    }

    private Coordinate getShotCoordinate() {
        display.printMessage("Make a shot!");
        Coordinate coordinate = null;
        boolean isValid = false;
        while (!isValid) {
            display.printInputMessage("Shot coordinate: ");
            String userInput = input.getInput();
            if (!input.isCoordinatesFormatValid(userInput)) {
                display.printMessage("Invalid input!");
                continue;
            }
            coordinate = convertInputToCoordinate(userInput);
            if (!isCoordinateInBoard(coordinate.getX(), coordinate.getY())) {
                display.printMessage("Coordinate out of battlefield!");
            } else {
                isValid = true;
            }
        }
        return coordinate;
    }

    private void placeShipsForPlayer(Player player) {
        List<Ship> ships = new ArrayList<>();
        ShipPlacementType placementType = getShipPlacementType();
        if (player.getType() == PlayerType.HUMAN && placementType == ShipPlacementType.MANUAL) {
            for (ShipType shipType : ShipType.values()) {
                display.printPlacementBoard(ships, BOARD_SIZE);
                display.printMessage("Ship length: " + shipType.getLength());
                int shipLength = shipType.getLength();
                ShipSquare[] squares = getShipSquares(shipLength, ships);
                Ship ship = new Ship(shipType, squares);
                ships.add(ship);
            }
            player.createBoard(BOARD_SIZE, ships);
        } else {
            player.createRandomBoard(BOARD_SIZE);
        }
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

    private ShipSquare[] getShipSquares( int shipLength, List<Ship> ships) {
        ShipDirection shipDirection = getShipDirection();
        display.printInputMessage("Choose a coordinate!");
        ShipSquare[] squares = null;
        boolean isValid = false;
        while (!isValid) {
            display.printMessage("Coordinate: ");
            String userInput = input.getInput();
            if (!input.isCoordinatesFormatValid(userInput)) {
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

    private ShipSquare[] createShipSquares(Coordinate coordinate, ShipDirection shipDirection, int shipLength) {
        ShipSquare[] shipSquares = new ShipSquare[shipLength];
        int x = coordinate.getX();
        int y = coordinate.getY();
        boolean isIncrementX = shipDirection.getDirection().equals(ShipDirection.HORIZONTAL.getDirection());
        for (int i = 0; i < shipLength; i++) {
            ShipSquare square = new ShipSquare(x, y);
            if (isIncrementX){
                x++;
            } else {
                y++;
            }
            shipSquares[i] = square;
        }
        return shipSquares;
    }

    private Coordinate convertInputToCoordinate(String userInput) {
        int asciiUpperLetterNum = 65;
        int y =(int) userInput.toUpperCase().charAt(0) - asciiUpperLetterNum;
        int x = Integer.parseInt(userInput.substring(1)) - 1;
        return new Coordinate(x, y);
    }

    private boolean isCoordinatesValid(Coordinate starterCoordinate, ShipDirection shipDirection, int shipLength, List<Ship> ships) {
        int starterX = starterCoordinate.getX();
        int starterY = starterCoordinate.getY();
        boolean isIncrementX = shipDirection.getDirection().equals(ShipDirection.HORIZONTAL.getDirection());
        for (int i = 0; i < shipLength; i++) {

            if (!isCoordinateInBoard(starterX, starterY)) {
                display.printMessage("Coordinate is out of battlefield");
                return false;
            }
            for (Ship ship : ships) {
                if (!Validator.isNeighbourSquaresEmpty(starterX, starterY, ship)) {
                    display.printMessage("Fields not available!");
                    return false;
                }
            }
            if (isIncrementX){
                starterX++;
            } else {
                starterY++;
            }
        }
        return true;
    }

    private boolean isCoordinateInBoard(int starterX, int starterY) {
        return starterX < BOARD_SIZE && starterY < BOARD_SIZE && starterX >= 0 && starterY >= 0;
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

    private boolean isPlayerAlive(Player player) {
        return player.isAlive();
    }
}
