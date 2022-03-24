package com.epam.jap;

import org.testng.annotations.Test;

public class GameTest {

    @Test
    public void shouldPassIfCreatedCellsCopyEqualsCells() {
        // given
        Game game = new Game();
        World world = new World();
        // when
        world.initializeWorld(5, 5);
    }
}
