package com.codecool.brothership.battleship;

import com.codecool.brothership.utilities.RandomHelper;

import java.util.*;

public class Board {
    private final WaterSquare[][] ocean;
    private final List<Ship> ships;
    private final int size;

    public Board(int size, List<Ship> ships) {
        this.ships = ships;
        this.size = size;
        BoardFactory boardFactory = new BoardFactory();
        this.ocean = boardFactory.manualPlacement(size, ships);
    }

    public Board(int boardSize) {
        this.size = boardSize;
        BoardFactory boardFactory = new BoardFactory();
        this.ships = boardFactory.randomPlacement(this.size);
        this.ocean = boardFactory.manualPlacement(this.size, this.ships);
    }


    public List<String> getBoardRows() {
        List<String> boardRows = new ArrayList<>();
        for (int y = 0; y < this.ocean.length; y++) {
            StringBuilder rowString = new StringBuilder();
            for (int x = 0; x < this.ocean[y].length; x++) {
                String squareCharacterString;
                WaterSquare waterSquare = ocean[y][x];
                if (waterSquare.getStatus().equals(WaterSquareStatus.EMPTY)) {
                    squareCharacterString = waterSquare.getStatus().getCharacter();
                } else {
                    squareCharacterString = getShipSquareCharacter(x, y);
                }
                rowString.append(squareCharacterString);
            }
            boardRows.add(rowString.toString());
        }
        return boardRows;
    }

    private String getShipSquareCharacter(int x, int y) {
        for (Ship ship : this.ships) {
            for (ShipSquare square : ship.getSquares()) {
                if (square.getX() == x && square.getY() == y) {
                    return square.getStatus().getCharacter();
                }
            }
        }
        return null;
    }

    public ShotStatus getShotStatus(Coordinate coordinate) {
        for (Ship ship : ships) {
            for (ShipSquare shipSquare : ship.getSquares()) {
                if (coordinate.getX() == shipSquare.getX() && coordinate.getY() == shipSquare.getY()) {
                    if (shipSquare.getStatus() == ShipSquareStatus.SHIP) {
                        shipSquare.changeStatus(ShipSquareStatus.HIT);
                        if (isShipSunk(ship)) {
                            return ShotStatus.SINK;
                        } else {
                            return ShotStatus.HIT;
                        }
                    } else {
                        return ShotStatus.INVALID;
                    }
                }
            }
        }
        for (WaterSquare[] waterSquares : ocean) {
            for (WaterSquare waterSquare : waterSquares) {
                if (coordinate.getX() == waterSquare.getX() && coordinate.getY() == waterSquare.getY()) {
                    if (waterSquare.getStatus() == WaterSquareStatus.EMPTY) {
                        waterSquare.changeStatus(WaterSquareStatus.MISSED);
                        return ShotStatus.MISS;
                    } else {
                        return ShotStatus.INVALID;
                    }
                }
            }
        }
        return null;
    }

    private boolean isShipSunk(Ship ship) {
        int shipLength = ship.getType().getLength();
        int hitCount = 0;
        for (ShipSquare square : ship.getSquares()) {
            if (square.getStatus() == ShipSquareStatus.HIT) {
                hitCount++;
            }
        }
        boolean isShipDestroyed = shipLength == hitCount;
        if (isShipDestroyed) {
            sinkShip(ship);
            return true;
        }
        return false;
    }

    private void sinkShip(Ship ship) {
        for (ShipSquare square : ship.getSquares()) {
            square.changeStatus(ShipSquareStatus.SUNK);
        }
    }

    public boolean isThereAnyShips() {
        for (Ship ship : ships) {
            for (ShipSquare square : ship.getSquares()) {
                if (square.getStatus() != ShipSquareStatus.SUNK) {
                    return true;
                }
            }
        }
        return false;
    }

    public void randomPlacement() {
    }
}
