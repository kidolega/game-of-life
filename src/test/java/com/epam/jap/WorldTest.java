package com.epam.jap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static com.epam.jap.PrinterTest.world;
import static org.testng.AssertJUnit.*;

public class WorldTest {

    static final Cell a = new Cell(true);
    static final Cell d = new Cell(false);

    @BeforeMethod
    public void setUp() {
        world = new World(new int[]{3,3});
    }

    @Test
    public void worldShouldChange() {
        // given
        world.population = new Population(AlIVE_0_F);
//        world.pastGeneration = world.currentGeneration.clone();
        // when
        world.evolveWorld();
        // then
        assertNotSame(world.population.evolvedGeneration, world.population.originalGeneration);
    }

    @Test
    public void worldShouldNotChange() {
        // given
        world.population = new Population(DEAD_0_F);
//        world.pastGeneration = world.currentGeneration.clone();
        // when
        world.evolveWorld();
        // then
        assertTrue(Arrays.deepEquals(world.population.evolvedGeneration, world.population.originalGeneration));
    }

//    @Test
//    public void shouldPassIfCreatedWorldIsNotEmpty() {
//        // given
//        World world = new World(3, 3);
//        // when
//        world.initializeWorld();
//        // then
//        Assert.assertNotNull(world.generation.evolvedCells);
//    }

    private static final Cell[][] DEAD_0_F = new Cell[][]{
            {d, d, d},
            {d, d, d},
            {d, d, d}
    };

    private static final Cell[][] AlIVE_0_F = new Cell[][]{
            {d, d, d},
            {d, a, d},
            {d, d, d}
    };
}
