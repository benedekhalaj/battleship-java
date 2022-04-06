package com.codecool.brothership.utilities;

import com.codecool.brothership.battleship.Ship;
import com.codecool.brothership.battleship.ShipSquare;
import com.codecool.brothership.battleship.Square;

public class Validator {

    public static boolean isNeighbourSquaresEmpty(int newCoordinateX, int newCoordinateY, Ship ship) {
        ShipSquare[] shipSquares = ship.getSquares();
        for (Square shipSquare : shipSquares) {
            int shipX = shipSquare.getX();
            int shipY = shipSquare.getY();
            for (int y = -1; y < 2; y++) {
                for (int x = -1; x < 2; x++) {
                    boolean ownCoordinate = (y == 0 && x == 0);
                    if (ownCoordinate) continue;

                    boolean isNeighbourOccupied = (newCoordinateX + x == shipX && newCoordinateY + y == shipY);
                    if (isNeighbourOccupied) return false;
                }
            }
        }
        return true;
    }
}
