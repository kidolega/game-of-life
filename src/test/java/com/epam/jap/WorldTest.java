package com.epam.jap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        world.currentGeneration = new Generation(AlIVE_0_F);
        world.pastGeneration = world.currentGeneration.clone();
        // when
        world.evolveWorld();
        // then
        assertNotSame(world.currentGeneration, world.pastGeneration);
    }

    @Test
    public void worldShouldNotChange() {
        // given
        world.currentGeneration = new Generation(DEAD_0_F);
        world.pastGeneration = world.currentGeneration.clone();
        // when
        world.evolveWorld();
        // then
        assertEquals(world.currentGeneration, world.pastGeneration);
    }

    @Test
    public void shouldPassIfCreatedWorldIsNotEmpty() {
        // given
        World world = new World(3, 3);
        // when
        world.initializeWorld();
        // then
        Assert.assertNotNull(world.currentGeneration.currentCells);
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
