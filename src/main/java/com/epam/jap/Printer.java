package com.epam.jap;

import java.io.PrintStream;

class Printer {

    private final PrintStream out;
    private final String HORIZONTAL_BORDER = "\u2501";
    private final String VERTICAL_BORDER = "\u2503";
    private final String TOP_LEFT_CORNER = "\u250F";
    private final String TOP_RIGHT_CORNER = "\u2513";
    private final String BOT_LEFT_CORNER = "\u2517";
    private final String BOT_RIGHT_CORNER = "\u251B";

    public Printer(PrintStream out) {
        this.out = out;
    }

    void printCell(int row, int col, World world) {
        out.print(world.cells[row][col] ? "\u25CF" : " ");
    }

    void printWorld(World world) {
        for (int row = 0; row < world.height; row++) {
            for (int col = 0; col < world.width; col++) {
                if (row == 0 || row == world.height - 1) {
                    if (row == 0 && col == 0) {
                        out.print(TOP_LEFT_CORNER);
                    } else if (row == 0 && col == world.width - 1) {
                        out.print(TOP_RIGHT_CORNER);
                    } else if (row == world.height - 1 && col == 0) {
                        out.print(BOT_LEFT_CORNER);
                    } else if (row == world.height - 1 && col == world.width - 1) {
                        out.print(BOT_RIGHT_CORNER);
                    } else {
                        out.print(HORIZONTAL_BORDER);
                    }
                } else if (col == 0 || col == world.width - 1) {
                    out.print(VERTICAL_BORDER);
                } else {
                    printCell(row, col, world);
                }
            }
            out.println();
        }
    }
}
