package com.epam.jap;

import java.util.Arrays;

public class Game {

    private World world;
    private final Printer printer;

    public Game(World world, Printer printer) {
        this.world = world;
        this.printer = printer;
    }


    public static void main(String[] args) {

        play();

    }

    private static void play() {

        Printer printer = new Printer(System.out);
        World world = new World(10, 20);

        world.initializeWorld();

        Boolean[][] cellsCopy;

        do {
            cellsCopy = createCellsCopy(world);
            printer.printWorld(world);
            world.evolve();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!compareOriginalCellsWithEvolved(world, cellsCopy));
    }

    private static Boolean[][] createCellsCopy(World world) {
        return Arrays.stream(world.cells).map(Boolean[]::clone).toArray(Boolean[][]::new);
    }

    private static boolean compareOriginalCellsWithEvolved(World world, Boolean[][] tempCells) {
        for (int i = 0; i < tempCells.length; i++) {
            for (int j = 0; j < tempCells[0].length; j++) {
                if (tempCells[i][j] != world.cells[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
