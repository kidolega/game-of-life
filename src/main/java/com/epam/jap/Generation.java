package com.epam.jap;

import java.util.Arrays;

class Generation implements Cloneable {

    Cell[][] currentCells;
    Cell[][] evolvedCells;
    Cell[][] originalCells;

    Generation(Cell[][] original) {
        this.currentCells = original;
        this.evolvedCells = copyCells(currentCells);
    }

//    void initializeGeneration() {
//        Arrays.stream(originalCells).toArray()
//    }

    Generation evolve() {
        for (int row = 1; row < currentCells.length - 1; row++) {
            for (int col = 1; col < currentCells[0].length - 1; col++) {
                evolveCell(row, col);
            }
        }

        originalCells = copyCells(currentCells);
        currentCells = copyCells(evolvedCells);
        return new Generation(evolvedCells);
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

    int iterateOverClosestFriends(int row, int col, int counter) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (currentCells[row + i][col + j].state) {
                    counter++;
                }
            }
        }
        return counter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Generation that = (Generation) o;
        return Arrays.deepEquals(evolvedCells, that.evolvedCells);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(evolvedCells);
    }

    @Override
    public Generation clone() {
        return new Generation(Arrays.stream(currentCells)
                .map(Cell[]::clone)
                .toArray(Cell[][]::new));
    }

    public Cell[][] copyCells(Cell[][] cells) {
        return Arrays.stream(cells).map(Cell[]::clone).toArray(Cell[][]::new);
    }
}
