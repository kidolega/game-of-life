package com.epam.jap;

public class Game {

    private static Printer printer = new Printer();
    static World world = new World();

    public static void main(String[] args) {

        World newWorld = world.createNewWorld(3, 3);

        printer.printWorld(newWorld);
        System.out.println();
        newWorld.evolve();
        printer.printWorld(newWorld);

    }
}
