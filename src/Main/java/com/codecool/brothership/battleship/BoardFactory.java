package com.codecool.brothership.battleship;


import com.codecool.brothership.utilities.RandomHelper;

import java.util.*;

public class BoardFactory {

    public List<Ship> randomPlacement(int boardSize) {
        List<Ship> randomShips = new ArrayList<>();
        Coordinate[][] coordinates = createBoarCoordinates(boardSize);
        for (ShipType shipType : ShipType.values()) {
            int shipLength = shipType.getLength();
            ShipSquare[] shipSquares = createRandomShipSquares(boardSize, shipLength, coordinates);
            removeOccupiedCoordinates(coordinates, shipSquares);
            randomShips.add(new Ship(shipType, shipSquares));
        }
        return randomShips;
    }

    private void removeOccupiedCoordinates(Coordinate[][] coordinates, ShipSquare[] shipSquares) {
        for (int y = 0; y < coordinates.length; y++) {
            for (int x = 0; x < coordinates[y].length; x++) {
                for (ShipSquare shipSquare : shipSquares) {
                    boolean isOwnSquare = (x == shipSquare.getX() && y == shipSquare.getY());
                    if (isOwnSquare) {
                        removeShipAndNeighbours(coordinates, x, y);
                    }

                }
            }
        }
    }

    private void removeShipAndNeighbours(Coordinate[][] coordinates, int ownX, int ownY) {
        int firstNeighbour = -1;
        int lastNeighbour = 2;
        for (int neighbourY = firstNeighbour; neighbourY < lastNeighbour; neighbourY++) {
            for (int neighbourX = firstNeighbour; neighbourX < lastNeighbour; neighbourX++) {
                int x = ownX + neighbourX;
                int y = ownY + neighbourY;
                boolean isShipOnBoard = (x < coordinates[0].length && y < coordinates.length && x >= 0 && y >= 0);
                if (isShipOnBoard) coordinates[y][x] = null;
            }
        }
    }

    private Coordinate[][] createBoarCoordinates(int boardSize) {
        Coordinate[][] coordinates = new Coordinate[boardSize][boardSize];
        for (int y = 0; y < coordinates.length; y++) {
            for (int x = 0; x < coordinates.length; x++) {
                coordinates[y][x] = new Coordinate(x, y);
            }
        }
        return coordinates;
    }

    private ShipSquare[] createRandomShipSquares(int boardSize, int shipLength, Coordinate[][] coordinates) {
        Random random = RandomHelper.getRandom();
        ShipSquare[] shipSquares = new ShipSquare[shipLength];
        ShipDirection randomDirectionType = ShipDirection.values()[random.nextInt(ShipDirection.values().length)];
        boolean isVertical = randomDirectionType.equals(ShipDirection.VERTICAL);
        int xMax = (isVertical) ? boardSize : boardSize - shipLength;
        int yMax = (isVertical) ? boardSize - shipLength : boardSize;
        int randomX = random.nextInt(xMax);
        int randomY = random.nextInt(yMax);
        for (int i = 0; i < shipLength; i++) {
            if (coordinates[randomY][randomX] == null) {
                return createRandomShipSquares(boardSize, shipLength, coordinates);
            }
            shipSquares[i] = new ShipSquare(randomX, randomY);
            if (isVertical) {
                randomY++;
            } else {
                randomX++;
            }
        }
        return shipSquares;
    }

    public WaterSquare[][] manualPlacement(int size, List<Ship> ships) {
        WaterSquare[][] ocean = new WaterSquare[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (isSquareEmpty(x, y, ships)) {
                    ocean[y][x] = new WaterSquare(x, y);
                } else {
                    ocean[y][x] = new WaterSquare(x, y, WaterSquareStatus.SHIP_PLACE);
                }
            }
        }
        return ocean;
    }

    private boolean isSquareEmpty(int x, int y, List<Ship> ships) {
        for (Ship ship : ships) {
            for (Square square : ship.getSquares()) {
                if (square.getX() == x && square.getY() == y) {
                    return false;
                }
            }
        }
        return true;
    }

    public HashMap<Coordinate, HashMap<SquareType, Integer>> makeBoardMap(int size, List<Ship> ships) {
        HashMap<Coordinate, HashMap<SquareType, Integer>> boardMap = new HashMap<>();
        int notShipIndex = -1;
        int oceanIndex = 0;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                HashMap<SquareType, Integer> data = new HashMap<>();
                Coordinate coordinate = new Coordinate(x, y);
                int shipIndex = getShipIndex(x, y, ships);
                if (shipIndex > notShipIndex) {
                    data.put(SquareType.SHIP, shipIndex);
                } else {
                    data.put(SquareType.OCEAN, oceanIndex);
                    oceanIndex++;
                }
                boardMap.put(coordinate, data);
            }
        }
        return boardMap;
    }

    private int getShipIndex(int x, int y, List<Ship> ships) {
        for (int index = 0; index < ships.size(); index++) {
            for (ShipSquare square : ships.get(index).getSquares()) {
                if (square.getX() == x && square.getY() == y) {
                    return index;
                }
            }
        }
        return -1;
    }
}