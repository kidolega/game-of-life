package com.epam.jap;

public class Game {

    private static Printer printer = new Printer();
    static World world = new World();

    public static void main(String[] args) {

        World originalWorld = world.createNewWorld(5, 5);
        World evolvedWorld = null;
        while (originalWorld != evolvedWorld) {
        printer.printWorld(originalWorld);
        System.out.println();
        originalWorld.evolve();
        evolvedWorld = originalWorld;
        }
    }
}
