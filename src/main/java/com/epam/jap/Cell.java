package com.epam.jap;

import java.util.Arrays;
import java.util.Random;

public class Cell {

    boolean state;

    public Cell(boolean state) {
        this.state = state;
    }

    public Cell() {
    }

    void initializeCell() {
        this.state = new Random().nextBoolean();
    }

    void revive() {
        this.state = true;
    }

    void kill() {
        this.state = false;
    }

    void evolveCell(int row, int col, Population population) {
        int counter = countFriends(row, col, population);
        if (population.currentGeneration[row][col].state && (counter < 2 || counter > 3)) {
            population.evolvedGeneration[row][col].kill();
        }
        if (!population.currentGeneration[row][col].state && counter == 3) {
            population.evolvedGeneration[row][col].revive();
        }
    }

    int countFriends(int row, int col, Population population) {
        int counter = 0;
        if (population.currentGeneration[row][col].state) {
            counter--;
        }
        return iterateOverClosestFriends(row, col, population, counter);
    }

    int iterateOverClosestFriends(int row, int col, Population population, int counter) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (population.currentGeneration[row + i][col + j].state) {
                    counter++;
                }
            }
        }
        return counter;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        Cell that = (Cell) o;
//        return Arrays.deepEquals(state, that.state);
//    }
//
//    @Override
//    public int hashCode() {
//        return Arrays.deepHashCode(state);
//    }

}
