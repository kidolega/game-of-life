package com.epam.jap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static com.epam.jap.PrinterTest.world;
import static org.testng.AssertJUnit.*;

public class WorldTest {

    @BeforeMethod
    public void setUp() {
        world = new World(3, 3);
    }

    @Test
    public void worldShouldChange() {
        // given
        world.generation = new Generation(AlIVE_0_F);
//        world.pastGeneration = world.currentGeneration.clone();
        // when
        world.evolveWorld();
        // then
        assertNotSame(world.generation.evolvedCells, world.generation.originalCells);
    }

    @Test
    public void worldShouldNotChange() {
        // given
        world.generation = new Generation(DEAD_0_F);
//        world.pastGeneration = world.currentGeneration.clone();
        // when
        world.evolveWorld();
        // then
        assertTrue(Arrays.deepEquals(world.generation.evolvedCells, world.generation.originalCells));
    }

    @Test
    public void shouldPassIfCreatedWorldIsNotEmpty() {
        // given
        World world = new World(3, 3);
        // when
        world.initializeWorld();
        // then
        Assert.assertNotNull(world.generation.evolvedCells);
    }

    private static final Boolean[][] DEAD_0_F = new Boolean[][]{
            {false, false, false},
            {false, false, false},
            {false, false, false}
    };

    private static final Boolean[][] AlIVE_0_F = new Boolean[][]{
            {false, false, false},
            {false, true, false},
            {false, false, false}
    };
}
