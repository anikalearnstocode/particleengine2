// GameState.java
// com.particleengineREAL2
// Anika Krieger
// Particle Engine 3
// Description: Abstract class representing the game state. Manages shape initialization, score handling, input handling, and state transitions for different shapes.


package com.particleengineREAL2;

import java.util.ArrayList;
import processing.core.PApplet;

public abstract class GameState {
    protected Main main; // Reference to the main application
    protected static int totalScore; // Cumulative score across game states
    protected ArrayList<Shape> shapes; // List to store shapes in the current state
    protected Shape selectedShape; // Currently selected shape
    protected float tubX, tubY, pixelWidth, pixelHeight; // Bath tub dimensions
    protected boolean isEndState = false; // Flag to indicate if the game has ended

    // Constructor to link back to the Main application
    public GameState(Main main) {
        this.main = main; // Initialize main reference
        this.shapes = new ArrayList<>(); // Initialize the shapes list
    }

    // Abstract method for initializing shapes, to be implemented by subclasses
    public void initializeShapes() {}

    // Increment the score by one
    public static void incrementScore() {
        totalScore++;
    }

    // Display the current score on the screen
    public void displayScore() {
        main.textSize(30); // Set text size
        main.fill(0); // Set text color to black
        main.text("Score: " + totalScore, 100, 50); // Display score at specified position
    }

    // Main draw method to handle the rendering logic
    public void draw() {

        if (!isEndState) {
            updateAndDrawShapes(); // Update and draw shapes if the game is not in end state
        } else {
            handleEndState(); // Handle the end state of the game
            main.println("called");
        }
    }

    // Abstract method for updating game logic, to be implemented by subclasses
    public abstract void update();

    // Abstract method for handling input, to be implemented by subclasses
    public abstract void handleInput(); 

    // Update shapes and check if they are in the bathtub
    public void updateAndDrawShapes() {
        // Iterate backwards through the shapes list for safe removal
        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape shape = shapes.get(i); // Get the current shape
            shape.update(shapes); // Call the shape's update method
            shape.draw();   // Call the shape's draw method
            
            // Check if the shape is in the bathtub
            if (isInBathtub(shape)) {
                shapes.remove(i); // Remove shape if it is in the bathtub
                if (shape == selectedShape) {
                    incrementScore(); // Increment score if selected shape is removed
                }
            }
        }
        // Check if there are no shapes left
        if (shapes.isEmpty()) {
            handleEndState(); // Handle the end of the game
        }
    }

    // Check if a shape is within the bathtub bounds
    protected boolean isInBathtub(Shape shape) {
        float[] tubBounds = main.getBathTubBounds(); // Get bathtub boundaries
        return shape.x > tubBounds[0] && shape.x < tubBounds[0] + tubBounds[2] &&
               shape.y > tubBounds[1] && shape.y < tubBounds[1] + tubBounds[3]; // Check position
    }

    // Handle the end state of the game
    protected void handleEndState() {
        String endMessage = ""; // Message to display at the end
        String nextStateMessage = ""; // Message for the next state

        // Set end state flag if not already set
        if (!isEndState) {
            isEndState = true; // Update flag
            finalScoreCount(); // Final score counting logic
        }

        // Set messages based on the current state
        if (this instanceof CircleState) {
            endMessage = "All circle toys in the tub!!";
            nextStateMessage = "Press T for triangle round!";
        } else if (this instanceof TriangleState) {
            endMessage = "All triangle toys in the tub!";
            nextStateMessage = "Press S for square round";
        } else if (this instanceof SquareState) {
            endMessage = "All squares in the bathtub!";
            nextStateMessage = "Game over!";
            finalScoreCount(); // Count final score in square state
        } 

        // Display end state messages
        main.textSize(40); // Set larger text size for end state
        main.fill(0); // Set text color to black
        main.textAlign(PApplet.CENTER); // Center align text
        main.text(endMessage, main.width / 2, main.height / 2); // Display end message
        main.text(nextStateMessage, main.width / 3, main.height / 3); // Display next state message
    }

    // Mouse press event handling
    public void mousePressed(int mouseX, int mouseY) {
        // Check if any shape is clicked and select it
        for (Shape shape : shapes) {
            if (shape.isMouseOver(mouseX, mouseY) || shape.isClicked(mouseX, mouseY)) {
                shape.select(); // Select the shape
                selectedShape = shape; // Store reference to the selected shape
                break; // Exit loop once a shape is selected
            }
        }
    }

    // Mouse drag event handling
    public void mouseDragged(int mouseX, int mouseY) {
        if (selectedShape != null) {
            // Update the position of the selected shape
            selectedShape.x = mouseX;
            selectedShape.y = mouseY;

            // Check if the selected shape is within the bathtub bounds
            if (selectedShape.x > tubX && selectedShape.x < tubX + pixelWidth 
                && selectedShape.y > tubY && selectedShape.y < tubY + pixelHeight) {
                incrementScore(); // Increment score if the shape is in the bathtub
                System.out.println("Score increased! Current Score: " + totalScore); // Debugging output
            }
        }
    }

    // Mouse release event handling
    public void mouseReleased() {
        if (selectedShape != null) {
            selectedShape.deselect(); // Deselect the shape
        }
        selectedShape = null; // Clear selected shape reference
    }

    // Display the final score count at game over
    public void finalScoreCount() {
        if (isEndState) {
            main.text("Game Over!", main.width / 2, main.height / 2 + 20); // Display game over message
            //main.text("Total Score: " + totalScore, main.width / 2, main.height / 2 - 50); // Total score (commented out)
        }
    }
}
