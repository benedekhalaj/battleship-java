package com.codecool.brothership.battleship;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final PlayerId id;
    private final PlayerType type;
    private Board board;

    public Player(PlayerId id, PlayerType type) {
        this.id = id;
        this.type = type;
    }

    public PlayerId getId() {
        return id;
    }

    public PlayerType getType() {
        return type;
    }

    public ShotStatus getShotStatus(Coordinate shotCoordinate) {
        return board.getShotStatus(shotCoordinate);
    }

    public boolean isAlive() {
        return board.isThereAnyShips();
    }

    public void createBoard(int boardSize, List<Ship> ships) {
        this.board = new Board(boardSize, ships);
    }

    public List<String> getBoardRows() {
        return board.getBoardRows();
    }

    public Coordinate shoot() {
        // Human player not uses
        return null;
    }
}
