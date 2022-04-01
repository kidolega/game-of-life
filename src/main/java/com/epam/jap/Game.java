package com.epam.jap;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Creates The Game.
 *
 * @Author Marcin Dolega
 */
public record Game(World world, Printer printer) {

        static User user = new User();

    /**
     * Starts The Game.
     *
     * @param args shouldn't be written.
     */

    public static void main(String[] args) {

        World world = new World(user.setWorldDimensions(new Scanner(System.in)));
        Printer printer = new Printer(System.out, world);
        Game game = new Game(world, printer);
        game.play();

    }

    void play() {
        world.population.initializePopulation(world.width, world().height);
        do {
            printer.printWorld();
            world.evolveWorld();
            waitTillNextEvolution();
        } while (Arrays.deepEquals(world.population.evolvedGeneration, world.population.originalGeneration));
    }

    void waitTillNextEvolution() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignore) {
            ignore.printStackTrace();
        }
    }
}
