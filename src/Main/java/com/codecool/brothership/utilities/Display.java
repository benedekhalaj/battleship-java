package com.codecool.brothership.utilities;

import com.codecool.brothership.battleship.Square;
import com.codecool.brothership.battleship.SquareStatus;

public class Display {
    private static final int UPPER_CHAR_NUM = 65;
    private static final String SMALL_SEPARATOR = " ";
    private static final String MEDIUM_SEPARATOR = "  ";
    private static final String BIG_SEPARATOR = "   ";
    private static final String PLAYER_SEPARATOR = BIG_SEPARATOR + "|" + BIG_SEPARATOR;
    private static final String NEW_LINE = "\n";
    private static final int COORDINATE_SIGN = -1;


    public void printBoard(Square[][] board) {
        for (int rowIndex = COORDINATE_SIGN; rowIndex < board.length; rowIndex++) {
            StringBuilder displayedRow = new StringBuilder();
            for (int colIndex = COORDINATE_SIGN; colIndex < board[0].length; colIndex++) {
                String field;
                if (rowIndex == COORDINATE_SIGN && colIndex == COORDINATE_SIGN) {
                    field = BIG_SEPARATOR;
                } else if (rowIndex == COORDINATE_SIGN && colIndex != COORDINATE_SIGN) {
                    field = Character.toString(UPPER_CHAR_NUM + colIndex) + SMALL_SEPARATOR ;
                } else if (colIndex == COORDINATE_SIGN && rowIndex != COORDINATE_SIGN) {
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

    public void printGameplay(Square[][] playerBoard, Square[][] enemyBoard) {
        // TODO prints the gameplay
        for (int rowIndex = COORDINATE_SIGN; rowIndex < playerBoard.length; rowIndex++) {
            StringBuilder displayedPlayerRow = new StringBuilder();
            StringBuilder displayedEnemyRow = new StringBuilder();
            String playerSeparator = (rowIndex == COORDINATE_SIGN) ? MEDIUM_SEPARATOR + "|" + MEDIUM_SEPARATOR : PLAYER_SEPARATOR;
            for (int colIndex = COORDINATE_SIGN; colIndex < playerBoard[0].length; colIndex++) {
                String playerField;
                String enemyField;
                if (rowIndex == COORDINATE_SIGN && colIndex == COORDINATE_SIGN) {
                    playerField = BIG_SEPARATOR;
                    enemyField = playerField;
                } else if (rowIndex == COORDINATE_SIGN && colIndex != COORDINATE_SIGN) {
                    playerField = Character.toString(UPPER_CHAR_NUM + colIndex) + SMALL_SEPARATOR;
                    enemyField = playerField;
                } else if (colIndex == COORDINATE_SIGN && rowIndex != COORDINATE_SIGN) {
                    playerField = (rowIndex + 1  < 10) ? (rowIndex + 1) + SMALL_SEPARATOR : String.valueOf((rowIndex + 1));
                    enemyField = playerField;
                } else {
                    playerField = playerBoard[rowIndex][colIndex].getStatus().getCharacter();
                    String enemyFieldChar =  enemyBoard[rowIndex][colIndex].getStatus().getCharacter();
                    enemyField = (enemyFieldChar.equals(SquareStatus.SHIP.getCharacter())) ? SquareStatus.EMPTY.getCharacter() : enemyFieldChar;
                }
                displayedPlayerRow.append(playerField);
                displayedEnemyRow.append(enemyField);
            }
            System.out.println(displayedPlayerRow + playerSeparator + displayedEnemyRow);
        }
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
