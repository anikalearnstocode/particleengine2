// SquareState.java
package com.particleengineREAL2;

import java.util.ArrayList;

//import processing.core.PApplet;

public class SquareState extends GameState {
    public SquareState(Main main) {
        super(main);
        shapes = new ArrayList<>();
        initializeShapes();
        getBathTubImage();
    }

    public void initializeShapes() {
        for (int i = 0; i < 10; i++) {
            shapes.add(new Square(main.random(main.width), main.random(main.height), 30, main, main.random(-2, 2), main.random(-2, 2)));
        }
    }

    private void getBathTubImage() {}

    @Override
    public void draw() {
        main.background(255); // Clear background
        main.image(main.bathtub, main.tubX, main.tubY, main.pixelWidth, main.pixelHeight);
        updateAndDrawShapes();
        displayScore();
    }

    @Override
    public void update()  {}

    @Override
    public void handleInput() {}

    @Override
    public void mousePressed(int mouseX, int mouseY) {
        super.mousePressed(mouseX, mouseY); // Use parent implementation
    }

    @Override
    public void mouseDragged(int mouseX, int mouseY) {
        super.mouseDragged(mouseX, mouseY); // Use parent implementation
    }

    @Override
    public void mouseReleased() {
        super.mouseReleased(); // Use parent implementation
    }
}
