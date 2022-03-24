package com.epam.jap;

import static java.lang.System.out;

public class Game {

    static World world = new World();
    static Printer printer = new Printer(out);

    public static void main(String[] args) {

        int counter = 0;

        world.createNewWorld(10, 10);

        while (counter < 1000) {
            counter++;
            printer.printWorld(world);
            out.println();
            world.evolve();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void initialize() {

    }
}
