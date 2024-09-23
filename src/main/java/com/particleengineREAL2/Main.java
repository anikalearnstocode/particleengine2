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
    Shapes shapes;
    public static void main(String[] args) 
    {
        PApplet.main("com.particleengineREAL2.Main");
    }

    public void settings()
    {
        size(700, 500); // size of window
    }

    public void setup()
    {
        shapes = new Shapes(this);

    }
    

    public void draw()
    {
        background(255); // clear background each frame
        shapes.draw(); // draw the circles
        
    }

    public void keyPressed()
    {
        shapes.keyPressed(); // call keyPressed in Super class when a key is pressed
    }

    public void mousePressed()
    {
        shapes.mousePressed(mouseX, mouseY); // call mousePressed in Super class when mouse is pressed
        shapes.mouseClicked();

    }

}
