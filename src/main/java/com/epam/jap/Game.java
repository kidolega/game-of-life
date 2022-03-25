package com.epam.jap;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Creates The Game.
 *
 * @Author Marcin Dolega
 */
public record Game(World world, Printer printer) {

    static Scanner scanner = new Scanner(System.in);
    /**
     * Starts The Game.
     *
     * @param args shouldn't be written.
     */
    public static void main(String[] args) {

        Printer printer = new Printer(System.out);
        World world = new World();
        Game game = new Game(world, printer);

        System.out.println("Enter world width:");
        world.width = scanner.nextInt();
        System.out.println("Enter world height:");
        world.height = scanner.nextInt();

        game.play();

    }

    void play() {
        world.initializeWorld();
        do {
            printer.printCurrentWorld(this, world);
        } while (!compareFutureCellsWithEvolved(world.pastCells));
    }

    static Boolean[][] createCellsCopy(Boolean[][] cells) {
        return Arrays.stream(cells).map(Boolean[]::clone).toArray(Boolean[][]::new);
    }

    boolean compareFutureCellsWithEvolved(Boolean[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j] != world.futureCells[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    void waitTillNextEvolution(int timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
        } catch (InterruptedException ignore) {
        }
    }
}
