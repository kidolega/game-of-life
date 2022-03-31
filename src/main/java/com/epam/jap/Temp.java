package com.epam.jap;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Temp {

    public static void main(String[] args) {

        int[][] ints = new int[][] {
                {1,2,3,4},
                {5,6,7,8}
        };
        int[] oneDirInts = new int[] {
                0, 1, 2, 3
        };

        Arrays.stream(oneDirInts).forEach(System.out::print);
        System.out.println();
        Arrays.stream(ints).flatMapToInt(Arrays::stream).forEach(x -> x = 3);
        Arrays.stream(ints).flatMapToInt(Arrays::stream).forEach(System.out::print);
//        IntStream stream = Arrays.stream(arr).flatMapToInt(x -> Arrays.stream(x));
    }


}
