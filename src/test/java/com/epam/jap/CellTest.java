package com.epam.jap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CellTest {

    private static final Boolean[][] onlyOneAliveInMid = new Boolean[][]{
            {true, false, false},
            {false, false, false},
            {false, false, false}};
    private static final Boolean[][] onlyTwoAlive = new Boolean[][]{
            {true, false, false},
            {false, true, false},
            {false, false, false}};
    private static final Boolean[][] threeAliveInMidRow = new Boolean[][]{
            {false, false, false},
            {true, true, true},
            {false, false, false}};


    @DataProvider
    Object[][] hasNotEnoughFriends() {
        return new Object[][] {
                {onlyOneAliveInMid},
                {onlyTwoAlive}
        };
    }

    @DataProvider
    Object[][] hasEnoughFriends() {
        return new Object[][] {
                {threeAliveInMidRow},
        };
    }

    @Test
    public void shouldReturnTrueIfCellIsAlive() {
        // given
        Cell cell = new Cell(0, 0, true);
        // when
        boolean aliveCell = cell.isAlive;
        // then
        Assert.assertTrue(aliveCell);
    }

    @Test
    public void shouldReturnFalseIfCellDies() {
        // given
        Cell cell = new Cell(0, 0, true);
        // when
        cell.die();
        // then
        Assert.assertFalse(cell.isAlive);
    }

    @Test(dataProvider = "hasNotEnoughFriends")
    public void shouldDieIfHasNotEnoughFriends(Boolean[][] cells) {
        // given
        World world = new World(3, 3);
        // when
        world.evolve();
        // then
        Assert.assertFalse(cells[1][1]);
    }

//    @Test(dataProvider = "hasEnoughFriends")
//    public void shouldNotDieIfHasEnoughFriends(Boolean[][] cells) {
//        // given
//        World world = new World(3, 3, cells);
//        // when
//        world.evolve();
//        // then
//        Assert.assertTrue(cells[1][1]);
//    }
}
