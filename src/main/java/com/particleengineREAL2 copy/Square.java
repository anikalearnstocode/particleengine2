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
        main.rectMode(PApplet.CENTER);
        main.rect(x, y, size, size); // draw square
        move(); // call move

    }

    void move() 
    {
        x += xVel; // update horizontal position
        y += yVel; // update vertical position

        // Check horizontal boundaries
        if (x > main.width - size / 2 || x < size / 2) 
        {
            xVel *= -1; // reverse horizontal direction
        }
        // Check vertical boundaries
        if (y > main.height - size / 2 || y < size / 2) 
        {
            yVel *= -1; // reverse vertical direction
        }
    }

    void scatter(float clickX, float clickY) 
    {
        xVel = (x - clickX) * 0.022f; // set horizontal velocity based on click distance
        yVel = (y - clickY) * 0.022f; // set vertical velocity based on click distance
    
    }

    // check if square is clicked based on distance from mouse
    boolean isClicked(float mx, float my) 
    {
        return mx >= x - size / 2 && mx <= x + size / 2 && my >= y - size / 2 && my <= y + size / 2; // return true if within bounds
    }

    // set square color
    void setColor(int color) 
    {
        this.squareColor = color; //update sq color
    }

    public float getSize() //getter
    {
        return size; 
    }
    void setSize(float newSize) //setter
    {
        size = newSize; // update new size
    }

    public void mouseClickedBehavior()
    {
        setSize(getSize() - 5);
    }

    void reset(float x_, float y_, float xVel_, float yVel_) 
    {
        this.x = x_; //reset x pos
        this.y = y_; //reset y pos
        this.xVel = xVel_; // reset horizontal vel
        this.yVel = yVel_; // reset vert vel
    }

    void checkBoundary(Circle other) //collission detection
    {
        float halfSize = size / 2; //half sq size
        float distance = PApplet.dist(x, y, other.x, other.y); //calculate dist to circ
        if (distance < halfSize + other.radius) //if distance less than combined radius sizes, reverse direction
        {
            xVel *= -1; 
            yVel *= -1; 
            other.xVel *= -1; 
            other.yVel *= -1; 
        }
    }

    void update() //called to update position
    {
        x += xVel; //update x and y pos based on velocity
        y += yVel;

        
        if (x < 0 || x > main.width - size) //check boundaries to reverse if out of bounds
        {
            xVel *= -1; 
        }
        if (y < 0 || y > main.height - size) 
        {
            yVel *= -1; 
        }
    }

}

