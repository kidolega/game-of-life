package com.epam.jap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static java.lang.System.out;

public class GameTest {

    private final World world = new World(new int[]{3,3});
    private final Printer printer = new Printer(out, world);
    private final Game game = new Game(world, printer);
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

//    @BeforeMethod
//    public void setUp() {
//        world = new World(0, 0);
//    }


//    @Test
//    public void shouldPrintGame() {
//        // given
//        printer = new Printer(new PrintStream(outContent), world);
//        // when
//        game.play();
//        // then
//        Assert.assertNotNull(outContent.toString());
//    }

//    @Test
//    public void worldShouldBeOfGivenSize() {
//        // given
//        String length = "20";
//        game = new Game(world, printer);
////        game.setWorldDimensions();
//        // when
//        game.setWorldDimensions(new Scanner(length));
//        game.world().initializeWorld();
//        // then
//        Assert.assertEquals(world.generation.currentCells.length, 20);
//    }
}

