package com.codecool.brothership.utilities;

import com.codecool.brothership.battleship.Player;
import com.codecool.brothership.battleship.Square;

public class Display {
    public void printBoard(Square[][] board) {
        // TODO prints the board(ocean)
    }

    public void printMenu() {
        // TODO Prints menu
    }

    public void printGameplay(Square[][] board, Player player) {
        // TODO prints the gameplay
    }

    public void printResult() {
        // TODO prints the game result
    }

    public void printInputMessage(String message) {
        // Prints message for input without a newline.
        System.out.print(message);
    }

    public void printMessage(String message) {
        // Prints message with newline.
        System.out.println(message);
    }
}
