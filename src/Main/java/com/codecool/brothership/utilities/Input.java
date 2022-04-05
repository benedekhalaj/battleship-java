package com.codecool.brothership.utilities;

import com.codecool.brothership.battleship.Coordinate;
import com.codecool.brothership.battleship.ShipDirection;
import com.codecool.brothership.battleship.ShipPlacementType;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Input {
    private static final Scanner SCANNER = new Scanner(System.in);

    private boolean validateInput(String input) {
        // TODO validate the user input
        return true;
    }

    public String getInput() {
        return SCANNER.nextLine();
    }

    public Coordinate getCoordinates() {
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

    private Coordinate convertInputToCoordinates(String userInput) {
        // TODO Implement conversion
        return new Coordinate(0, 0);
    }

    public boolean isDirectionValid(String userInput) {
        return userInput.equals(ShipDirection.VERTICAL.getDirection()) ||
                userInput.equals(ShipDirection.HORIZONTAL.getDirection());
    }

    public ShipDirection getDirection(String userInput) {
        for (ShipDirection shipDirection : ShipDirection.values()) {
            if (userInput.equals(shipDirection.getDirection())) {
                return shipDirection;
            }
        }
        return null;
    }

    public boolean isPlacementTypeValid(String userInput) {
        return userInput.equals(ShipPlacementType.MANUAL.getPlacementType()) ||
                userInput.equals(ShipPlacementType.RANDOM.getPlacementType());
    }

    public ShipPlacementType getPlacementType(String userInput) {
        for (ShipPlacementType shipPlacementType : ShipPlacementType.values()) {
            if (userInput.equals(shipPlacementType.getPlacementType())) {
                return shipPlacementType;
            }
        }
        return null;
    }

    public boolean isCoordinatesFormatValid(String userInput) {
        String regex = "^[a-zA-Z][0-9]{1,2}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(userInput);
        return matcher.find();
    }
}
