package com.codecool.brothership.battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {

    private final AiType aiType;
    private final List<Coordinate> possibleShots = new ArrayList<>();
    private final Random random = new Random();

    public ComputerPlayer(PlayerId id, AiType aiType) {
        super(id, PlayerType.AI);
        this.aiType = aiType;
        createPossibleShots();
    }

    private void createPossibleShots() {
        int boardSize = 10;
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                possibleShots.add(new Coordinate(x, y));
            }
        }
    }

    public AiType getAiType() {
        return aiType;
    }

    @Override
    public Coordinate shoot() {
        int randomChoice = random.nextInt(possibleShots.size());
        Coordinate randomCoordinate = possibleShots.get(randomChoice);
        possibleShots.remove(randomChoice);
        return randomCoordinate;
    }
}
