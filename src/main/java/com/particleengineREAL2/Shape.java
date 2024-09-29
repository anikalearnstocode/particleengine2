/*
Shape.java
Author: Anika Krieger
Project Name: Particle Engine 3
Date: September 25
Description: Parent class
*/

package com.particleengineREAL2;

import processing.core.PApplet;

public abstract class Shape {
    // Common properties for all shapes
    float x, y, size;
    float xVel;
    float yVel;
    PApplet main;
   // int color;
    
        // Constructor to initialize common attributes
        Shape(float x_, float y_, float size_, PApplet main_, float xVel_, float yVel_) {
            this.x = x_;
            this.y = y_;
            this.size = size_;
            this.main = main_;       
            this.xVel = xVel_; // X velocity
            this. yVel = yVel_; // Y velocity
    }

    // Abstract methods to be implemented by subclasses
    public abstract void draw();  // Each shape will implement its own drawing logic
    public abstract void update(); // Each shape can have its own movement logic
    public abstract void mouseClicked();

    // Common move behavior for all shapes
    public void move() {
        x += xVel;
        y += yVel;

        // Bounce off screen edges
        
        checkBoundary(null); // Check if shape hits the screen boundaries
    }

    // Common boundary check for all shapes
    public void checkBoundary(Shape other) {
        // if (x < 0 || x > main.width) {
        //     xVel *= -1; // Reverse direction when hitting horizontal boundary
        // }
        // if (y < 0 || y > main.height) {
        //     yVel *= -1; // Reverse direction when hitting vertical boundary
        // }

            float distance = PApplet.dist(this.x, this.y, other.x, other.y); // Calculate distance between two shapes
            float combinedSize = this.size + other.size; // Sum of radii (or size equivalents)
    
            // If distance between centers is less than combined sizes, collision occurs
            if (distance < combinedSize) {
                // Reverse velocities of both shapes (basic collision response)
                this.xVel *= -1;
                this.yVel *= -1;
                other.xVel *= -1;
                other.yVel *= -1;
            }
        }
    

    // Getter and Setter for size (can be overridden if necessary in subclasses)
    public float getSize() {
        return size;
    }

    public void setSize(float newSize) {
        this.size = newSize;
    }

    // Optional: Add mouse interaction methods (abstract if subclasses need to implement them differently)
    public boolean isClicked(float mx, float my) {
        // Generic bounding box check (can be overridden by subclasses for more precise detection)
        return mx >= x - size / 2 && mx <= x + size / 2 && my >= y - size / 2 && my <= y + size / 2;
    }

    // public int getColor() 
    // { return color; }

    // public void setColor(int color) { // Setter for color
    //     this.color = color;
    // }
   
}