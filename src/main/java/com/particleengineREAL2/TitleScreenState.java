// TitleScreenState.java
package com.particleengineREAL2;

public class TitleScreenState extends GameState {

    public TitleScreenState(Main main) {
        super(main);
    }

    @Override
    public void draw() {
        main.drawTitleScreen(); // Call method from Main to draw title
    }

    @Override
    public void update() {
        // No updates needed on the title screen
    }

    @Override
    public void handleInput() {
        // Handle input for transitioning to the game state
        if (main.keyPressed) {
            main.setState(1); // Transition to game state 1
        }
    }
}
