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

    public List<Ship> getShips() {
        return this.board.getShips();
    }

    public PlayerType getType() {
        return type;
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
