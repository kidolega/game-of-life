package com.epam.jap;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Creates The Game.
 *
 * @Author Marcin Dolega
 */
public record Game(World world, Printer printer) {

    /**
     * Starts The Game.
     *
     * @param args shouldn't be written.
     */

    public static void main(String[] args) {

        Printer printer = new Printer(System.out);
        World world = new World(0, 0);
        Game game = new Game(world, printer);
        System.out.println("Hello!");
        game.setWorldDimensions(new Scanner(System.in));
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

    int getSideSizeFromUser(@NotNull String axis, @NotNull Scanner scanner) {
        String string;
        Pattern pattern = Pattern.compile("^[1-9][0-9]*$");
        Matcher matcher;
        do {
            System.out.println("Enter world " + axis + ":");
            string = scanner.nextLine();
            matcher = pattern.matcher(string);
        } while (!matcher.matches());
        if (axis.equals("width")) {
            return Integer.parseInt(string);
        } else if (axis.equals("height")) {
            return Integer.parseInt(string);
        }
        return 0;
    }

    void setWorldDimensions(Scanner scanner) {
        world.width = getSideSizeFromUser("width", scanner);
        world.height = getSideSizeFromUser("height", scanner);
    }
}
