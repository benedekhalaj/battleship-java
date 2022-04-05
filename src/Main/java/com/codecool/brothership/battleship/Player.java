package com.codecool.brothership.battleship;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final PlayerId id;
    private final PlayerType type;
    private final List<Ship> ships = new ArrayList<>();
    private Board board;

    public Player(PlayerId id, PlayerType type) {
        this.id = id;
        this.type = type;
    }

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public PlayerId getId() {
        return id;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public PlayerType getType() {
        return type;
    }

    public Square shoot() {
        // TODO returns valid shot square
        return new Square(0, 0);
    }

    public boolean isAlive() {
        // TODO return true if player has ship, what has not sunk yet
        return true;
    }

    public void createBoard(int boardSize, List<Ship> ships) {
        this.board = new Board(boardSize, ships);
    }

    public List<String> getBoardRows() {
        return board.getBoardRows();
    }
}
