package com.particleengineREAL2;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {
    GameState currentState; // Current game state
    TitleState titleState;
    CircleState circleState;
    TriangleState triangleState;
    SquareState squareState;

    PImage bathtub;
    float tubX = 400; // Example values, adjust as needed
    float tubY = 550; // Example values, adjust as needed
    float pixelWidth = 400; // Example values, adjust as needed
    float pixelHeight = 200; // Example values, adjust as needed

    public static void main(String[] args) {
        PApplet.main("com.particleengineREAL2.Main");
    }

    public void settings() {
        size(800, 600); // Set up canvas size
    }

    public void setup() {

        bathtub = loadImage(Main.class.getResource("/Bathtub.png").getPath());
        

        titleState = new TitleState(this);
        circleState = new CircleState(this);
        triangleState = new TriangleState(this);
        squareState = new SquareState(this);

        currentState = titleState;
    }

    public void draw() {
        currentState.draw(); // Call the draw method of the current state
    }

    public void keyPressed() {
        if (key == 'P' || key == 'p') {
            currentState = circleState; // Switch to the circle round state
        } else if (key == 'T' || key == 't') {
            currentState = triangleState; // Switch to the triangle round state
        } else if (key == 'S' || key == 's') {
            currentState = squareState; // Switch to the square round state
        }
    }

    public void mousePressed() {
        // if (currentState instanceof CircleState) {
        //     ((CircleState) currentState).mousePressed(mouseX, mouseY);
        // } else if (currentState instanceof TriangleState) {
        //     ((TriangleState) currentState).mousePressed(mouseX, mouseY);
        // } else if (currentState instanceof SquareState) {
        //     ((SquareState) currentState).mousePressed(mouseX, mouseY);
        // }

        currentState.mousePressed(mouseX, mouseY);
    }

    public void mouseDragged() {
        // if (currentState instanceof CircleState) {
        //     ((CircleState) currentState).mouseDragged(mouseX, mouseY);
        // } else if (currentState instanceof TriangleState) {
        //     ((TriangleState) currentState).mouseDragged(mouseX, mouseY);
        // } else if (currentState instanceof SquareState) {
        //     ((SquareState) currentState).mouseDragged(mouseX, mouseY);
        // }

        currentState.mouseDragged(mouseX, mouseY);
    }

    public void mouseReleased() {
        // if (currentState instanceof CircleState) {
        //     ((CircleState) currentState).mouseReleased();
        // } else if (currentState instanceof TriangleState) {
        //     ((TriangleState) currentState).mouseReleased();
        // } else if (currentState instanceof SquareState) {
        //     ((SquareState) currentState).mouseReleased();
        // }

        currentState.mouseReleased();
    }

	public void drawTitleState() {}
}
