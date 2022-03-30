package com.epam.jap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static java.lang.System.out;

public class GameTest {

    private World world;
    private Printer printer = new Printer(out);
    private Game game;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent;

    @BeforeMethod
    public void setUp() {
        world = new World(0, 0);
        game = new Game(world, printer, new Scanner(inContent));
    }


    @Test
    public void shouldPrintGame() {
        // given
        printer = new Printer(new PrintStream(outContent));
        // when
        game.play();
        // then
        Assert.assertNotNull(outContent.toString());
    }

//    @Test
//    public void worldShouldBeOfGivenSize() {
//        // given
//        inContent = new ByteArrayInputStream("20".getBytes());
//        game.getSideSizeFromUser("width");
//        game.getSideSizeFromUser("height");
//        // when
//        Assert.assertEquals(world.generation.currentCells.length, 20);
//    }
}

