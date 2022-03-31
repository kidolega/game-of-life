package com.epam.jap;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

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

    int[] setWorldDimensions(Scanner scanner) {
        return new int[]{
        getSideSizeFromUser("width", scanner),
        getSideSizeFromUser("height", scanner)
        };
    }
}
