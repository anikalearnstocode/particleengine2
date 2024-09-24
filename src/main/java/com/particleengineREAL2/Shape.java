/*
Circle.java
Author: Anika Krieger
Project Name: Particle Engine 2
Date: September 19
Description: Shape
*/

package com.particleengineREAL2;

import processing.core.PApplet;

public class Shape
{
    PApplet main;
 
    //int circleColor; // color of the circle

    float x, y; // location of the square
    float xVel; // horizontal velocity
    float yVel; // vertical velocity
    float size; // size of the square
    float radius; // radius of the circle
    


    int color; 
    
    Shape(float x_, float y_, float radius_, PApplet main_, int c, float xVel_, float yVel_) // constructor for Circle class
    {
        main = main_;
        x = x_; y = y_; // initial x and y coordinates
        radius = radius_; // initialize radius
        size = radius_;
        main = main_; // assign PApplet reference
        color = c; // set circle color
        xVel = xVel_; // set horizontal velocity
        yVel = yVel_; // set vertical velocity
    }

    void draw() 
    {   
        main.fill(color); // set fill color of circle
        // main.ellipse(x, y, radius * 2, radius * 2); // draw circle
        // move(); // call move
    }

    void move() 
    {
        
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
        color = newColor; // change circle color
    }

    boolean isClicked(float mouseX, float mouseY) 
    {
        return PApplet.dist(mouseX, mouseY, x, y) < radius; // check if mouse is inside circle
    }

    void setSize(float newSize) 
    {
        radius = newSize / 2; // update radius based on new size
    }

    // void reset()
    // {
    //     super.reset(circleRadius, circleRadius, circleRadius, circleRadius);
    // }

    void checkBoundary(Shape other) //collission detection
    {
        float distance = PApplet.dist(x,y,other.x,other.y); //check distance
        if(distance < radius + other.radius) //if distance less than half of other radius, reverse
        {
            xVel *= -1;
            yVel *= -1;
            other.xVel *= -1;
            other.yVel *= -1;
        }
    }

    void update() //update x and y positions 
    {
        x += xVel;
        y += yVel;

        if (x < radius || x > main.width - radius) //check boundaries of window and reverse if necessary
        {
            xVel *= -1;
        }
        if (y < radius || y > main.height - radius) 
        {
            yVel *= -1;
        }
    }

    public float getRadius() //getter
    {
        return radius;
    }
    void setRadius(float newRadius) //setter
    {
        radius = newRadius; // update new size
    }

    public void mouseClickedBehavior() //if clicked, shrink radius by 10
    {
        setRadius(getRadius() - 10);
    }

    int getColor()
    {
        return color; 
    }

}