package com.epam.jap;

import java.io.PrintStream;


class Printer {

    World world;

    private final PrintStream out;
    private final String horizontalBorder = "\u2501"; // prints ━
    private final String verticalBorder = "\u2503"; // prints ┃
    private final String topLeftCorner = "\u250F";  // prints ┏
    private final String topRightCorner = "\u2513"; // prints ┓
    private final String botLeftCorner = "\u2517"; // prints ┗
    private final String botRightCorner = "\u251B"; // prints ┛

    public Printer(PrintStream out, World world) {
        this.out = out;
        this.world = world;
    }

    void printCell(int row, int col) {
        out.print(world.population.currentGeneration[row][col].state ? "\u25CF" : " "); // prints ●
    }

    void printWorld() {
        for (int row = 0; row < world.height; row++) {
            for (int col = 0; col < world.width; col++) {
                if (row == 0 || row == world.height - 1) {
                    if (row == 0 && col == 0) {
                        out.print(topLeftCorner);
                    } else if (row == 0 && col == world.width - 1) {
                        out.print(topRightCorner);
                    } else if (row == world.height - 1 && col == 0) {
                        out.print(botLeftCorner);
                    } else if (row == world.height - 1 && col == world.width - 1) {
                        out.print(botRightCorner);
                    } else {
                        out.print(horizontalBorder);
                    }
                } else if (col == 0 || col == world.width - 1) {
                    out.print(verticalBorder);
                } else {
                    printCell(row, col);
                }
            }
            out.println();
        }
    }
}
