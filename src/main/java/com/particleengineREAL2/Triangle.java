/* 
TriangleParticle.java
Author: Anika Krieger
Project Name: Particle Engine 3
Date: September 25
Description: TriangleParticle - sets up triangular particle and movement
*/

package com.particleengineREAL2;

import java.util.ArrayList;

import processing.core.PApplet;

public class Triangle extends Shape {

    public int color;

    
    public Triangle(float x_, float y_, float size_, PApplet main_, float xVel_, float yVel_) { // Constructor for Square class
        super(x_, y_, size_, main_, xVel_, yVel_); // Call to parent class constructor
        this.color = main.color(0);
    }

    @Override
    public void draw() {
       // main.fill(color);
        drawShape();
        main.pushMatrix();
        main.translate(x, y); // Move to particle's position
        main.triangle(-size / 2, size / 2, size / 2, size / 2, 0, -size / 2); // Draw triangle
        main.popMatrix();
    }

    @Override
    public void move() {
        x += xVel + 2; // Update x position based on velocity
        y += yVel + 2; // Update y position based on velocity
    }

    @Override
    public boolean isClicked(float mx, float my) {
        return mx >= x - size / 2 && mx <= x + size / 2 && my >= y - size / 2 && my <= y + size / 2; // Check for click within bounds
    }

    @Override
    public void update(ArrayList<Shape> shapes) {
        move();  
        checkBoundary(); 
        for (Shape other : shapes) {
            if (other != this && checkCollission(other)) {
                handleCollision(other); // Handle collision with another Circle
            }
        }
    }

    // @Override
    // public void scatter(float clickX, float clickY) {
    //     float dx = x - clickX;
    //     float dy = y - clickY;
    //     float distance = PApplet.dist(x, y, clickX, clickY);
    //     if (distance > 0) {
    //         xVel = dx / distance * 5;
    //         yVel = dy / distance * 5;
    //     }
    // }

    public void mouseClicked() {
        color = main.color(0,255,0);
        size += 5; // Increase size on mouse click
    }

    protected void drawShape() {
        if (isSelected) {
            main.fill(0,255,0);
        } else {
            main.fill(0);
        }
        }
    }


