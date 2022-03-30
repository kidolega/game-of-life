package com.epam.jap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.lang.System.out;

public class GameTest {

    private World world;
    private Printer printer;
    private Game game;
    static ByteArrayOutputStream outContent;

    @BeforeMethod
    public void setUp() {
        world = new World(0, 0);
        printer = new Printer(out);
        game = new Game(world, printer);
        outContent = new ByteArrayOutputStream();
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

    @Test
    public void worldShouldBeOfGivenSize() {
        // given
        game.getSideSizeFromUser("width");
        // when
        // then
    }
}
