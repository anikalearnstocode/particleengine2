// TitleScreenState.java
package com.particleengineREAL2;

public class TitleState extends GameState {

    public TitleState(Main main) {
        super(main);
    }

    @Override
    public void draw() {
        main.drawTitleState(); // Call method from Main to draw title
    }

    @Override
    public void update() {
        // No updates needed on the title screen
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
