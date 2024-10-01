/*
Anika Krieger
Sep 30
Particle Engine 3
Extra Credit Attempt - Game!
File Description: Main class for handling game states and user interactions.
*/

package com.particleengineREAL2;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {
    GameState currentState; // Current game state
    TitleState titleState;  // Title screen state
    CircleState circleState; // Circle round state
    TriangleState triangleState; // Triangle round state
    SquareState squareState; // Square round state
    GameOverState gameover; // Game over state

    PImage bathtub; // Bathtub image
    float tubX, tubY; // Tub position coordinates
    float pixelWidth = 400; // Tub width
    float pixelHeight = 200; // Tub height

    public static void main(String[] args) {
        PApplet.main("com.particleengineREAL2.Main"); // Launch the Processing application
    }

    public void settings() {
        size(800, 600); // Set up the canvas size
    }

    public void setup() {
        bathtub = loadImage("photo/Bathtub.jpg"); // Load bathtub image

        // Initialize all game states
        titleState = new TitleState(this);
        circleState = new CircleState(this);
        triangleState = new TriangleState(this);
        squareState = new SquareState(this);
        gameover = new GameOverState(this);

        currentState = titleState; // Start with the title screen
    }

    public void draw() {
        currentState.draw(); // Delegate drawing to the current state
        
        // Check for end game condition during square state
        if (currentState instanceof SquareState) {
            checkEndCondition();
        }
    }

    private void checkEndCondition() {
        // Check if all shapes are cleared in the square state
        if (currentState instanceof SquareState) {
            if (currentState.shapes.isEmpty()) {
                int finalScore = GameState.totalScore; // Get total score
                System.out.println("Final Score: " + finalScore); // Debugging
                currentState = new GameOverState(this); // Switch to GameOverState
            }
        }
    }

    public void keyPressed() {
        // Change game state based on key input
        if (key == 'P' || key == 'p') {
            currentState = circleState; // Switch to circle state
        } else if (key == 'T' || key == 't') {
            currentState = triangleState; // Switch to triangle state
        } else if (key == 'S' || key == 's') {
            currentState = squareState; // Switch to square state
        } else if (key == 'R' || key == 'r') {
            currentState = titleState; // Reset to title state
        }
    }

    public void mousePressed() {
        // Handle mouse press events in the current state
        currentState.mousePressed(mouseX, mouseY);
    }

    public void mouseDragged() {
        // Handle mouse drag events in the current state
        currentState.mouseDragged(mouseX, mouseY);
    }

    public void mouseReleased() {
        // Handle mouse release events in the current state
        currentState.mouseReleased();
    }

    public void drawTitleState() {}

    public PImage getBathTubImage() {
        // Draw the bathtub image at the bottom of the screen
        tubX = (width - pixelWidth) / 2;
        tubY = height - pixelHeight - 50;
        image(bathtub, tubX, tubY, pixelWidth, pixelHeight);
        return bathtub;
    }

    public float[] getBathTubBounds() {
        // Return the boundaries of the bathtub for collision checks
        return new float[] { tubX, tubY, pixelWidth, pixelHeight };
    }
}
