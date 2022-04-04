package com.codecool.brothership.utilities;

import com.codecool.brothership.battleship.Coordinates;
import com.codecool.brothership.battleship.ShipPlacementType;
import com.codecool.brothership.battleship.Square;

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

    public Coordinates getCoordinates() {
        boolean isValid = false;
        String userInput = null;
        while (!isValid) {
            userInput = SCANNER.nextLine();
            if (validateInput(userInput)) {
                isValid = true;
            }
        }
        return convertInputToCoordinates(userInput);
    }

    private Coordinates convertInputToCoordinates(String userInput) {
        // TODO Implement conversion
        return new Coordinates(0, 0);
    }
}
