/* 
TriangleParticle.java
Author: Anika Krieger
Project Name: Particle Engine 2
Date: September 19
Description: TriangleParticle - sets up triangular particle and movement
*/

package com.particleengineREAL2;

import processing.core.*;

public class Triangle
{
    PApplet main;
    float x, y; // location of the triangle
    float xVel; // horizontal velocity
    float yVel; // vertical velocity
    float size; // size of the triangle
    int triangleColor; // color of the triangle

    Triangle(float x_, float y_, float size_, PApplet main_, int c, float xVel_, float yVel_) // constructor for TriangleParticle class
    {
        x = x_; y = y_; // initial x and y coordinates
        size = size_; // initialize size
        main = main_; // assign PApplet reference
        triangleColor = c; // set triangle color
        xVel = xVel_; // set horizontal velocity
        yVel = yVel_; // set vertical velocity
    }

    void draw() 
    {
        main.fill(triangleColor); // set fill color of triangle
        main.pushMatrix(); // save current transformation
        main.translate(x, y); // move to particle's position
        main.triangle(-size / 2, size / 2, size / 2, size / 2, 0, -size / 2); // draw triangle
        main.popMatrix(); // restore transformation
        move(); // call move
    }

    void move() 
    {
        float speedFactor = PApplet.map(main.mouseX, 0, main.width, 0.5f, 2.0f); // map mouseX to a speed factor between 0.5 and 2.0

        // update x and y coordinates based on velocity and speed
        x += xVel * speedFactor; 
        y += yVel * speedFactor;
    
        // check if triangle hits boundaries and if so reverse velocity 
        if (x > main.width - size / 2 || x < size / 2) 
        {
            xVel *= -1;
        }
        if (y > main.height - size / 2 || y < size / 2) 
        {
            yVel *= -1;
        }
    }

    void scatter(float clickX, float clickY) 
    {
        float dx = x - clickX;
        float dy = y - clickY;
        float distance = PApplet.dist(x,y,clickX,clickY);
        if (distance > 0) {
            xVel = dx / distance * 5;
            yVel = dy / distance * 5;
        }
    }

    // check if triangle is clicked based on distance from mouse
    boolean isClicked(float mx, float my) 
    {
        // Simplified check for bounding box
        return mx >= x - size / 2 && mx <= x + size / 2 && my >= y - size / 2 && my <= y + size / 2; // return true if within bounds
    }

    // set triangle color
    void setColor(int color) 
    {
        this.triangleColor = color; //update color
    }

    public float getSize() //getter
    {
        return size;
    }
    
    void setSize(float newSize) //setter
    {
        size = newSize; // update size directly
    }

    public void mouseClickedBehavior()
    {
        setSize(getSize() + 5);
    }
    
    void reset(float x_, float y_, float xVel_, float yVel_) //reset x and y positions and velocities
    {
        this.x = x_;
        this.y = y_;
        this.xVel = xVel_;
        this.yVel = yVel_;
    }
}
