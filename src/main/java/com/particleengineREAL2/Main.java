/* 
Main.java
Author: Anika Krieger
Project Name: Particle Engine
Date: September 19
Description: Particle Engine 2
*/

package com.particleengineREAL2;

import processing.core.PApplet;

public class Main extends PApplet 
{

    Super balls; // handles balls in simulation

    public static void main(String[] args) 
    {
        PApplet.main("com.particleengineREAL2.Main");
    }

    public void settings()
    {
        size(800, 600); // size of window
    }

    public void setup()
    {
        balls = new Super(this); // create instance of Balls class
        balls.setup(); // call setup to initialize balls
    }

    public void draw()
    {
        background(255); // clear background each frame
        balls.draw(); // draw the balls
    }

    public void keyPressed()
    {
        balls.keyPressed(); // call keyPressed in Balls class when a key is pressed
    }

    public void mousePressed()
    {
        balls.mousePressed(mouseX, mouseY); // call mousePressed in Balls class when mouse is pressed
    }
}
