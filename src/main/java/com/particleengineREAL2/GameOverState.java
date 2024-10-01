/*
Anika Krieger
Sep 30
Particle Engine 3
Extra Credit Attempt - Game!
File Description: Handles the game over state and displays final score with restart option.
*/

package com.particleengineREAL2;

import processing.core.PApplet;

public class GameOverState extends GameState {

    public GameOverState(Main main) {
        super(main); // Initialize with reference to the main class
    }

    @Override
    public void draw() {
        main.background(255); // Set background to white
        main.textSize(40); // Set text size
        main.fill(0); // Set fill color to black
        main.textAlign(PApplet.CENTER); // Center the text

        // Display the game-over message and the final score
        main.text("Game Over!", main.width / 2, main.height / 2 - 20); // Game over message
        main.text("Final Score: " + totalScore, main.width / 2, main.height / 2 + 20); // Final score
        main.text("Press 'R' to Restart", main.width / 2, main.height / 2 + 60); // Restart instructions
    }

    @Override
    public void update() {
        // No updates needed in the game-over state
    }

    @Override
    public void handleInput() {
        // Handle input if necessary (e.g., restart game)
    }

    public void keyPressed() {
        // Restart game logic when 'R' is pressed
        if (main.key == 'R' || main.key == 'r') {
            main.currentState = main.titleState; // Return to title screen or reset game state
        }
    }
}
