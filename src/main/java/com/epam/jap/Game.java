package com.epam.jap;

import java.util.Arrays;

public record Game(World world, Printer printer) {

    public static void main(String[] args) {


        Printer printer = new Printer(System.out);
        World world = new World(4, 8);
        Game game = new Game(world, printer);
        game.play();

    }

    void play() {

        world.initializeWorld();

        Boolean[][] cellsCopy;

        do {
            cellsCopy = createCellsCopy(world.cells);
            printer.printWorld(world);
            world.evolve();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!compareOriginalCellsWithEvolved(cellsCopy));
    }

    static Boolean[][] createCellsCopy(Boolean[][] cells) {
        return Arrays.stream(cells).map(Boolean[]::clone).toArray(Boolean[][]::new);
    }

    boolean compareOriginalCellsWithEvolved(Boolean[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j] != world.cells[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
