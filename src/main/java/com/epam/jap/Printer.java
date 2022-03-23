package com.epam.jap;

public class Printer {



    public void printWorld(World world) {
        for (int row = 0; row < world.height; row++) {
            for (int col = 0; col < world.width; col++) {
                if ((row == 0 || row == world.height - 1) || (col == 0 || col == world.width - 1)) {
                    System.out.print("*");
                } else {
                    if (world.cells[row][col]) {
                        System.out.print("X");
                    } else {
                        System.out.print("_");
                    }
                }
            }
            System.out.println();
        }
    }
}
