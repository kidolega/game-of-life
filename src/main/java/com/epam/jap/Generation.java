package com.epam.jap;

import java.util.Arrays;

class Generation implements Cloneable {

    final Boolean[][] originalCells;
    Boolean[][] currentCells;
    Boolean[][] futureCells;

    Generation(Boolean[][] cells) {
        this.originalCells = cells;
        this.currentCells = cells;
        this.futureCells = cells;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Generation that = (Generation) o;
        return Arrays.deepEquals(currentCells, that.currentCells);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(currentCells);
    }

    @Override
    public Generation clone() {
        return new Generation(Arrays.stream(currentCells).map(Boolean[]::clone).toArray(Boolean[][]::new));
    }

    void evolve() {
//        originalCells = currentCells.clone();
        for (int row = 1; row < originalCells[0].length - 1; row++) {
            for (int col = 1; col < originalCells.length - 1; col++) {
                evolveCell(row, col);
            }
        }
    }

    void evolveCell(int row, int col) {
        int counter = countFriends(row, col);

        if (originalCells[col][row] && (counter < 2 || counter > 3)) {
            currentCells[col][row] = false;
        }
        if (!originalCells[col][row] && counter == 3) {
            currentCells[col][row] = true;
        }
    }

    int countFriends(int row, int col) {
        int counter = 0;
        if (originalCells[row][col]) {
            counter--;
        }
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (originalCells[row + i][col + j]) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
