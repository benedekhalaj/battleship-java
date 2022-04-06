package com.codecool.brothership.battleship;

import java.util.*;

public class BoardFactory {

    public Board[][] randomPlacement(int rows, int cols) {
        return new Board[rows][cols];
    }

    public List<WaterSquare> manualPlacement(int size, List<Ship> ships) {
        List<WaterSquare> ocean = new ArrayList<>();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (isSquareEmpty(x, y, ships)) {
                    ocean.add(new WaterSquare(x, y));
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
                int shipIndex = getShipIndex(x , y, ships);
                if(shipIndex > notShipIndex) {
                    data.put(SquareType.SHIP, shipIndex);
                } else {
                    data.put(SquareType.OCEAN, oceanIndex);
                }
                boardMap.put(coordinate,data);
                oceanIndex++;
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