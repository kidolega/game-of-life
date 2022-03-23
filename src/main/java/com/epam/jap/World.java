package com.epam.jap;

import java.util.ArrayList;

public class World {

    private static final boolean IS_ALIVE = true;
    private static final boolean IS_DEAD = false;

    private int height;
    private int width;
    private Boolean[][] cells;
    ArrayList<Cell> aliveCells;

    public World(int height, int width, Boolean[][] cells) {
        this.height = height;
        this.width = width;
        this.cells = cells;
    }

    public World evolve() {
        int aliveCellsCounter = 0;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (cells[row][col]) {
                    aliveCellsCounter++;
                    cells[row][col] = false;
//                    aliveCells.add(new Cell(row, col, true));
                }
            }
        }

        if (aliveCellsCounter == 0) {
            return this;
        }

        if (aliveCellsCounter < 3) {
            Boolean[][] evolvedCells = new Boolean[height][width];
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    evolvedCells[row][col] = IS_DEAD;
                }
            }
            return new World(height, width, evolvedCells);
        }

        return this;
    }
}
