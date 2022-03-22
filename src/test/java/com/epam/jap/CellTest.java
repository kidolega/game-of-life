package com.epam.jap;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CellTest {

    @Test
    public void shouldReturnTrueIfCellIsAlive() {
        // given
        Cell cell = new Cell();
        // when
        // then
        Assert.assertTrue(cell.isAlive);
    }

    @Test
    public void shouldReturnFalseIfCellDies() {
        // given
        Cell cell = new Cell();
        // when
        cell.die();
        // then
        Assert.assertFalse(cell.isAlive);
    }

    @Test
    public void shouldDieIfHasNoFriends() {
        // given

        // when
        // then
    }
}
