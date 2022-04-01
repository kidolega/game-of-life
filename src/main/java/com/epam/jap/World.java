package com.epam.jap;

/**
 * Class that contains all methods necessary to create living game world.
 */
public class World {

    int height;
    int width;
    Population population;

    World(int[] dimensions) {
        this.width = dimensions[0];
        this.height = dimensions[1];
        this.population = initializeDeadWorld(width, height);
    }

    Population initializeDeadWorld(int width, int height) {
        population.originalGeneration = new Cell[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                population.originalGeneration[row][col] = new Cell(false);
            }
        }
        return population;
    }
    void evolveWorld() {
        population.evolveGeneration();
    }
}
