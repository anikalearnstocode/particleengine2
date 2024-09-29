/*
Main.java
Author: Anika Krieger
Project Name: Particle Engine 3
Date: September 25
Description: Particle Engine 2
*/

package com.particleengineREAL2;

import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet {
    ArrayList<Shape> shapes = new ArrayList<>(); // Store all shapes here
    int state = 0; // Game state - 0: title screen, 1: game state 1, etc.
    Shape selectedShape = null;

    public static void main(String[] args) {
        PApplet.main("com.particleengineREAL2.Main");
        PApplet.main("Main");
    }

    public void settings() {
        size(800, 600); // Set up canvas size
    }

    public void setup() {
        initializeGameState1(); // First game state

        shapes = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            shapes.add(new Circle(random(width), random(height), 30, this, random(-2, 2), random(-2, 2))); // Removed the 'i' argument
            shapes.add(new Triangle(random(width), random(height), 40, this, random(-2, 2), random(-2, 2), i));
            shapes.add(new Square(random(width), random(height), 50, this, random(-2, 2), random(-2, 2), i));
        }
    }

    public void draw() {
        background(255); // Clear background

        // Different behaviors based on state
        if (state == 0) {
            drawTitleScreen();
        } else if (state == 1) {
            playState1();
        }
    }

    // Title screen
    void drawTitleScreen() {
        textSize(50);
        fill(0);
        textAlign(CENTER);
        text("ROUND UP YOUR TOYS GAME", 400, 250);
        text("Press P to Play!", width / 2, height / 2 + 100);
    }

    // Game state 1: Drag circles into a cup
    void playState1() {
        for (Shape shape : shapes) {
            shape.update(); // Polymorphic update
            shape.draw();   // Polymorphic draw
        }
        // Call your drag-and-drop logic here
    }

    void initializeGameState1() {
        // Create some circles for the first state
        for (int i = 0; i < 20; i++) {
            shapes.add(new Circle(random(width), random(height), 30, this, random(-2, 2), random(-2, 2))); // Removed the 'i' argument

        }
    }

    public void keyPressed() {
        if (state == 0) {
            state = 1; // Transition to game state 1
        }
    }

    public void mousePressed() {
        for (Shape shape : shapes) {
            if (shape instanceof Circle && ((Circle) shape).isClicked(mouseX, mouseY)) {
                selectedShape = shape; // Store reference to clicked shape
            }
        }
    }
    
    public void mouseDragged() {
        if (selectedShape != null) {
            selectedShape.x = mouseX;
            selectedShape.y = mouseY;
        }
    }
    
    public void mouseReleased() {
        selectedShape = null; // Deselect shape when mouse is released
    }
}
