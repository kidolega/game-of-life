package com.epam.jap;

import java.util.Arrays;

class Population implements Cloneable {

    Cell[][] originalGeneration;
    Cell[][] currentGeneration;
    Cell[][] evolvedGeneration;

    Population(Cell[][] original) {
        originalGeneration = original;
        currentGeneration = new Cell[original.length][original[0].length];
        evolvedGeneration = new Cell[original.length][original[0].length];
    }

    Population initializeGeneration(int width, int height) {
        originalGeneration = new Cell[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (row == 0 || row == height - 1 || col == 0 || col == width - 1) {
                    originalGeneration[row][col] = new Cell(false);
                } else {
                    originalGeneration[row][col] = new Cell();
                    originalGeneration[row][col].initializeCell();
                }
            }
        }
        currentGeneration = clone(originalGeneration);
//        evolvedGeneration = clone(currentGeneration);
        return new Population(originalGeneration);
    }

    Population evolveGeneration() {
        for (int row = 1; row < currentGeneration.length - 1; row++) {
            for (int col = 1; col < currentGeneration[0].length - 1; col++) {
                currentGeneration[row][col].evolveCell(row, col, this);
            }
        }
        originalGeneration = clone(currentGeneration);
        currentGeneration = clone(evolvedGeneration);
        return new Population(evolvedGeneration);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Population that = (Population) o;
        return Arrays.deepEquals(evolvedGeneration, that.evolvedGeneration);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(evolvedGeneration);
    }

    public Cell[][] clone(Cell[][] cells) {
        return Arrays.stream(cells)
                .map(Cell[]::clone)
                .toArray(Cell[][]::new);
    }
}
