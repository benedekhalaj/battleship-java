package com.codecool.brothership.battleship;

public enum ShipPlacementType {
    MANUAL("m"),
    RANDOM("r");

    private final String placementType;

    ShipPlacementType(String placementType) {
        this.placementType = placementType;
    }

    public String getPlacementType() {
        return placementType;
    }
}
