package com.epam.jap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CellTest {

    private Cell cell;

    @BeforeMethod
    private void setUp() {
        this.cell = new Cell();
    }

    @Test
    public void returnsTrueIfAlive() {
        // given
        // when
        cell.revive();
        // then
        Assert.assertTrue(cell.state);
    }

    @Test
    public void returnsTrueIfDead() {
        // given
        // when
        cell.kill();
        // then
        Assert.assertFalse(cell.state);
    }
}
