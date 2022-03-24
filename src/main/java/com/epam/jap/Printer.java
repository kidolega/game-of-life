package com.epam.jap;

import java.io.PrintStream;

class Printer {

    private final PrintStream out;
    private final World world = new World();

    public Printer(PrintStream out) {
        this.out = out;
    }

    void printCell(int row, int col, World world) {
        out.print(world.cells[row][col] ? "\u25CF" : " ");
    }

    void printHorizontalBorder() {
        out.print("\u2501");
    }

    void printVerticalBorder() {
        out.print("\u2503");
    }

    void printTopLeftCorner() {
        out.print("\u250F");
    }

    void printTopRightCorner() {
        out.print("\u2513");
    }

    void printBotLeftCorner() {
        out.print("\u2517");
    }

    void printBotRightCorner() {
        out.print("\u251B");
    }

    void printWorld(World world) {
        for (int row = 0; row < world.height; row++) {
            for (int col = 0; col < world.width; col++) {
                if (row == 0 || row == world.height - 1) {
                    if (row == 0 && col == 0) {
                        printTopLeftCorner();
                    } else if (row == 0 && col == world.width - 1) {
                        printTopRightCorner();
                    } else if (row == world.height - 1 && col == 0) {
                        printBotLeftCorner();
                    } else if (row == world.height - 1 && col == world.width - 1) {
                        printBotRightCorner();
                    } else {
                        printHorizontalBorder();
                    }
                } else if (col == 0 || col == world.width - 1) {
                    printVerticalBorder();
                } else {
                    printCell(row, col, world);
                }
            }
            out.println();
        }
    }
}
