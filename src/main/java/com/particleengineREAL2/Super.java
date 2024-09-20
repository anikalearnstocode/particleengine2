/* 
Balls.java
Author: Anika Krieger
Project Name: Particle Engine
Date: September 19
Description: Super class - contains commands for all balls
*/

package com.particleengineREAL2;

import java.util.ArrayList;
import processing.core.*;

public class Super 
{
    PApplet main;
    ArrayList<Ball> balls;
    ArrayList<Square> squares;
    ArrayList<Triangle> triangles;

    int ballCount = 5; // total number of balls
    int sqrCount = 5;
    int triCount = 5;
    float ballRadius = 15; // uniform size for all balls
    float squareSize = 15;
    float triangleSize = 15;

    int backgroundColor = 0; // set background color to white

    Super(PApplet main_) 
    {
        main = main_; // initialize Balls object
        balls = new ArrayList<Ball>(); // initialize ArrayList to store balls
        squares = new ArrayList<Square>(); // initialize ArrayList to store balls
        triangles = new ArrayList<Triangle>(); // initialize ArrayList to store balls

        setup(); // ensure setup is called to initialize the balls
    }

    void setup() 
    {
        main.background(backgroundColor); // set background to default established above
        create(); // call create to create and set up balls
    }

    void create() 
    {
        balls.clear(); // clear balls list before initializing
        squares.clear();
        triangles.clear();

        for (int i = 0; i < ballCount; i++) 
        {
            float x = main.random(main.width); // assign initial position inside windown boundaries
            float y = main.random(main.height);

            int color = main.color(255); // give all balls color white 

            float xVel = main.random(-2, 2); // randomize velocity for each ball
            float yVel = main.random(-2, 2);

            balls.add(new Ball(x, y, ballRadius, main, color, xVel, yVel)); // create new Ball object and add to list
        }

        for (int i = 0; i < sqrCount; i++) 
        {
            float x = main.random(main.width); // assign initial position inside windown boundaries
            float y = main.random(main.height);

            int color = main.color(255); // give all balls color white 

            float xVel = main.random(-2, 2); // randomize velocity for each ball
            float yVel = main.random(-2, 2);

            squares.add(new Square(x, y, squareSize, main, color, xVel, yVel)); // create new Ball object and add to list
        }

        for (int i = 0; i < triCount; i++) 
        {
            float x = main.random(main.width); // assign initial position inside windown boundaries
            float y = main.random(main.height);

            int color = main.color(255); // give all balls color white 

            float xVel = main.random(-2, 2); // randomize velocity for each ball
            float yVel = main.random(-2, 2);

            triangles.add(new Triangle(x, y, triangleSize, main, color, xVel, yVel)); // create new Ball object and add to list
        }
    }

    void draw() 
    {
        main.background(backgroundColor); // set background color before drawing each frame
        main.noStroke(); // no outline for shapes

        for (Ball ball : balls) // draw the balls!
        {
            ball.draw();
        }

        // for (int i=0; i < balls.size(); i++)
        // {
        //     for (int j = i + 1; j < balls.size(); j++)
        //     {
        //         balls.get(i).checkCollission(balls.get(j));
        //     }
        // }

        for (Square sq : squares)
        {
            sq.draw();
        }

        for (Triangle tr : triangles)
        {
            tr.draw();
        }
        
    }

    void mousePressed(float clickX, float clickY) 
    {
        
        for (Ball ball : balls) // check if a ball is clicked when mouse is pressed
        {
            if (ball.isClicked(main.mouseX, main.mouseY)) 
            {
                backgroundColor = ball.ballColor; // if ball is clicked, update background color
                break; // exit the loop once ball is found
            }
        }

        for (Square sq : squares) // check if a ball is clicked when mouse is pressed
        {
            if (sq.isClicked(main.mouseX, main.mouseY)) 
            {
                backgroundColor = sq.squareColor; // if ball is clicked, update background color
                break; // exit the loop once ball is found
            }
        }

        for (Triangle tri : triangles) // check if a ball is clicked when mouse is pressed
        {
            if (tri.isClicked(main.mouseX, main.mouseY)) 
            {
                backgroundColor = tri.triangleColor; // if ball is clicked, update background color
                break; // exit the loop once ball is found
            }

        for(Ball ball : balls) {

            ball.scatter(clickX, clickY);
        }

        for(Square sq : squares) {

            sq.scatter(clickX, clickY);
        }

        for(Triangle t : triangles) {

            t.scatter(clickX, clickY);
        } 
    }
    }

    void keyPressed() 
    {
        // check if the "B" / "b" key is pressed
        if (main.key == 'b' || main.key == 'B') 
        {
            blueGradient(); // if so, apply blue gradient to all balls
        }

        // check if the "P" / "p" key is pressed
        if (main.key == 'p' || main.key == 'P') 
        {
            pinkGradient(); // if so, apply pink gradient to all balls
        }
    }

    void blueGradient() 
    {
        // if b/B is pressed, make all balls fall within the following color range
        for (Ball ball : balls) 
        {
            int r = (int)main.random(0, 205); // red value range
            int g = (int)main.random(136, 236); // green value range
            int b = (int)main.random(255); // blue value

            int color = main.color(r, g, b); // create color using RGB values
            ball.setColor(color); // set new ball color
        }

        for (Square sq : squares) 
        {
            int r = (int)main.random(0, 205);
            int g = (int)main.random(136, 236);
            int b = (int)main.random(255);

            int color = main.color(r, g, b);
            sq.setColor(color); // set new square color
        }

        for (Triangle tri : triangles) 
        {
            int r = (int)main.random(0, 205);
            int g = (int)main.random(136, 236);
            int b = (int)main.random(255);

            int color = main.color(r, g, b);
            tri.setColor(color); // set new triangle color
        }
    
    }

    void pinkGradient() 
    {
        // if p/P is pressed, make all balls fall within the following color range
        for (Ball ball : balls) 
        {
            int r = (int)main.random(255); // red val range
            int g = (int)main.random(60, 225); // green val range
            int b = (int)main.random(250, 255); // blue val range

            int color = main.color(r, g, b); // create color from RGB
            ball.setColor(color); // set new ball color
        }
        
        for (Square sq : squares) 
        {
            int r = (int)main.random(255);
            int g = (int)main.random(60, 225);
            int b = (int)main.random(250, 255);

            int color = main.color(r, g, b);
            sq.setColor(color); // set new square color
        }

        for (Triangle tri : triangles) 
        {
            int r = (int)main.random(255);
            int g = (int)main.random(60, 225);
            int b = (int)main.random(250, 255);

            int color = main.color(r, g, b);
            tri.setColor(color); // set new triangle color
        }    
    }
}
