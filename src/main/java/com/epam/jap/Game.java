package com.epam.jap;

import java.util.Arrays;

import static java.lang.System.out;

public class Game {

    static World world = new World();
    static Printer printer = new Printer(out);
//    static Boolean[][] cellsCopy;

    public static void main(String[] args) {

        initialize();
    }

    private static void initialize() {

        world.initializeWorld(5, 5);
        Boolean[][] cellsCopy;

        do {
            cellsCopy = Arrays.stream(world.cells).map(Boolean[]::clone).toArray(Boolean[][]::new);
            printer.printWorld(world);
            world.evolve();
            compareCellSets(cellsCopy);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!compareCellSets(cellsCopy));
    }

//    private static Boolean[][] createCellsCopy() {
//        return Arrays.stream(world.cells).map(Boolean[]::clone).toArray(Boolean[][]::new);
//    }

    private static boolean compareCellSets(Boolean[][] tempCells) {
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
