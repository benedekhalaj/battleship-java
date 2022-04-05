package com.codecool.brothership.battleship;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<WaterSquare> ocean = new ArrayList<>();
    private final List<Ship> ships;
    private final int size;

    private static final String SMALL_SEPARATOR = " ";
    private static final String MEDIUM_SEPARATOR = "  ";
    private static final String BIG_SEPARATOR = "   ";

    public Board(int size, List<Ship> ships) {
        this.ships = ships;
        this.size = size;
        createOcean();
    }

    private void createOcean() {
        // TODO fill ocean with waterSquares
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (isSquareEmpty(x, y)) {
                    ocean.add(new WaterSquare(x, y));
                }
            }
        }
    }

    private boolean isSquareEmpty(int x, int y) {
        for (Ship ship : ships) {
            for (Square square : ship.getSquares()) {
                if (square.getX() == x && square.getY() == y) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<String> getBoardRows() {
        List<String> boardRows = new ArrayList<>();
        for (int y = 0; y < size; y++) {
            StringBuilder boardRow = new StringBuilder();
            for (int x = 0; x < size; x++) {
                String squareCharacter = getShipSquareCharacter(x, y);
                if (squareCharacter == null) {
                    squareCharacter = getWaterSquareCharacter(x, y);
                }
                boardRow.append(squareCharacter);
            }
            boardRows.add(boardRow.toString());
        }
        return boardRows;
    }

    private String getWaterSquareCharacter(int x, int y) {
        for (WaterSquare waterSquare : ocean) {
            if (waterSquare.getX() == x && waterSquare.getY() == y) {
                return waterSquare.getStatus().getCharacter();
            }
        }
        return null;
    }

    private String getShipSquareCharacter(int x, int y) {
        for (Ship ship : ships) {
            for (ShipSquare square : ship.getSquares()) {
                if (square.getX() == x && square.getY() == y) {
                    return square.getStatus().getCharacter();
                }
            }
        }
        return null;
    }
}
