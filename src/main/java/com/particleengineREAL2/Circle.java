/*
Circle.java
Author: Anika Krieger
Project Name: Particle Engine 3
Date: September 25
Description: Circle - sets up circular particle and movement
*/

package com.particleengineREAL2;

import java.util.ArrayList;

import processing.core.PApplet;

public class Circle extends Shape {

    public int color;
    
    public Circle(float x_, float y_, float size_, PApplet main_, float xVel_, float yVel_) { // Constructor for Square class
        super(x_, y_, size_, main_, xVel_, yVel_); // Call to parent class constructor
        this.color = main.color(0);
    }

    @Override
    public void draw() {
        drawShape();
        //main.fill(color);
        main.ellipse(x, y, size, size); // Draw circle
    }
    
    

    @Override
    public void move() {
        x += xVel; // Update x position based on velocity
        y += yVel; // Update y position based on velocity
    }

    @Override
    public boolean isClicked(float mx, float my) {
        return isMouseOver(mx, my);
    }

    @Override
    public void update(ArrayList<Shape> shapes) {
        move();  
        checkBoundary(); 
        for (Shape other : shapes) {
            if (other instanceof Circle && other != this && checkCollission(other)) {
                handleCollision(other); // Handle collision with another Circle
            }
        }
    }
    

    public void mouseClicked() {
        color = main.color(0,255,0);
        size += 5; // Increase size on mouse click
    }
        
}
