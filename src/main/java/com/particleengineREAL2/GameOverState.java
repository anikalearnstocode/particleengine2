// GameOverState.java
package com.particleengineREAL2;

import processing.core.PApplet;

public class GameOverState extends GameState {

    public GameOverState(Main main) {
        super(main);
    }

    @Override
    public void draw() {
        main.background(255);
        main.textSize(40);
        main.fill(0);
        main.textAlign(PApplet.CENTER);
        
        // Display the game-over message and the final score
        main.text("Game Over!", main.width / 2, main.height / 2 - 20);
        main.text("Final Score: " + totalScore, main.width / 2, main.height / 2 + 20);
        main.text("Press 'R' to Restart", main.width / 2, main.height / 2 + 60);
    }

    @Override
    public void update() {
        // No updates needed in the game-over state
    }

    @Override
    public void handleInput() {
        // Handle input if necessary (for example, restart game)
    }

    public void keyPressed() {
        // Restart game logic
        if (main.key == 'R' || main.key == 'r') {
            main.currentState = main.titleState; // Return to title screen or reset game state
        }
    }
}

