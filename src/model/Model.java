package model;

import classes.ColorCode;
import classes.ModularCounter;

import java.util.Scanner;

public class Model {
    //Variables
    private static ModularCounter red = new ModularCounter(256);
    private static ModularCounter green = new ModularCounter(256);
    private static ModularCounter blue = new ModularCounter(256);
    private static Scanner sc = new Scanner(System.in);

    //Constructor
    Model() {

    }

    //Methods
    public static void main(String[] args) {
        String selection;

        do {
            //prints out the menu
            printMenu();
            selection = sc.nextLine();

            //checks selection
            switch (selection) {
                case "a":
                    printAbsolute();
                    break;

                case "r":

                    break;

                case "?":
                    printCurrentValues();
                    break;

                case "q":
                    System.exit(-1);
                    break;

                default:
                    System.out.println("Error. Please enter a value from above.");
            }
        } while (true);
    }

    public static void changeColorViaAbsoluteValue(ColorCode cc, String value) {
        try {
            int v = Integer.parseInt(value);
            changeColorViaAbsoluteValue(cc, v);
        } catch(Exception ex) {
            System.out.println("Invalid code value!");
        }
    }

    public static void changeColorViaAbsoluteValue(ColorCode cc, int value) {
        if (cc == ColorCode.RED) {
            red.reset();
            red.inc(value);
        } else if (cc == ColorCode.GREEN) {
            green.reset();
            green.inc(value);
        } else {
            blue.reset();
            blue.inc(value);
        }
    }

    public static void changeColorViaRelativeValue(ColorCode cc, String value) {
        try {
            int v = Integer.parseInt(value);
            changeColorViaRelativeValue(cc, v);
        } catch (Exception ex) {
            System.out.println("Invalid code value!");
        }
    }

    public static void changeColorViaRelativeValue(ColorCode cc, int value) {
        if (cc == ColorCode.RED && red.getValue()+value >= 0 && red.getValue()+value <= 255) {
            red = new ModularCounter(red.getValue()+value, 256);
        } else if (cc == ColorCode.GREEN && green.getValue()+value >= 0 && green.getValue() <= 255) {
            green = new ModularCounter(green.getValue()+value, 256);
        } else {
            if (blue.getValue()+value >= 0 && blue.getValue()+value <= 255) {
                blue = new ModularCounter(blue.getValue()+value, 256);
            }
        }
    }

    public static int getRed() {
        return red.getValue();
    }

    public static int getGreen() {
        return green.getValue();
    }

    public static int getBlue() {
        return blue.getValue();
    }

    public static int getHex() {
        String hexValue = "";

        //red value
        if (red.getValue() >= 0 && red.getValue() <= 9) {
            hexValue += "0" + Integer.toHexString(red.getValue());
        } else {
            hexValue += Integer.toHexString(red.getValue());
        }

        //green value
        if (green.getValue() >= 0 && green.getValue() <= 9) {
            hexValue += "0" + Integer.toHexString(green.getValue());
        } else {
            hexValue += Integer.toHexString(green.getValue());
        }

        //blue value
        if (blue.getValue() >= 0 && blue.getValue() <= 9)
            hexValue += "0" + Integer.toHexString(blue.getValue());
        else {
            hexValue += Integer.toHexString(blue.getValue());
        }

        return Integer.parseInt(hexValue);
    }

    public static String modelToString() {
        return "State: [" +
                "red=" + red.getValue() +
                ", green=" + green.getValue() +
                ", blue=" + blue.getValue() +
                ']';
    }

    //Additional methods for psvm
    public static void printMenu() {
        System.out.println("\n-------------------- Menu: ----------------------\n" +
                           "\ta - changeColorViaAbsoluteValue \n" +
                           "\tr - changeColorViaRelativeValue \n" +
                           "\t? - view all accessors \n" +
                           "\tq - quit" +
                           "--------------------------------------------------\n\n" +
                           "Enter a command");
    }

    public static void printCurrentValues() {
        System.out.println("getRed \t = " + getRed() + "\n" +
                           "getGreen = " + getGreen() + "\n" +
                           "getBlue  = " + getBlue() + "\n" +
                           "getHex \t = #" + getHex());
    }

    public static void printAbsolute() {
        try {
            String color;
            int value;

            System.out.println("Enter 3 numbers between 0 and 255 \n");
            System.out.print("Red: ");
            processInput(ColorCode.RED);

            System.out.print("Green: ");
            processInput(ColorCode.GREEN);

            System.out.print("Blue: ");
            processInput(ColorCode.BLUE);

            System.out.println(modelToString());
        } catch (Exception ex) {

        }
    }

    public static void processInput(ColorCode cc) {
        int i;

        try {
            do {
                i = sc.nextInt();
            } while (i < 0 || i > 255);

            changeColorViaAbsoluteValue(cc, i);
        } catch (Exception ex) {
            System.out.println("Error. Please enter a value from above.");

            //sets the color on a default value
            i = 0;
            changeColorViaAbsoluteValue(cc, i);
        }
    }
}
