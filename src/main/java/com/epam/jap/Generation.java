package com.epam.jap;

import java.util.Arrays;

class Generation implements Cloneable {

    Boolean[][] originalCells;
    Boolean[][] currentCells;
    Boolean[][] pastCells;

    Generation(Boolean[][] original) {
        this.originalCells = original;
        this.currentCells = copyCells(originalCells);
    }

    Generation evolve() {
        for (int row = 1; row < originalCells.length - 1; row++) {
            for (int col = 1; col < originalCells[0].length - 1; col++) {
                evolveCell(row, col);
            }
        }

        pastCells = copyCells(originalCells);
        originalCells = copyCells(currentCells);
        return new Generation(currentCells);
    }

    void evolveCell(int row, int col) {
        int counter = countFriends(row, col);
        if (originalCells[row][col] && (counter < 2 || counter > 3)) {
            currentCells[row][col] = false;
        }
        if (!originalCells[row][col] && counter == 3) {
            currentCells[row][col] = true;
        }
    }

    int countFriends(int row, int col) {
        int counter = 0;
        if (originalCells[row][col]) {
            counter--;
        }
        return iterateOverClosestFriends(row, col, counter);
    }

    int iterateOverClosestFriends(int row, int col, int counter) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (originalCells[row + i][col + j]) {
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
        return Arrays.deepEquals(currentCells, that.currentCells);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(currentCells);
    }

    @Override
    public Generation clone() {
        return new Generation(Arrays.stream(originalCells)
                .map(Boolean[]::clone)
                .toArray(Boolean[][]::new));
    }

    public Boolean[][] copyCells(Boolean[][] cells) {
        return Arrays.stream(cells).map(Boolean[]::clone).toArray(Boolean[][]::new);
    }
}
