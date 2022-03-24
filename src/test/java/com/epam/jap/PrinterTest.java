package com.epam.jap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PrinterTest {

    Printer printer = new Printer(System.out);
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeAll
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

//    @AfterAll
//    public void restoreStreams()  {
//        System.setOut(originalOut);
//        System.setErr(errContent);
//    }

    public void testPrintCell() {
    }

//    public void testPrintHorizontalBorder() {
//        // given
//        originalOut.print("\u2501");
//        // when
//        printer.printHorizontalBorder();
//        printer(horizontal)
//        // then
//        Assert.assertEquals(originalOut.print("\u2501"), originalOut);
//    }

    public void testPrintVerticalBorder() {
    }

    public void testPrintTopLeftCorner() {
    }

    public void testPrintTopRightCorner() {
    }

    public void testPrintBotLeftCorner() {
    }

    public void testPrintBotRightCorner() {
    }

    public void testPrintWorld() {
    }
}
