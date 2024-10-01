// TitleScreenState.java
package com.particleengineREAL2;

import processing.core.PApplet;

public class TitleState extends GameState {

    public TitleState(Main main) {
        super(main);
    }

    @Override
    public void draw() {
        main.background(255);
        main.getBathTubImage();

        main.textSize(48);
        main.fill(0);
        main.textAlign(PApplet.CENTER);
        main.text("Particle Engine Game", main.width / 2, main.height / 2 - 50);
        main.text("Press 'P' to Play with Circles", main.width / 2, main.height / 2 + 20);


    }

    @Override
    public void update() {
        // No updates needed on the title screen
    }

    public void keyPressed()
    {
        
    }

    @Override
    public void handleInput() {
        // Handle keyboard input if necessary (for example, press P to start)
    }

    @Override
    public void mousePressed(int mouseX, int mouseY) {
        // No mouse interaction needed on the title screen
    }

    @Override
    public void mouseDragged(int mouseX, int mouseY) {
        // No dragging interaction needed on the title screen
    }

    @Override
    public void mouseReleased() {
        // No mouse interaction needed on the title screen
    }
}
