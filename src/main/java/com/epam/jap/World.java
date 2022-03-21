package com.epam.jap;

import java.util.Arrays;

public class World {

    private int width;
    private int height;
    private Boolean[][] cells;

    public World(int width, int height, Boolean[][] cells) {

    }

    public World evolve() {
        if (width == 1 && height == 1 && cells[0][0]) {
            Boolean[][] newCells = cells;
            newCells[0][0] = false;
            return new World(1, 1, newCells);
        }
        return this;
    }
}
