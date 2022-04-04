package com.codecool.brothership.utilities;

import com.codecool.brothership.battleship.Board;
import com.codecool.brothership.battleship.Player;
import com.codecool.brothership.battleship.Square;

public class Display {
    private static final int UPPER_CHAR_NUM = 65;
    private static final String SMALL_SEPARATOR = " ";
    private static final String MEDIUM_SEPARATOR = "  ";
    private static final String BIG_SEPARATOR = "   ";
    private static final String NEW_LINE = "\n";

    public void printBoard(Square[][] board) {
        int coordinateSign = -1;
        for (int rowIndex = coordinateSign; rowIndex < board.length; rowIndex++) {
            StringBuilder displayedRow = new StringBuilder();
            for (int colIndex = coordinateSign; colIndex < board[0].length; colIndex++) {
                String field;
                if (rowIndex == coordinateSign && colIndex == coordinateSign) {
                    field = BIG_SEPARATOR;
                } else if (rowIndex == coordinateSign && colIndex != coordinateSign) {
                    field = Character.toString(UPPER_CHAR_NUM + colIndex) + SMALL_SEPARATOR ;
                } else if (colIndex == coordinateSign && rowIndex != coordinateSign) {
                    field = (rowIndex + 1  < 10) ? (rowIndex + 1) + SMALL_SEPARATOR : String.valueOf((rowIndex + 1));
                } else {
                    field = board[rowIndex][colIndex].getStatus().getCharacter();
                }
                displayedRow.append(field);
            }
            System.out.println(displayedRow);
        }
        // TODO prints the board(ocean)
    }

    public void printMenu() {
        System.out.println("""
                Select game mode:
                1: Player VS Player
                2: Player VS AI
                3: AI VS AI""");
        // TODO Prints menu
    }

    public void printGameplay(Square[][] playerBoard, Board[][] enemyBoard, Player player) {
        for (Square[] squares : playerBoard) {

        }

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
