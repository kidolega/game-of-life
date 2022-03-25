package com.epam.jap;

import java.util.Random;

/**
 * Class that contains all methods necessary to create living game world.
 */
public class World {

    public int height;
    public int width;
    public Boolean[][] cells;
    public Boolean[][] futureCells;

    World(int height, int width) {
        this.height = height;
        this.width = width;
        cells = new Boolean[height][width];
    }

    void initializeWorld() {
        cells = new Boolean[height][width];
        Random random = new Random();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if ((row == 0 || row == height - 1) || (col == 0 || col == width - 1)) {
                    cells[row][col] = false;
                } else {
                    cells[row][col] = random.nextBoolean();
                }
            }
        }
        futureCells = Game.createCellsCopy(cells);
    }

    void evolveWorld() {
        for (int row = 1; row < width - 1; row++) {
            for (int col = 1; col < height - 1; col++) {
                evolveCell(row, col);
            }
        }
        cells = Game.createCellsCopy(futureCells);
    }

    void evolveCell(int row, int col) {
        int counter = countFriends(row, col);
        if (cells[col][row] && (counter < 2 || counter > 3)) {
            futureCells[col][row] = false;
        }
        if (!cells[col][row] && counter == 3) {
            futureCells[col][row] = true;
        }
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
}
