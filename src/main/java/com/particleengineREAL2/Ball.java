/* 
Ball.java
Author: Anika Krieger
Project Name: Particle Engine 2
Date: September 19
Description: Ball - sets up first ball and movement
*/

package com.particleengineREAL2;

import processing.core.*;

public class Ball 
{
    PApplet main;
    float x, y; // location of the ball
    float xVel; // horizontal velocity
    float yVel; // vertical velocity
    float radius; // size of the ball
    int ballColor; // color of the ball

    Ball(float x_, float y_, float radius_, PApplet main_, int c, float xVel_, float yVel_) // constructor for Ball class: size, color, position, and velocity
    {
        x = x_; y = y_; // initial x and y coordinates
        radius = radius_; // initialize radius
        main = main_; // assign PApplet reference
        ballColor = c; // set ball color
        xVel = xVel_; // set horizontal velocity
        yVel = yVel_; // set vertical velocity
    }

    void draw() 
    {
        float newRadius = PApplet.map(main.mouseY, 0, main.height, 10, 50); // map between 10 and 50 for radius
        main.fill(ballColor); // set fill color of ball
        main.ellipse(x, y, newRadius * 2, newRadius * 2); // draw ball with new radius
        move(); // call move
    }

    void move() 
    {
        float speedFactor = PApplet.map(main.mouseX, 0, main.width, 0.5f, 2.0f); // map mouseX to a speed factor between 0.5 and 5.0

        // update x and y coordinates based on velocity and speed
        x += xVel * speedFactor; 
        y += yVel * speedFactor;
    
        // check if ball hits boundaries and if so reverse velocity 
        if (x > main.width - radius || x < radius) {
            xVel *= -1;
        }
        if (y > main.height - radius || y < radius) {
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

    // check if ball is clicked based on distance from mouse
    boolean isClicked(float mx, float my) 
    {
        float d = PApplet.dist(mx, my, x, y); // calculate distance from mouse to ball
        return d < radius; // return true if within radius
    }

    /*
    boolean isClicked(float mx, float my) 
    {
        switch (shapeType) {
            case "circle":
                return PApplet.dist(mx, my, x, y) < size / 2; // check for circle
            case "square":
                return mx >= x - size / 2 && mx <= x + size / 2 && my >= y - size / 2 && my <= y + size / 2; // check for square
            case "triangle":
                return mx >= x - size / 2 && mx <= x + size / 2 && my >= y - size / 2 && my <= y + size / 2; // check for triangle (bounding box check)
            default:
                return false;
        }
    }
     */

    // set ball color
    void setColor(int color) 
    {
        this.ballColor = color; 
    }
}
