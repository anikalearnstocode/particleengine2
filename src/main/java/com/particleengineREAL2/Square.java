/* 
SquareParticle.java
Author: Anika Krieger
Project Name: Particle Engine 2
Date: September 19
Description: SquareParticle - sets up square particle and movement
*/

package com.particleengineREAL2;

import processing.core.*;

public class Square 
{
    PApplet main;
    float x, y; // location of the square
    float xVel; // horizontal velocity
    float yVel; // vertical velocity
    float size; // size of the square
    int squareColor; // color of the square

    Square(float x_, float y_, float size_, PApplet main_, int c, float xVel_, float yVel_) // constructor for SquareParticle class
    {
        x = x_; y = y_; // initial x and y coordinates
        size = size_; // initialize size
        main = main_; // assign PApplet reference
        squareColor = c; // set square color
        xVel = xVel_; // set horizontal velocity
        yVel = yVel_; // set vertical velocity
    }

    void draw() 
    {
        main.fill(squareColor); // set fill color of square
        main.rect(x - size / 2, y - size / 2, size, size); // draw square
        move(); // call move
    }

    void move() 
    {
        float speedFactor = PApplet.map(main.mouseX, 0, main.width, 0.5f, 2.0f); // map mouseX to a speed factor between 0.5 and 2.0

        // update x and y coordinates based on velocity and speed
        x += xVel * speedFactor; 
        y += yVel * speedFactor;
    
        // check if square hits boundaries and if so reverse velocity 
        if (x > main.width - size / 2 || x < size / 2) {
            xVel *= -1;
        }
        if (y > main.height - size / 2 || y < size / 2) {
            yVel *= -1;
        }
    }

    void scatter(float clickX, float clickY) {
        float dx = x - clickX;
        float dy = y - clickY;
        float distance = PApplet.dist(x,y,clickX,clickY);
        if (distance > 0) {
            xVel = dx / distance * 5;
            yVel = dy / distance * 5;
        }
    }


    // check if square is clicked based on distance from mouse
    boolean isClicked(float mx, float my) 
    {
        return mx >= x - size / 2 && mx <= x + size / 2 && my >= y - size / 2 && my <= y + size / 2; // return true if within bounds
    }

    // set square color
    void setColor(int color) 
    {
        this.squareColor = color; 
    }
}
