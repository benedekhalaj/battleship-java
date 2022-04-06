package com.codecool.brothership.battleship;

import java.util.*;

public class Board {
    private final List<WaterSquare> ocean;
    private final List<Ship> ships;
    private final HashMap<Coordinate, HashMap<SquareType, Integer>> boardMap;
    private final int size;

    public Board(int size, List<Ship> ships) {
        this.ships = ships;
        this.size = size;
        BoardFactory boardFactory = new BoardFactory();
        this.ocean = boardFactory.manualPlacement(size, ships);
        this.boardMap = boardFactory.makeBoardMap(size, ships);
    }


    public List<String> getBoardRows() {
        List<String> boardRows = new ArrayList<>();
        for (int y = 0; y < size; y++) {
            StringBuilder boardRow = new StringBuilder();
            for (int x = 0; x < size; x++) {
                String squareCharacter;
                squareCharacter = getSquareCharacter(y, x);
                boardRow.append(squareCharacter);
            }
            boardRows.add(boardRow.toString());
        }
        return boardRows;
    }

    private String getSquareCharacter(int y, int x) {
        String squareCharacter;
        Coordinate coordinate = new Coordinate(x, y);
        HashMap<SquareType, Integer> coordinateInformation = boardMap.get(coordinate);
        boolean isOcean = coordinateInformation.containsKey(SquareType.OCEAN);
        if (isOcean) {
            int index = coordinateInformation.get(SquareType.OCEAN);
            WaterSquare waterSquare = this.ocean.get(index);
            squareCharacter = waterSquare.getStatus().getCharacter();
        } else {
            int index = coordinateInformation.get(SquareType.SHIP);
            squareCharacter = getShipSquareCharacter(x, y, this.ships.get(index));
        }
        return squareCharacter;
    }

    private String getShipSquareCharacter(int x, int y, Ship ship) {
        for (ShipSquare square : ship.getSquares()) {
            if (square.getX() == x && square.getY() == y) {
                return square.getStatus().getCharacter();
            }
        }
        return null;
    }

    public List<Ship> getShips() {
        return this.ships;
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
        for (WaterSquare waterSquare : ocean) {
            if (coordinate.getX() == waterSquare.getX() && coordinate.getY() == waterSquare.getY()) {
                if (waterSquare.getStatus() == WaterSquareStatus.EMPTY) {
                    waterSquare.changeStatus(WaterSquareStatus.MISSED);
                    return ShotStatus.MISS;
                } else {
                    return ShotStatus.INVALID;
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
}
