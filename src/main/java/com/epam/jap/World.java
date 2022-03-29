package com.epam.jap;

import java.util.Arrays;
import java.util.Random;

/**
 * Class that contains all methods necessary to create living game world.
 */
public class World {

    public int height;
    public int width;
    Generation currentGeneration;
    Generation futureGeneration;
    Generation pastGeneration;

    public World() {
    }

    World(int height, int width) {
        this.height = height;
        this.width = width;
    }

    void initializeWorld() {
        Boolean[][] cells = new Boolean[height][width];
        Random random = new Random();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (row == 0 || row == height - 1 || col == 0 || col == width - 1) {
                    cells[row][col] = false;
                } else {
                    cells[row][col] = random.nextBoolean();
                }
            }
        }
        currentGeneration = new Generation(cells);
//        futureGeneration = currentGeneration.clone();
    }

    void evolveWorld() {
        currentGeneration.evolve();
    }
}
