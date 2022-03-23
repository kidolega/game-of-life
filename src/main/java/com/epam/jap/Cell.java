package com.epam.jap;

public class Cell {
    public boolean isAlive = true;
    private int row;
    private int col;

    public Cell(int row, int col, boolean isAlive) {
        this.row = row;
        this.col = col;
        this.isAlive = isAlive;
    }

    public Cell() {
    }

    public void die() {
        isAlive = false;
    }
}
