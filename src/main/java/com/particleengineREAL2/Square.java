/* 
SquareParticle.java
Author: Anika Krieger
Project Name: Particle Engine 2
Date: September 19
Description: SquareParticle - sets up square particle and movement
*/

package com.particleengineREAL2;

import java.util.ArrayList;

import processing.core.PApplet;

public class Square extends Shape {

    public int color;

    
    public Square(float x_, float y_, float size_, PApplet main_, float xVel_, float yVel_) { // Constructor for Square class
        super(x_, y_, size_, main_, xVel_, yVel_); // Call to parent class constructor
        this.color = main.color(0);
    }

    public void draw() {
        drawShape();
        //main.fill(color);
        main.rectMode(PApplet.CENTER);
        main.rect(x, y, size, size); // Draw square
    }

    @Override
    public void move() {
        x += xVel + 6; // Update x position based on velocity
        y += yVel + 6; // Update y position based on velocity
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
        select();
        //color = main.color(0,255,0);
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
