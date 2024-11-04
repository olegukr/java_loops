package org.factoriaf5;

import java.util.Scanner;

public final class App {
    private App() {
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input number from 1 to 10: ");
        int numberIn = scanner.nextInt();

        System.out.println("\ndado n = " + numberIn);
        System.out.println("\nOutput:");
        for (int i = 1; i < 11; i++) {
            int result = numberIn * i;
            System.out.println(numberIn + " x " + i + " = " + result);

        }
        scanner.close();
    }
}
