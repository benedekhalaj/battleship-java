package com.codecool.brothership.utilities;

import com.codecool.brothership.battleship.ShipPlacementType;

import java.util.Scanner;

public class Input {
    private static final Scanner SCANNER = new Scanner(System.in);

    private boolean validateInput(String input) {
        // TODO validate the user input
        return true;
    }

    public String getInput() {
        String userInput = SCANNER.nextLine();
        return userInput;
    }

    public ShipPlacementType getPlacementType() {
        return ShipPlacementType.MANUAL;
    }
}
