package com.epam.jap;

import java.util.Arrays;

class Generation implements Cloneable {

    Boolean[][] cells;

    Generation(Boolean[][] cells) {
        this.cells = cells;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Generation that = (Generation) o;
        return Arrays.deepEquals(cells, that.cells);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(cells);
    }

    @Override
    public Generation clone() {
        return new Generation(Arrays.stream(cells).map(Boolean[]::clone).toArray(Boolean[][]::new));
    }

    int countFriends(int row, int col) {
        int counter = 0;
        if (cells[col][row]) {
            counter--;
        }
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (cells[col + i][row + j]) {
                    counter++;
                }
            }
        }
        return counter;
    }

    void evolveCell(int row, int col, Generation futureGeneration) {
        int counter = countFriends(row, col);
        if (cells[col][row] && (counter < 2 || counter > 3)) {
            futureGeneration.cells[col][row] = false;
        }
        if (!cells[col][row] && counter == 3) {
            futureGeneration.cells[col][row] = true;
        }
    }

    Generation evolve() {
        Generation generation = clone();
        for (int row = 1; row < cells.length - 1; row++) {
            for (int col = 1; col < cells[0].length - 1; col++) {
                evolveCell(row, col, generation);
            }
        }
        return generation;
    }
}
