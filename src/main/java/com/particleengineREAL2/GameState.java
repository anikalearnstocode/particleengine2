// GameState.java
package com.particleengineREAL2;

public abstract class GameState {
    protected Main main; // Reference to the main application

    public GameState(Main main) {
        this.main = main; // Link back to the main class
    }

    public abstract void draw(); // Method to draw the state
    public abstract void update(); // Method to update the state
    public abstract void handleInput(); // Method to handle input
}
