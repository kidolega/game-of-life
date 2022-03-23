package com.epam.jap;

import java.util.ArrayList;
import java.util.Random;

public class World {

    private static final boolean IS_ALIVE = true;
    private static final boolean IS_DEAD = false;

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

    public World evolve() {
        return null;
    }


    private void die(World world) {
        for (int i = 0; i < world.height; i++) {
            for (int j = 0; j < world.width; j++) {
                if (world.cells[i][j]) {
                    System.out.print(1);
                } else {
                    System.out.print(0);
                }
            }
            System.out.println();
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
        for (int i = -1; i < 1; i++) {
            for (int j = -1; j < 1; j++) {
                if (cells[row + i][col + j]) {
                    counter++;
                }
            }
        }
        return counter;
    }
}