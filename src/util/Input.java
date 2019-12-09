package util;

import java.util.Scanner;

public class Input {
    public Scanner scanner = new Scanner(System.in);

    public String getString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public String getString() {
        System.out.println("Write a string");
        return scanner.nextLine();
    }

    public boolean yesNo(String prompt) {
        System.out.println(prompt);
        String test = scanner.next();
        return test.equalsIgnoreCase("y") || test.equalsIgnoreCase("yes") || test.equalsIgnoreCase("true");
    }

    public boolean yesNo() {
        System.out.println("Enter yes or no:");
        String test = scanner.next();
        return test.equalsIgnoreCase("y") || test.equalsIgnoreCase("yes") || test.equalsIgnoreCase("true");
    }

    public int getInt(String prompt, int min, int max) {
        System.out.println(prompt);
        int input = scanner.nextInt();
        if (input < min || input > max) {
            return getInt(prompt, min, max);
        } else {
            return input;
        }
    }

    public int getInt(int min, int max) {
        System.out.printf("Enter a number between %s and %s %n", min, max);
        int input = scanner.nextInt();
        if (input < min || input > max) {
            return getInt(min, max);
        } else {
            return input;
        }
    }

    public int getInt(String prompt) {
        System.out.println(prompt);
        return scanner.nextInt();
    }

    public int getInt() {
        System.out.println("enter a integer:");
//        return scanner.nextInt();
        try{
            return Integer.parseInt(getString());
        } catch (NumberFormatException nfe){
            System.out.println("Error: Not a integer input... ");
            return getInt();
        }
    }

    public double getDouble(String prompt, double min, double max) {
        System.out.println(prompt);
        double input = scanner.nextDouble();
        if (input < min || input > max) {
            return getDouble(min, max);
        } else {
            return input;
        }
    }

    public double getDouble(double min, double max) {
        System.out.printf("Enter a number between %s and %s %n", min, max);
        double input = scanner.nextDouble();
        if (input < min || input > max) {
            return getDouble(min, max);
        } else {
            return input;
        }
    }

    public double getDouble(String prompt) {
        System.out.println(prompt);
        return scanner.nextDouble();
    }

    public double getDouble() {
        System.out.println("Enter a double");
//        return scanner.nextDouble();
        try{
            return Double.parseDouble(getString());
        } catch (NumberFormatException nfe){
            System.out.println("Error: Not a decimal input...");
            return getDouble();
        }
    }

    public void cleanLines() {
        scanner.nextLine();
    }
}
