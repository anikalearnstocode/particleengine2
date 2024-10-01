// Main.java
// Anika Krieger
// Sep 30
//Description: Main application class for game
//Important! I'm trying to implement the extra credit of doing a game!!

package com.particleengineREAL2;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {
    GameState currentState; // Reference to the current game state
    TitleState titleState; // State for the title screen
    CircleState circleState; // State for the circle round
    TriangleState triangleState; // State for the triangle round
    SquareState squareState; // State for the square round
    GameOverState gameover; // State for the game-over screen

    PImage bathtub; // Image of the bathtub
    float tubX, tubY; // Position coordinates for the bathtub image
    float pixelWidth = 400; // Width of the bathtub image
    float pixelHeight = 200; // Height of the bathtub image

    public static void main(String[] args) {
        PApplet.main("com.particleengineREAL2.Main"); // Launch the application
    }

    public void settings() {
        size(800, 600); // Set up the canvas size for the application
    }

    public void setup() {
        // Load the bathtub image from the specified path
        bathtub = loadImage("photo/Bathtub.jpg");

        // Initialize the game states
        titleState = new TitleState(this);
        circleState = new CircleState(this);
        triangleState = new TriangleState(this);
        squareState = new SquareState(this);
        gameover = new GameOverState(this);

        currentState = titleState; // Set the initial state to the title screen
    }

    public void draw() {
        background(255); // Clear the background to white
        currentState.draw(); // Call the draw method of the current game state
        
        // Check for end game condition only when in SquareState
        if (currentState instanceof SquareState) {
            checkEndCondition();
        }
    }

    private void checkEndCondition() {
        // Check if the current state is SquareState and if it has no remaining shapes
        if (currentState instanceof SquareState) {
            if (currentState.shapes.isEmpty()) {
                int finalScore = GameState.totalScore; // Access the total score
                System.out.println("Final Score: " + finalScore); // Debugging output for the final score
                currentState = new GameOverState(this); // Switch to the game-over state
            }
        }
    }

    public void keyPressed() {
        // Handle key presses to switch between game states
        if (key == 'P' || key == 'p') {
            currentState = circleState; // Switch to the circle round state
        } else if (key == 'T' || key == 't') {
            currentState = triangleState; // Switch to the triangle round state
        } else if (key == 'S' || key == 's') {
            currentState = squareState; // Switch to the square round state
        } else if (key == 'R' || key == 'r') {
            currentState = titleState; // Switch back to the title screen state
        }
    }

    public void mousePressed() {
        // Delegate mouse pressed event to the current state
        currentState.mousePressed(mouseX, mouseY);
    }

    public void mouseDragged() {
        // Delegate mouse dragged event to the current state
        currentState.mouseDragged(mouseX, mouseY);
    }

    public void mouseReleased() {
        // Delegate mouse released event to the current state
        currentState.mouseReleased();
    }

    public void drawTitleState() {
        // This method is currently empty and can be implemented if needed
    }

    public PImage getBathTubImage() {
        // Calculate the position for the bathtub image to center it at the bottom of the screen
        tubX = (width - pixelWidth) / 2;
        tubY = height - pixelHeight - 50;
        image(bathtub, tubX, tubY, pixelWidth, pixelHeight); // Draw the bathtub image
        return bathtub; // Return the bathtub image
    }

    public float[] getBathTubBounds() {
        // Return the boundaries of the bathtub for collision detection
        return new float[] { tubX, tubY, pixelWidth, pixelHeight };
    }
}
