/* 
SquareParticle.java
Author: Anika Krieger
Project Name: Particle Engine 2
Date: September 19
Description: SquareParticle - sets up square particle and movement
*/

package com.particleengineREAL2;

import processing.core.PApplet;

public class Square extends Shape {
    
    public Square(float x_, float y_, float size_, PApplet main_, float xVel_, float yVel_) { // Constructor for Square class
        super(x_, y_, size_, main_, xVel_, yVel_); // Call to parent class constructor
    }

        public void draw() {
        //super.draw();
        main.rectMode(PApplet.CENTER);
        main.rect(x, y, size, size); // Draw square
    }

    @Override
    public void move() {
        x += xVel; // Update x position based on velocity
        y += yVel; // Update y position based on velocity
    }

    @Override
    public boolean isClicked(float mx, float my) {
        return mx >= x - size / 2 && mx <= x + size / 2 && my >= y - size / 2 && my <= y + size / 2; // Check for click within bounds
    }

    @Override
    public void update() {
        move();  
        checkBoundary(); 
 
    }

    public void mouseClicked() {
        size += 5; // Increase size on mouse click
    }
        
}