package com.epam.jap;

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
        game.setWorldDimensions();
        game.play();

    }

    void play() {
        world.initializeWorld();
        do {
            printer.printCurrentWorld(this, world);
        } while (true);
    }

    static Boolean[][] createCellsCopy(Boolean[][] cells) {
        return Arrays.stream(cells).map(Boolean[]::clone).toArray(Boolean[][]::new);
    }

    void waitTillNextEvolution(int timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
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
            string = scanner.next();
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
