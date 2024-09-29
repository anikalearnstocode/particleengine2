/*
Circle.java
Author: Anika Krieger
Project Name: Particle Engine 3
Date: September 25
Description: Circle - sets up circular particle and movement
*/

package com.particleengineREAL2;

import processing.core.PApplet;

public class Circle extends Shape {
    
    public Circle(float x_, float y_, float size_, PApplet main_, float xVel_, float yVel_) { // Constructor for Square class
        super(x_, y_, size_, main_, xVel_, yVel_); // Call to parent class constructor
    }

    @Override
    public void draw() {
        main.ellipse(x, y, size, size); // Draw circle
    }

    @Override
    public void move() {
        x += xVel; // Update x position based on velocity
        y += yVel; // Update y position based on velocity
    }

    @Override
    public boolean isClicked(float mx, float my) {
        float radius = size / 2;
        return PApplet.dist(mx, my, x, y) < radius; // Check if clicked within radius
    }

    @Override
    public void update() {
        move();   
    }

    public void mouseClicked() {
        size += 5; // Increase size on mouse click
    }
        
}
