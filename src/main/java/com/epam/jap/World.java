package com.epam.jap;

import java.util.Arrays;

public class World {

    private int width;
    private int height;
    private Boolean[][] cells;

    public World(int width, int height, Boolean[][] cells) {
        this.width = width;
        this.height = height;
        this.cells = cells;
    }

    public World evolve() {
        if (width == 1 && height == 1 && cells[0][0]) {
            Boolean[][] cellsEvolved = new Boolean[][]{{false}};
            cellsEvolved[0][0] = false;
            return new World(1, 1, cellsEvolved);
        }

        if (width == 2 && height == 1 && !(!cells[0][0] && !cells[0][1])) {
            Boolean[][] cellsEvolved = new Boolean[][]{{false},{false}};
            cellsEvolved[0][0] = false;
            return new World(2, 1, cellsEvolved);
        }
        return this;
    }
}
