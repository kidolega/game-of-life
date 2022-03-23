package com.epam.jap;

public class Printer {



    public void printWorld(World world) {
        for (int i = 0; i < world.height; i++) {
            for (int j = 0; j < world.width; j++) {
                if (world.cells[i][j]) {
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
