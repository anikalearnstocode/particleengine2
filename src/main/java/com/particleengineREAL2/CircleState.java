package com.particleengineREAL2;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

public class CircleState extends GameState {
    private ArrayList<Shape> shapes;
    private PImage bathtub;
    private float tubX = 100, tubY = 100, pixelWidth = 200, pixelHeight = 100;
    private Shape selectedShape = null;


    public CircleState(Main main) {
        super(main);
        shapes = new ArrayList<>();
        bathtub = main.loadImage("Bathtub.png");
        initializeShapes();

    }

    private void initializeShapes() 
    {
        for (int i = 0; i < 20; i++)
        {
            shapes.add(new Circle(main.random(main.width), main.random(main.height), 30, main, main.random(-2, 2), main.random(-2, 2)));
        }
    }

    @Override
    public void draw()
    {
        main.background(255);
        main.image(bathtub, tubX, tubY, pixelWidth, pixelHeight); // Draw bathtub image

    for (int i = shapes.size() - 1; i >= 0; i--) { // Iterate backwards to safely remove shapes
            Shape shape = shapes.get(i); // Get the shape at index i
            shape.update(); // Polymorphic update
            shape.draw();   // Polymorphic draw
            
            // Check if shape is within the bathtub area
            if (shape.x > tubX && shape.x < tubX + pixelWidth && shape.y > tubY && shape.y < tubY + pixelHeight) {
                shapes.remove(i); // Remove shape from the list
            }
        }

        if (shapes.isEmpty()) {
            main.textSize(40);
            main.fill(0);
            main.textAlign(PApplet.CENTER);
            main.text("All circle toys in the bathtub!", main.width / 2, main.height / 2);
            main.text("Press 'T' for Triangle Round", main.width / 3, main.height / 3);
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