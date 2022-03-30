package com.epam.jap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Creates The Game.
 *
 * @Author Marcin Dolega
 */
public record Game(World world, Printer printer, Scanner scanner) {

    /**
     * Starts The Game.
     *
     * @param args shouldn't be written.
     */

    public static void main(String[] args) {

        Printer printer = new Printer(System.out);
        World world = new World(0, 0);
        Game game = new Game(world, printer, new Scanner(System.in));
        game.setWorldDimensions();
        game.play();

    }

    void play() {
        world.initializeWorld();
        do {
            printer.printCurrentWorld(this, world);
            world.evolveWorld();
            waitTillNextEvolution();
        } while (!Arrays.deepEquals(world.generation.evolvedCells, world.generation.originalCells));
    }

    void waitTillNextEvolution() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignore) {
            ignore.printStackTrace();
        }
    }

    int getSideSizeFromUser(String axis) {
        String string;
        Pattern pattern = Pattern.compile("^[1-9][0-9]*$");
        Matcher matcher;
        do {
            System.out.println("Enter world " + axis + ":");
            string = scanner.nextLine();
            matcher = pattern.matcher(string);
        } while (!matcher.matches() || Integer.parseInt(string) < 1);
        if (axis.equals("width")) {
            return Integer.parseInt(string);
        } else if (axis.equals("height")) {
            return Integer.parseInt(string);
        }
        return 0;
    }

    void setWorldDimensions() {
        world.width = getSideSizeFromUser("width");
        world.height = getSideSizeFromUser("height");
    }
}
