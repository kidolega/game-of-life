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
        game.getWorldDimensions();
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
            ignore.printStackTrace();
        }
    }

    void getWorldDimensions() {
        String width;
        String height;
        Pattern pattern = Pattern.compile("^[1-9][0-9]*$");
        Matcher matcher;
        do {
            System.out.println("Enter world width:");
            width = scanner.next();
            matcher = pattern.matcher(width);
        } while (!matcher.matches() || Integer.parseInt(width) < 1);
            world.width = Integer.parseInt(width);

        do {
            System.out.println("Enter world height:");
            height = scanner.next();
            matcher = pattern.matcher(height);
        } while (!matcher.matches() || Integer.parseInt(height) < 1);
        world.height = Integer.parseInt(height);
    }
}
