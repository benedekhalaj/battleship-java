package com.codecool.brothership.battleship;

import com.codecool.brothership.utilities.Display;
import com.codecool.brothership.utilities.Input;

public class Battleship {
    private final Display display = new Display();
    private final Input input = new Input();

    public void run() {
        // TODO this loop runs the game
        System.out.println("This is the main loop");
        GameMode gameMode = GameMode.P_VS_P;
        Game game = new Game(display, input, gameMode);
        game.play();
    }
}
