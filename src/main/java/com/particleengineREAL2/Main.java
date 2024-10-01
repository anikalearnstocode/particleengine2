package com.particleengineREAL2;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {
    GameState currentState; // Current game state
    TitleState titleState;
    CircleState circleState;
    TriangleState triangleState;
    SquareState squareState;
    GameOverState gameover;

    PImage bathtub;
    float tubX, tubY; // Example values, adjust as needed
    float pixelWidth = 400; // Example values, adjust as needed
    float pixelHeight = 200; // Example values, adjust as needed

    public static void main(String[] args) {
        PApplet.main("com.particleengineREAL2.Main");
    }

    public void settings() {
        size(800, 600); // Set up canvas size
    }

    public void setup() {

        bathtub = loadImage("photo/Bathtub.jpg");

        titleState = new TitleState(this);
        circleState = new CircleState(this);
        triangleState = new TriangleState(this);
        squareState = new SquareState(this);
        gameover = new GameOverState(this);

        currentState = titleState;
    }

    public void draw() {
        currentState.draw(); // Call the draw method of the current state
        
        // Check for end game condition
        if (currentState instanceof SquareState) {
            checkEndCondition();
        }
    }

    private void checkEndCondition() {
        // // Assuming the currentState is SquareState, check its shapes
        // if (currentState.shapes.isEmpty()) {
        //     int finalScore = GameState.totalScore; // Access total score
        //     System.out.println("Final Score: " + finalScore); // Debugging or could show on screen
        //     currentState = new TriangleState(this); // Switch to EndState
        // }

        if (currentState instanceof SquareState) {
            if (currentState.shapes.isEmpty()) {
                int finalScore = GameState.totalScore; // Access total score
                System.out.println("Final Score: " + finalScore); // Debugging or could show on screen
                currentState = new GameOverState(this); // Switch to GameOverState
            }
        }
    }

    public void keyPressed() {
        if (key == 'P' || key == 'p') {
            currentState = circleState; // Switch to the circle round state
        } else if (key == 'T' || key == 't') {
            currentState = triangleState; // Switch to the triangle round state
        } else if (key == 'S' || key == 's') {
            currentState = squareState; // Switch to the square round state
        } else if (key == 'R' || key == 'r') {
            currentState = titleState;
        }
    }

    public void mousePressed() {
        currentState.mousePressed(mouseX, mouseY);
    }

    public void mouseDragged() {
        currentState.mouseDragged(mouseX, mouseY);
    }

    public void mouseReleased() {
        currentState.mouseReleased();
    }

	public void drawTitleState() {}

    public PImage getBathTubImage()
    {
        tubX = (width - pixelWidth) / 2;
        tubY = height - pixelHeight - 50;
        image(bathtub, tubX, tubY, pixelWidth, pixelHeight);
        return bathtub;
    }

    public float[] getBathTubBounds() {
        return new float[] { tubX, tubY, pixelWidth, pixelHeight };
    }
}