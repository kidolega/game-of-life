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
        this.population = new Population(new Cell[width][height]).initializeGeneration(width, height);
    }

    void evolveWorld() {
        population.evolveGeneration();
    }
}
