/*
Shape.java
Author: Anika Krieger
Project Name: Particle Engine 3
Date: September 25
Description: Parent class
*/

package com.particleengineREAL2;

import java.util.ArrayList;
import processing.core.PApplet;

public abstract class Shape {
    // Common properties for all shapes
    float x, y, size;
    float xVel;
    float yVel;
    PApplet main;
    boolean isSelected;

    // Constructor to initialize common attributes
    Shape(float x_, float y_, float size_, PApplet main_, float xVel_, float yVel_) {
        this.x = x_;
        this.y = y_;
        this.size = size_;
        this.main = main_;       
        this.xVel = xVel_; // X velocity
        this.yVel = yVel_; // Y velocity
        this.isSelected = false;
    }

    // Change the type to ArrayList<Shape> (not Shapes) to store different shapes
    public static ArrayList<Shape> createShapes(int circleCount, int squareCount, int triangleCount, PApplet main) {
        ArrayList<Shape> shapes = new ArrayList<>();

        int[] shapeCounts = {circleCount, squareCount, triangleCount};
        int totalShapes = circleCount + squareCount + triangleCount;

        // Use main.random() to call random() from the PApplet instance
        for (int i = 0; i < totalShapes; i++) {
            float size = main.random(50, 80);  // Corrected random call
            float x = main.random(main.width);
            float y = main.random(main.height);
            float speedX = main.random(-2, 2);
            float speedY = main.random(-2, 2);

            // Correct shape creation and add to ArrayList<Shape>
            if (i < shapeCounts[0]) {
                shapes.add(new Circle(x, y, size, main, speedX, speedY));
            } else if (i < shapeCounts[0] + shapeCounts[1]) {
                shapes.add(new Square(x, y, size, main, speedX, speedY));
            } else {
                shapes.add(new Triangle(x, y, size, main, speedX, speedY));
            }
        }

        return shapes;
    }

    protected void drawShape() {
        if (isSelected) {
            main.fill(0, 255, 0); // Change to green if selected
        } else {
            main.fill(0); // Default color is black
        }
    }

    // Abstract methods to be implemented by subclasses
    public abstract void draw();  // Each shape will implement its own drawing logic
    public abstract void update(ArrayList<Shape> shapes); // Pass shapes list for collision checks
    public abstract void mouseClicked();
    //public abstract void scatter(float clickX, float CkickY);
    

    // Common move behavior for all shapes
    public void move() {
        x += xVel;
        y += yVel;
        checkBoundary(); // Check if shape hits the screen boundaries
    }

    // Common boundary check for all shapes
    public void checkBoundary() {
        if (x < size /2 || x > main.width - size /2)
        {
            xVel *= -1;
            x = PApplet.constrain(x, size / 2, main.width - size / 2);
        }

        if (y < size /2 || y > main.height - size /2)
        {
            yVel *= -1;
            y = PApplet.constrain(y, size / 2, main.height - size / 2);
        }
        
    }

    // Getter and Setter for size
    public float getSize() {
        return size;
    }

    public void setSize(float newSize) {
        this.size = newSize;
    }

    public boolean isMouseOver(float mx, float my)
    {
        return mx >= x - size / 2 && mx <= x + size / 2 && my >= y - size / 2 && my <= y + size / 2;
    }

    public void shapeColor()
    {
        if (isSelected)
        {
            main.fill(0, 255, 0);
        } else {
            main.fill(0);
        }
        draw();
    }

    public void select() {
        isSelected = true;
    }

    public void deselect() {
        isSelected = false;
    }

    public boolean isClicked(float mx, float my) {
        return isMouseOver(mx, my);
    }

    public boolean checkCollission(Shape other) {
        float distance = PApplet.dist(this.x, this.y, other.x, other.y);
        return distance < (this.size / 2 + other.size / 2);
    }

    public void handleCollision(Shape other) {
        // Reverse the direction of both shapes
        this.xVel *= -1;
        this.yVel *= -1;
        other.xVel *= -1;
        other.yVel *= -1;

        float overlap = (this.size / 2 + other.size / 2) - PApplet.dist(this.x, this.y, other.x, other.y);
    if (overlap > 0) {
        float pushX = (other.x - this.x) / PApplet.dist(this.x, this.y, other.x, other.y);
        float pushY = (other.y - this.y) / PApplet.dist(this.x, this.y, other.x, other.y);
        this.x -= pushX * overlap / 2;
        this.y -= pushY * overlap / 2;
        other.x += pushX * overlap / 2;
        other.y += pushY * overlap / 2;
    }
}

}
