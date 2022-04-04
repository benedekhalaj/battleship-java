package com.codecool.brothership.battleship;

import java.util.List;

public class Player {
    private final List<Ship> ships;

    public Player(List<Ship> ships) {
        this.ships = ships;
    }

    public Square shoot() {
        // TODO returns valid shot square
        return new Square(0, 0);
    }

    public boolean isAlive() {
        // TODO return tru if player has ship, what has not sunk yet
        return true;
    }
}
