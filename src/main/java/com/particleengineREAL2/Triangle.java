/* 
TriangleParticle.java
Author: Anika Krieger
Project Name: Particle Engine 2
Date: September 19
Description: TriangleParticle - sets up triangular particle and movement
*/

package com.particleengineREAL2;

import processing.core.*;

public class Triangle extends Shape
{

    Triangle(float x_, float y_, float size_, PApplet main_, int c, float xVel_, float yVel_) // constructor for TriangleParticle class
    {
        super( x_,  y_,  size_,  main_,  c,  xVel_,  yVel_);
        // x = x_; y = y_; // initial x and y coordinates
        // size = size_; // initialize size
        // main = main_; // assign PApplet reference
        // triangleColor = c; // set triangle color
        // xVel = xVel_; // set horizontal velocity
        // yVel = yVel_; // set vertical velocity
    }

    void draw() 
    {
        super.draw();
        main.pushMatrix(); // save current transformation
        main.translate(x, y); // move to particle's position
        main.triangle(-size / 2, size / 2, size / 2, size / 2, 0, -size / 2); // draw triangle 
        main.fill(100);       
        main.triangle(-size / 5, size / 5, size / 5, size / 5, 0, -size / 5); // draw triangle

        main.popMatrix(); // restore transformation
        move(); // call move
    }

    void move() 
    {
        super.move();

        float speedFactor = PApplet.map(main.mouseX, 0, main.width, 0.5f, 2.0f); // map mouseX to a speed factor between 0.5 and 2.0

        // update x and y coordinates based on velocity and speed
        x += xVel * speedFactor; 
        y += yVel * speedFactor;

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
    
    // void reset()
    // {
    //     super.reset(circleRadius, circleRadius, circleRadius, circleRadius);
    // }
}
