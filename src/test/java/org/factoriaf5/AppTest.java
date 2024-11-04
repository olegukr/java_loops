package org.factoriaf5;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
class AppTest {

    @Test
    public void testMainOutput() {
        // 1. Simulate user input (e.g., the number 5)
        String input = "5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // 2. Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // 3. Execute the main method
        App.main(new String[0]);


        String expectedOutput = """
                                Input number from 1 to 10: \ndado n = 5
                                
                                Output:
                                5 x 1 = 5
                                5 x 2 = 10
                                5 x 3 = 15
                                5 x 4 = 20
                                5 x 5 = 25
                                5 x 6 = 30
                                5 x 7 = 35
                                5 x 8 = 40
                                5 x 9 = 45
                                5 x 10 = 50
                                """;

        // Compare the actual output to the expected output
        assertEquals(expectedOutput, outputStream.toString());

        // Restore System.in and System.out to their default values
        System.setIn(System.in);
        System.setOut(System.out);
    }

}
/***
 
1. Simulating User Input

How It Works:
String input = "5\n"; – This line creates a string
variable input that simulates entering the number 5,
followed by a newline character (\n). This simulates
the user typing 5 and then pressing Enter.

System.setIn(new ByteArrayInputStream(input.getBytes())); –
Here, the standard input stream System.in is redirected
to a new ByteArrayInputStream containing the input string.
This allows the test to "provide" input without real user
interaction.

input.getBytes() converts the input string into a byte
array, which ByteArrayInputStream can read as input data.

When App.main() calls Scanner scanner = new Scanner(System.in);
and scanner.nextInt(), it will "see" this simulated input
5 as though it was entered by the user.

In short, this step enables the test to simulate user input
without requiring actual keyboard input.


2. Capturing Console Output

How It Works:
ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
– This line creates a ByteArrayOutputStream, which acts
as a temporary storage area for anything that would
normally be printed to the console.

System.setOut(new PrintStream(outputStream)); – Here,
the standard output stream System.out is redirected to
a new PrintStream that writes to outputStream instead
of the console.

This means that when App.main() runs and calls System.out.println()
to print to the console, all output will instead be written
to outputStream.

By redirecting System.out, we can capture all printed output
from the main method, storing it in outputStream as a
string. This allows the test to later verify that the actual
output matches the expected output.


3. Executing the main Method

How It Works:
App.main(new String[0]); – This line calls the main
method in the App class. In Java, main is the entry
point that runs when the program starts as a standalone
application.

new String[0] – An empty String array is passed as
the argument to main since it has the signature
public static void main(String[] args). In this test, we’re
not using arguments, so we provide an empty array.

During the execution of main, the program will interact
with the prepared System.in and System.out streams that
we redirected in previous steps:

System.in has been set to the simulated input of 5,
so scanner.nextInt() will read 5.

System.out has been redirected to a ByteArrayOutputStream,
allowing us to capture the output so we can later compare
it with the expected result in step 5.

***/