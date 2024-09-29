// SquareState.java
package com.particleengineREAL2;

import java.util.ArrayList;

import processing.core.PApplet;

public class SquareState extends GameState {
    private ArrayList<Shape> shapes;
    private Shape selectedShape = null;


    public SquareState(Main main) {
        super(main);
        shapes = new ArrayList<>();
        initializeShapes();
    }

    private void initializeShapes() {
        for (int i = 0; i < 20; i++) {
            shapes.add(new Square(main.random(main.width), main.random(main.height), 30, main, main.random(-2, 2), main.random(-2, 2)));
        }
    }

    @Override
    public void draw() {
        main.background(255); // Clear background

        for (int i = shapes.size() - 1; i >= 0; i--) { // Iterate backwards to safely remove shapes
            Shape shape = shapes.get(i); // Get the shape at index i
            shape.update(); // Polymorphic update
            shape.draw();   // Polymorphic draw
            
            // Check if shape is within the bathtub area
            if (shape.x > main.tubX && shape.x < main.tubX + main.pixelWidth && shape.y > main.tubY && shape.y < main.tubY + main.pixelHeight) {
                shapes.remove(i); // Remove shape from the list
            }
        }

        if (shapes.isEmpty()) {
            main.textSize(40);
            main.fill(0);
            main.textAlign(PApplet.CENTER);
            main.text("All squares in the bathtub!", main.width / 2, main.height / 2);
        }
    }

    @Override
    public void update()  {}

    @Override
    public void handleInput() {}

    @Override
    public void mousePressed(int mouseX, int mouseY) {
        // Check if any shape is clicked and select it
        for (Shape shape : shapes) {
            if (shape.isClicked(mouseX, mouseY)) {
                selectedShape = shape;
                break;
            }
        }
    }
    @Override
    public void mouseDragged(int mouseX, int mouseY) {
        // If a shape is selected, update its position based on mouse dragging
        if (selectedShape != null) {
            selectedShape.x = mouseX;
            selectedShape.y = mouseY;
        }
    }

    @Override
    public void mouseReleased() {
        // Deselect the shape when the mouse is released
        selectedShape = null;
    }
}