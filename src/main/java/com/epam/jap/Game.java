package com.epam.jap;

import static java.lang.System.out;

public class Game {

    public static Printer printer = new Printer(System.out);
    static World world = new World();

    public static void main(String[] args) {

        int counter = 0;
        world.createNewWorld(10, 10);
        World evolvedWorld = null;
        while (counter < 10) {
            counter++;
        printer.printWorld();
        out.println();
        world.evolve();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
