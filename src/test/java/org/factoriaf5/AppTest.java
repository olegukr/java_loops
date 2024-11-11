package org.factoriaf5;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
class AppTest {

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
    public void testAppWithValidInputAndContinue() {
        String input = "5\nY\n3\nN\n"; // Введення чисел 5 і 3, після першого запиту продовжуємо, після другого — завершуємо
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        App.main(new String[]{});

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Input number from 1 to 10"));
        assertTrue(output.contains("5 x 1 = 5"));
        assertTrue(output.contains("3 x 10 = 30"));
        assertTrue(output.contains("Do you want to continue? (Y/N):"));
    }

    @Test
    public void testAppWithInvalidInputAndValidExit() {
        String input = "abc\n12\n7\nN\n"; // Некоректне введення, потім поза діапазоном, далі число 7 і вихід
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        App.main(new String[]{});

        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Invalid input. Please enter a number between 1 and 10."));
        assertTrue(output.contains("The number must be in the range 1...10"));
        assertTrue(output.contains("7 x 10 = 70"));
        assertTrue(output.contains("Do you want to continue? (Y/N):"));
    }
}