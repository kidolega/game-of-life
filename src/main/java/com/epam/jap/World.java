package com.epam.jap;

import java.util.Random;

public class World {

    public int height;
    public int width;
    public Boolean[][] cells;

    public World() {
    }

    public World(int height, int width) {
        this.height = height;
        this.width = width;
        this.cells = new Boolean[height][width];
    }

    public World(int height, int width, Boolean[][] cells) {
        this.height = height;
        this.width = width;
        this.cells = cells;
    }

    public World(Boolean[][] cells) {
        this.cells = cells;
    }

    public World createNewWorld(int height, int width) {
        Boolean[][] cells = new Boolean[height][width];
        Random random = new Random();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                cells[row][col] = random.nextBoolean();
            }
        }
        return new World(height, width, cells);
    }

    public void evolve() {
        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                evolveCell(i, j, cells);
            }
        }
    }

    public void evolveCell(int row, int col, Boolean[][] cells) {
        int counter = countFriends(row, col, cells);
        if (cells[row][col] && (counter < 2 || counter > 3)) {
            cells[row][col] = false;
        }
        if (!cells[row][col] && counter == 3) {
            cells[row][col] = true;
        }
    }

    public int countFriends(int row, int col, Boolean[][] cells) {
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
