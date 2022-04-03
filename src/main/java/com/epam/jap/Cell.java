package com.epam.jap;

public class Cell {

    boolean state;

    public void revive() {
        this.state = true;
    }

    public void kill() {
        this.state = false;
    }
}
