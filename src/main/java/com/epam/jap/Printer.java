package com.epam.jap;

public class Printer {


    public void printWorld(World world) {
        for (int row = 0; row < world.height; row++) {
            for (int col = 0; col < world.width; col++) {
                if (row == 0 || row == world.height - 1) {
                    if (row == 0 && col == 0) {
                        System.out.print("\u250F");
                    } else if (row == 0 && col == world.width - 1) {
                        System.out.print("\u2513");
                    } else if (row == world.height - 1 && col == 0) {
                        System.out.print("\u2517");
                    } else if (row == world.height - 1 && col == world.width - 1) {
                        System.out.println("\u251B");
                    } else {
                        System.out.print("\u2501");
                    }
                } else if (col == 0 || col == world.width - 1) {
                    System.out.print("\u2503");
                } else if (world.cells[row][col]) {
                    System.out.print("\u25CF");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
