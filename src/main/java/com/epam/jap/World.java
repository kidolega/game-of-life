package com.epam.jap;

import java.util.Random;

/**
 * Class that contains all methods necessary to create living game world.
 */
public class World {

    public int height;
    public int width;
    public Boolean[][] cells;

    public World() {
    }

    World(int height, int width, Boolean[][] cells) {
        this.height = height;
        this.width = width;
        this.cells = cells;
    }

    void initializeWorld(int height, int width) {
        Boolean[][] cells = new Boolean[height][width];
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
        Game.world = new World(height, width, cells);
    }

    void evolve() {
        for (int row = 1; row < width - 1; row++) {
            for (int col = 1; col < height - 1; col++) {
                evolveCell(row, col);
            }
        }
    }

    void evolveCell(int row, int col) {
        int counter = countFriends(row, col);
        if (cells[row][col] && (counter < 2 || counter > 3)) {
            cells[row][col] = false;
        }
        if (!cells[row][col] && counter == 3) {
            cells[row][col] = true;
        }
    }

    int countFriends(int row, int col) {
        int counter = 0;
        if (cells[row][col]) {
            counter--;
        }
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (cells[row + i][col + j]) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
