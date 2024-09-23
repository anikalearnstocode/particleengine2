/*
Circle.java
Author: Anika Krieger
Project Name: Particle Engine 2
Date: September 19
Description: Circle - sets up circular particle and movement
*/

package com.particleengineREAL2;

import processing.core.PApplet;

public class Circle 
{
    PApplet main;
    float x, y; // location of the circle
    float xVel; // horizontal velocity
    float yVel; // vertical velocity
    float radius; // radius of the circle
    int circleColor; // color of the circle

    Circle(float x_, float y_, float radius_, PApplet main_, int c, float xVel_, float yVel_) // constructor for Circle class
    {
        x = x_; y = y_; // initial x and y coordinates
        radius = radius_; // initialize radius
        main = main_; // assign PApplet reference
        circleColor = c; // set circle color
        xVel = xVel_; // set horizontal velocity
        yVel = yVel_; // set vertical velocity


    }

    void draw() 
    {   

        main.fill(circleColor); // set fill color of circle
        main.ellipse(x, y, radius * 2, radius * 2); // draw circle
        move(); // call move
        
    }

    void move() 
    {
        x += xVel; // update horizontal position
        y += yVel; // update vertical position

        if (x > main.width || x < 0) // check horizontal boundaries
        {
            xVel *= -1; // reverse horizontal direction
        }
        if (y > main.height || y < 0) // check vertical boundaries
        {
            yVel *= -1; // reverse vertical direction
        }
    }

    void scatter(float clickX, float clickY) 
    {
        xVel = (x - clickX) * 0.022f; // set horizontal velocity based on click distance
        yVel = (y - clickY) * 0.022f; // set vertical velocity based on click distance
    }

    void setColor(int newColor) 
    {
        circleColor = newColor; // change circle color
    }

    boolean isClicked(float mouseX, float mouseY) 
    {
        return PApplet.dist(mouseX, mouseY, x, y) < radius; // check if mouse is inside circle
    }

    void setSize(float newSize) 
    {
        radius = newSize / 2; // update radius based on new size
    }

    void reset(float x_, float y_, float xVel_, float yVel_) 
    {
        this.x = x_;
        this.y = y_;   
        this.xVel = xVel_;
        this.yVel = yVel_;
    }

    void checkBoundary(Circle other)
    {
        float distance = PApplet.dist(x,y,other.x,other.y);
        if(distance < radius + other.radius)
        {
            xVel *= -1;
            yVel *= -1;
            other.xVel *= -1;
            other.yVel *= -1;
        }
    }

    void update() 
    {
        x += xVel;
        y += yVel;

        if (x < radius || x > main.width - radius) 
        {
            xVel *= -1;
        }
        if (y < radius || y > main.height - radius) 
        {
            yVel *= -1;
        }
    }

}