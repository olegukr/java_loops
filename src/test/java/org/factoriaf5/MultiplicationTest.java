package org.factoriaf5;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiplicationTest {

 private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testCheckToContinue_Yes() {
        String input = "\n";  // simulate input Enter instead "Y"
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        boolean result = Multiplication.checkToContinue(scanner);

        assertTrue(result);
    }

       @Test
    public void testCheckToContinue_No() {
        String input = "N\n";  // simulate input "N"
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        boolean result = Multiplication.checkToContinue(scanner);

        assertFalse(result);
    }

    @Test
    public void testCheckToContinue_InvalidThenYes() {
        String input = "maybe\nY\n";  // invalid input "maybe", after that "Y"
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        boolean result = Multiplication.checkToContinue(scanner);

        assertTrue(result);
        assertTrue(outputStreamCaptor.toString().contains("For Continue press \"Y\" or Enter, for Exit press \"N\""));
    }


   @Test
    public void testInputNumber_ValidInput() {
        String input = "5\n";  // simulate input 5
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        int result = Multiplication.inputNumber(scanner);

        assertEquals(5, result);
    }

    @Test
    public void testInputNumber_InvalidInputThenValid() {
        String input = "abc\n3\n";  //  simulate invalid input 
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        int result = Multiplication.inputNumber(scanner);

        assertEquals(3, result);
        assertTrue(outputStreamCaptor.toString().contains("Invalid input"));
    }

    @Test
    public void testInputNumber_OutOfRangeThenValid() {
        String input = "15\n7\n";  // Введення поза діапазоном, а потім правильне число 7
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        int result = Multiplication.inputNumber(scanner);

        assertEquals(7, result);
        assertTrue(outputStreamCaptor.toString().contains("The number must be in the range 1...10"));
    }



    @Test
    public void testMultiplyNumberBy1To10() {
        int number = 2;
        Multiplication.multiplyNumberBy1To10(number);

        String expectedOutput = "\nOutput:\n" +
                "2 x 1 = 2\n" +
                "2 x 2 = 4\n" +
                "2 x 3 = 6\n" +
                "2 x 4 = 8\n" +
                "2 x 5 = 10\n" +
                "2 x 6 = 12\n" +
                "2 x 7 = 14\n" +
                "2 x 8 = 16\n" +
                "2 x 9 = 18\n" +
                "2 x 10 = 20\n";

        assertTrue(outputStreamCaptor.toString().contains(expectedOutput.trim()));
    }

    @Test
    void testNumberMultiplication() {

    }
}
