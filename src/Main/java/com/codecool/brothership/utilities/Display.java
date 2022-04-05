package com.codecool.brothership.utilities;

import com.codecool.brothership.battleship.*;

import java.util.List;

public class Display {
    private static final int UPPER_CHAR_NUM = 65;
    private static final String SMALL_SEPARATOR = " ";
    private static final String MEDIUM_SEPARATOR = "  ";
    private static final String BIG_SEPARATOR = "   ";
    private static final String BIG_PLAYER_SEPARATOR = BIG_SEPARATOR + "|" + BIG_SEPARATOR;
    private static final String SMALL_PLAYER_SEPARATOR = SMALL_SEPARATOR + "|" + BIG_SEPARATOR;
    private static final String NEW_LINE = "\n";
    private static final int COORDINATE_SIGN = -1;

    public void printGameModes() {
        System.out.println("""
                Select game mode:
                1: Player VS Player
                2: Player VS AI
                3: AI VS AI""");
    }

    public void printMenu() {
        System.out.println("""
                Welcome to BrotherShip! Select an option:
                1: Play
                0: Exit""");
    }

    public void printResult() {
        // TODO prints the game result
    }

    public void printInputMessage(String message) {
        // Prints message for input without a newline.
        System.out.print(message);
    }

    public void printMessage(String message) {
        // Prints message with newline.
        System.out.println(message);
    }

    public void printPlacementBoard(List<Ship> ships, int boardSize) {
        for (int y = COORDINATE_SIGN; y < boardSize; y++) {
            StringBuilder displayedRow = new StringBuilder();
            for (int x = COORDINATE_SIGN; x < boardSize; x++) {
                String squareCharacter;
                boolean topLeftCorner = (y == COORDINATE_SIGN && x == COORDINATE_SIGN);
                boolean characterCoordinatesLine = (x == COORDINATE_SIGN);
                boolean numberCoordinatesLine = (y == COORDINATE_SIGN);
                if (topLeftCorner) {
                    squareCharacter = MEDIUM_SEPARATOR;
                } else if (numberCoordinatesLine) {
                    squareCharacter = (x + 1 < 10) ? (x + 1) + SMALL_SEPARATOR : String.valueOf((x + 1));
                } else if (characterCoordinatesLine) {
                    squareCharacter = Character.toString(UPPER_CHAR_NUM + y) + SMALL_SEPARATOR;
                } else {
                    if (isShipAtCoordinate(ships, y, x)) {
                        squareCharacter = ShipSquareStatus.SHIP.getCharacter();
                    } else {
                        squareCharacter = WaterSquareStatus.EMPTY.getCharacter();
                    }
                }
                displayedRow.append(squareCharacter);
            }
            System.out.println(displayedRow);
        }
    }

    private boolean isShipAtCoordinate(List<Ship> ships, int y, int x) {
        for (Ship ship : ships) {
            for (ShipSquare shipSquare : ship.getSquares()) {
                if (shipSquare.getX() == x && shipSquare.getY() == y) {
                    return true;
                }
            }
        }
        return false;
    }

    public void printBattlefield(List<String> playerBoardRows, List<String> opponentBoardRows, int boardSize) {
        for (int y = COORDINATE_SIGN; y < playerBoardRows.size(); y++) {
            StringBuilder row = new StringBuilder();
            boolean numberCoordinatesLine = (y == COORDINATE_SIGN);
            if (numberCoordinatesLine) {
                String coordinatesRow = makeNumberCoordinatesRow(boardSize);
                row.append(coordinatesRow).append(SMALL_PLAYER_SEPARATOR).append(coordinatesRow);
            } else {
                row.append(Character.toString(UPPER_CHAR_NUM + y))
                        .append(SMALL_SEPARATOR)
                        .append(playerBoardRows.get(y))
                        .append(BIG_PLAYER_SEPARATOR)
                        .append(Character.toString(UPPER_CHAR_NUM + y))
                        .append(SMALL_SEPARATOR)
                        .append(opponentBoardRows.get(y));
            }
            System.out.println(row);
        }
    }

    private String makeNumberCoordinatesRow(int boardSize) {
        StringBuilder row = new StringBuilder();
        for (int x = 1; x < boardSize + 1; x++) {
            boolean topLeftCorner = (x == 1);
            if (topLeftCorner) {
                row.append(MEDIUM_SEPARATOR);
            }
            row.append(x).append(SMALL_SEPARATOR);
        }
        return row.toString();
    }
}
