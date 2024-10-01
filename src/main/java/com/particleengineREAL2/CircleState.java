package com.particleengineREAL2;


//import processing.core.PApplet;
import java.util.ArrayList;

public class CircleState extends GameState {
    
    public CircleState(Main main) {
        super(main);
        shapes = new ArrayList<>();
        initializeShapes();

    }

    public void initializeShapes() 
    {
        for (int i = 0; i < 10; i++)
        {
            shapes.add(new Circle(main.random(main.width), main.random(main.height), 80, main, main.random(-2, 2), main.random(-2, 2)));
        }
    }

    @Override
    public void draw()
    {
        main.background(255);
        main.getBathTubImage();
        updateAndDrawShapes();
        displayScore();

    }

 //   public void updateAndDrawShapes()  {}

    @Override
    public void update() {}

    @Override
    public void handleInput() {}

    @Override
    public void mousePressed(int mouseX, int mouseY) {
        super.mousePressed(mouseX, mouseY);
    }

    @Override
    public void mouseDragged(int mouseX, int mouseY) {
        super.mouseDragged(mouseX, mouseY); // Call the parent method
    }

    @Override
    public void mouseReleased() {
        super.mouseReleased();
    }
}