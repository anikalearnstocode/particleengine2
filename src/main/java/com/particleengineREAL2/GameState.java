package com.particleengineREAL2;

import java.util.ArrayList;

import processing.core.PApplet;

public abstract class GameState {
    protected Main main; // Reference to the main application
    protected static int totalScore; // Use static for cumulative score
    protected ArrayList<Shape> shapes;
    protected Shape selectedShape;
    protected float tubX, tubY, pixelWidth, pixelHeight;
    protected boolean isEndState = false;

    public GameState(Main main) {
        this.main = main; // Link back to the main class
        this.shapes = new ArrayList<>();
    }

    public void initializeShapes() {}

    public static void incrementScore() {
        totalScore++;
    }

    public void displayScore() {
        main.textSize(30);
        main.fill(0);
        main.text("Score: " + totalScore, 100, 50);
    }

    public void draw() {
        if (!isEndState) {
            updateAndDrawShapes();
        } else {
            handleEndState();
        }
    }

    public abstract void update();
    
    public abstract void handleInput(); // Method to handle input
    
    public void updateAndDrawShapes() {
        for (int i = shapes.size() - 1; i >= 0; i--) { // Iterate backwards to safely remove shapes
            Shape shape = shapes.get(i); // Get the shape at index i
            shape.update(shapes); // Polymorphic update
            shape.draw();   // Polymorphic draw
         
            if (isInBathtub(shape)) {
                shapes.remove(i);
                if (shape == selectedShape) {
                    incrementScore();
                }
            }

            if (shapes.isEmpty()) {
                handleEndState();
            }
        }
    }

    protected boolean isInBathtub(Shape shape) {
        float[] tubBounds = main.getBathTubBounds();
        return shape.x > tubBounds[0] && shape.x < tubBounds[0] + tubBounds[2] &&
               shape.y > tubBounds[1] && shape.y < tubBounds[1] + tubBounds[3];
    }

    protected void handleEndState() {
        String endMessage = "";
        String nextStateMessage = "";

        if (!isEndState) {
            isEndState = true;
            finalScoreCount();
        }

        if (this instanceof CircleState) {
            endMessage = "All circle toys in the tub!!";
            nextStateMessage = "Press T for triangle round!";
        } else if (this instanceof TriangleState) {
            endMessage = "All triangle toys in the tub!";
            nextStateMessage = "Press S for square round";
        } else if (this instanceof SquareState) {
            endMessage = "All squares in the bathtub!";
            nextStateMessage = "Game over!";
            finalScoreCount();
        } 

        main.textSize(40);
        main.fill(0);
        main.textAlign(PApplet.CENTER);
        main.text(endMessage, main.width / 2, main.height / 2);
        main.text(nextStateMessage, main.width / 3, main.height / 3);
    }

    public void mousePressed(int mouseX, int mouseY) {
        // Check if any shape is clicked and select it
        for (Shape shape : shapes) {
            if (shape.isMouseOver(mouseX, mouseY) || shape.isClicked(mouseX, mouseY)) {
                shape.select();
                selectedShape = shape;
                break;
            }
        }
    }

    public void mouseDragged(int mouseX, int mouseY) {
        if (selectedShape != null) {
            selectedShape.x = mouseX;
            selectedShape.y = mouseY;

            if (selectedShape.x > tubX && selectedShape.x < tubX + pixelWidth 
                && selectedShape.y > tubY && selectedShape.y < tubY + pixelHeight) {
                incrementScore();
                System.out.println("Score increased! Current Score: " + totalScore); // Debugging
            }
        }
    }

    public void mouseReleased() {
        if (selectedShape != null) {
            selectedShape.deselect();
        }
        selectedShape = null;
    }

    public void finalScoreCount() {
        if (isEndState = true) {
            main.text("Game Over!", main.width / 2, main.height / 2 + 20);
            //main.text("Total Score: " + totalScore, main.width / 2, main.height / 2 - 50);
        }
    }
}
