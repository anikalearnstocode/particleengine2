/*
Super.java
Author: Anika Krieger
Project Name: Particle Engine
Date: September 19
Description: Shapes class - contains commands for all circles

NEW KEYPREESSED: shape resize for all 3, circles turn pink, triangles turn yellow, squares turn green
NEW MOUSEPRESSED: change bg color to color of clicked shape, scatter shapes on mouse click, new size alterations that are different for each shape
*/


package com.particleengineREAL2;

import java.util.ArrayList;
import processing.core.*;

public class Shapes 
{
    PApplet main; //reference main PApplet instance
    ArrayList<Circle> circles; //store circle objects
    ArrayList<Square> squares; //store square objects
    ArrayList<Triangle> triangles; //store tri objects

    int circleCount = 7; // total number of circles
    int sqrCount = 7; // total number of squares
    int triCount = 6; // total num of triangles
    float circleRadius = 15; // uniform size for all circles
    float squareSize = 30; //initial size of all squares
    float triangleSize = 30; //initial size of all triangles

    int backgroundColor = 0; // set background color to white

    Shapes(PApplet main_) 
    {
        main = main_; // initialize Super object
        circles = new ArrayList<Circle>(); // initialize ArrayList to store circles
        squares = new ArrayList<Square>(); // initialize ArrayList to store squares
        triangles = new ArrayList<Triangle>(); // initialize ArrayList to store triangles

        setup(); // ensure setup is called to initialize the circles
    }

    void setup() 
    {
        main.background(backgroundColor); // set background to default established above
        create(); // call create to create and set up circles
    }

    void create() 
    {
        circles.clear(); // clear circles list before initializing
        squares.clear(); // same as above
        triangles.clear(); // same as above

        //make circles
        for (int i = 0; i < circleCount; i++) 
        {
            float x = main.random(main.width); // assign initial position inside window boundaries
            float y = main.random(main.height); //same as above

            int color = main.color(255); // give all circles color white 

            float xVel = main.random(-2, 2); // randomize velocity for each circle
            float yVel = main.random(-2, 2); //same as above

            circles.add(new Circle(x, y, circleRadius, main, color, xVel, yVel)); // create new Circle object and add to list
        }

        //make yo squrs!
        for (int i = 0; i < sqrCount; i++) 
        {
            float x = main.random(main.width); //assign rand position within window
            float y = main.random(main.height);

            int color = main.color(255); //initial sq color

            float xVel = main.random(-2, 2); //randomize velocity
            float yVel = main.random(-2, 2);

            squares.add(new Square(x, y, squareSize, main, color, xVel, yVel)); // create new sq object and add to list
        }

        //make some triangles!
        for (int i = 0; i < triCount; i++) 
        {
            float x = main.random(main.width); //rand position within window
            float y = main.random(main.height);

            int color = main.color(255); //initial tri color

            float xVel = main.random(-2, 2); //randomize velocity
            float yVel = main.random(-2, 2);

            triangles.add(new Triangle(x, y, triangleSize, main, color, xVel, yVel)); //create new and add to list
        }
    }

    void draw() 
    {
        main.background(backgroundColor);  //clear background for frame
        main.noStroke();  //just looks ugly with outline 

        //update and draw
        for (Circle circle : circles) 
        {
            circle.update();
            circle.draw();
        }

        for (Square sq : squares)
        {
            sq.update();
            sq.draw();
        }

        for (Triangle tr : triangles)
        {
         //   tr.update();
            tr.draw();
        }

        // boundary collissions between shapes
        for (Circle circle : circles) 
        {
            for (Square sq : squares) 
            {
                circle.checkBoundary(circle); // circle checks against other circles
                sq.checkBoundary(circle); // square checks against circles
            }
        }
    }

    //resize all shapes to a new size (will be relevant for keyPressed)
    void shapeResize(float newSize)
    {
        for (Circle circle : circles)
        {
            circle.setSize(newSize);//resize to newSize
        }
       
        for (Square sq : squares)
        {
            sq.setSize(newSize);//resize to newSize
        }

        for (Triangle tr : triangles)
        {
            tr.setSize(newSize);//resize to newSize
        }

    }

    //handle mouse pressed events
    void mousePressed(float clickX, float clickY) 
    {
        //check if shape was clicked and if so change bg color to color of clicked shape
        for (Circle circle : circles) 
        {
            if (circle.isClicked(main.mouseX, main.mouseY)) 
            {
                backgroundColor = circle.circleColor; 
                break; 
            }
        }

        for (Square sq : squares) 
        {
            if (sq.isClicked(main.mouseX, main.mouseY)) 
            {
                backgroundColor = sq.squareColor; 
                break;
            }
        }

        for (Triangle tri : triangles) 
        {
            if (tri.isClicked(main.mouseX, main.mouseY)) 
            {
                backgroundColor = tri.triangleColor;
                break;
            }
        }

        //scatter shapes on mouse click
        for (Circle circle : circles) 
        {
            circle.scatter(clickX, clickY);
        }

        for (Square sq : squares) 
        {
            sq.scatter(clickX, clickY);
        }

        for (Triangle t : triangles) 
        {
            t.scatter(clickX, clickY);
        } 
    }

    //handle keyboard interactions
    void keyPressed() 
    {
        if (main.key == 'b' || main.key == 'B') 
    {
        changeTriangleColors(); // change only triangles to blue
    }

        if (main.key == 'p' || main.key == 'P') 
        {
            changeCircleColors(); 
        }

        if (main.key == 'y' || main.key == 'Y') 
        {
            changeSquareColors(); // change only squares to yellow, red, and orange
        }

        if (main.key == 'r' || main.key == 'R') 
        {
            shapeResize(50);  // resize all shapes to 50 on pressing 'r'
        }

        if (main.key == 'w' || main.key == 'W')
        {
            backgroundColor = (255); // make background white on keyPress w/W
        }

    }

    //to specify color range
    void changeCircleColors() 
    {
        for (Circle circle : circles) 
        {
            int r = (int)main.random(200, 255);
            int g = (int)main.random(50, 150);
            int b = (int)main.random(200, 255);

            int color = main.color(r, g, b);
            circle.setColor(color); 
        }
    }

    //to specify color range
    void changeTriangleColors() 
    { 
    for (Triangle tri : triangles) 
        {
        int r = (int)main.random(0, 100);
        int g = (int)main.random(150, 255);
        int b = (int)main.random(200, 255);

        int color = main.color(r, g, b);
        tri.setColor(color);
        }
    }
    
    //to specify color range
    void changeSquareColors() 
    {
        for (Square sq : squares) 
        {
            int r = (int)main.random(200, 255);
            int g = (int)main.random(150, 255);
            int b = (int)main.random(0); // Keep blue low for yellow/red/orange shades
    
            int color = main.color(r, g, b);
            sq.setColor(color);
        }
    
    }


     void mouseClicked() 
     {
        boolean triangleClicked = false; //check to see if __ was clicked... (start as false always)
        boolean squareClicked = false;
        boolean circleClicked = false;
    

        for (Square sq : squares) 
        {
            if (sq.isClicked(main.mouseX, main.mouseY)) //if clicked
            {
                squareClicked = true; //trigger behavior inside of sq class
                break; //leave loop
            }
        }
    
        for (Triangle tri : triangles) 
        {
            if (tri.isClicked(main.mouseX, main.mouseY)) //if clicked
            {
                triangleClicked = true; //trigger behavior inside of tr class
                break; //leave loop
            }
        }

        for (Circle circle : circles) 
        {
            if (circle.isClicked(main.mouseX, main.mouseY)) //if clicked
            {
                circleClicked = true; //trigger behavior inside of circ class
                break; //leave loop
            }
        }

        if (triangleClicked)
        {
            for (Triangle tri : triangles) //if tri clicked, double its current size
            {
                tri.setSize(tri.getSize() * 2);
            }
        }

        if (squareClicked)
        {
            for (Square sq : squares)
            {
                sq.setSize(sq.getSize() - 5); //if sq clicked, decrease size by 5
            }
        }

        if (circleClicked)
        {
            for (Circle circle : circles)
            {
                circle.setRadius(circle.getRadius() -10); //if circ clicked, decrease radius by 10
            }
        }
    }
}

    

