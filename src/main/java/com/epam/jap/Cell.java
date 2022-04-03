package com.epam.jap;

import java.util.Random;

public class Cell {

    boolean state;

    public Cell(boolean state) {
        this.state = state;
    }

    public Cell() {
    }

    void initialize() {
        this.state = new Random().nextBoolean();
    }

    void revive() {
        this.state = true;
    }

    void kill() {
        this.state = false;
    }

    void evolveCell(int row, int col) {
        int counter = countFriends(row, col);
        if (currentCells[row][col].state && (counter < 2 || counter > 3)) {
            evolvedCells[row][col].kill();
        }
        if (!currentCells[row][col].state && counter == 3) {
            evolvedCells[row][col].revive();
        }
    }
    int countFriends(int row, int col) {
        int counter = 0;
        if (currentCells[row][col].state) {
            counter--;
        }
        return iterateOverClosestFriends(row, col, counter);
    }

}
