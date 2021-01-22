package model;

import classes.ColorCode;
import classes.ModularCounter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Model {
    //=============================================== Variables ======================================================//
    private static ModularCounter red = new ModularCounter(256);
    private static ModularCounter green = new ModularCounter(256);
    private static ModularCounter blue = new ModularCounter(256);
    private static Scanner sc = new Scanner(System.in); //Scanner

    //============================================= color changing ===================================================//
    public static void changeColorViaAbsoluteValue(ColorCode cc, int value) {
        if (value >= 0 && value <= 255) {
            switch (cc) {
                case RED:
                    red.reset(); //resets the value to zero
                    red.inc(value); //red = 0 + value
                    break;

                case GREEN:
                    green.reset();
                    green.inc(value);
                    break;

                case BLUE:
                    blue.reset();
                    blue.inc(value);
            }
        }
    }

    public static void changeColorViaRelativeValue(ColorCode cc, String operator) {
        if (operator.contains("+")) {
            if (cc == ColorCode.RED && red.getValue()+10 < red.getModulus()) {
                red.inc(10);
            } else if (cc == ColorCode.GREEN && green.getValue()+10 < green.getModulus()) {
                green.inc(10);
            } else {
                if (blue.getValue()+10 < blue.getModulus())
                    blue.inc(10);
            }
        }
        else if (operator.contains("-")){
            if (cc == ColorCode.RED && red.getValue()-10 >= 0) {
                red.dec(10);
            } else if (cc == ColorCode.GREEN && green.getValue()-10 >= 0) {
                green.dec(10);
            } else {
                if (blue.getValue()-10 >= 0)
                    blue.dec(10);
            }
        }
        else {
            //do nothing
        }
    }

    //============================================== getting values ==================================================//
    public static int getRed() {
        return red.getValue();
    }

    public static int getGreen() {
        return green.getValue();
    }

    public static int getBlue() {
        return blue.getValue();
    }

    public static String getHex() {
        String hexValue = "";

        //red value
        String rV = Integer.toHexString(red.getValue());
        if (rV.length() == 1)
            hexValue += "0" + rV;
        else
            hexValue += rV;

        //green value
        String gV = Integer.toHexString(green.getValue());
        if (gV.length() == 1)
            hexValue += "0" + gV;
        else
             hexValue += gV;

        //blue value
        String bV = Integer.toHexString(blue.getValue());
        if (bV.length() == 1)
            hexValue += "0" + bV;
        else
            hexValue += bV;

        return hexValue;
    }

    //============================================== other methods ===================================================//
    Model() {

    }

    public static void main(String[] args) {
        String selection;
        boolean correctInput; // TODO: 22.01.2021  new

        do {
            //prints out the menu
            printMenu();

            //gets the input (selection) from user
            selection = sc.next();

            //checks selection
            switch (selection) {
                case "a":
                    do {
                        try {
                            System.out.print("Type the value for red (Range 0-255)? To cancel, type -1: ");
                            int value = sc.nextInt();
                            changeColorViaAbsoluteValue(ColorCode.RED, value);

                            System.out.print("Type the value for green (Range 0-255)? To cancel, type -1: ");
                            value = sc.nextInt();
                            changeColorViaAbsoluteValue(ColorCode.GREEN, value);

                            System.out.print("Type the value for blue (Range 0-255)? To cancel, type -1: ");
                            value = sc.nextInt();
                            changeColorViaAbsoluteValue(ColorCode.BLUE, value);

                            System.out.println(modelToString());

                            //checks if correct input
                            correctInput = true;
                        } catch (Exception ex) {
                            changeColorViaAbsoluteValue(ColorCode.RED, -1);

                            correctInput = false;
                            sc.nextLine();  //clears scanner
                        }
                    } while (!correctInput);
                    break;

                case "r":
                    System.out.print("Do you want to increment or decrement red [+ | -]? To cancel, type anything else: ");
                    String op = sc.next();
                    changeColorViaRelativeValue(ColorCode.RED, op);

                    System.out.print("Do you want to increment or decrement green [+ | -]? To cancel, type anything else: ");
                    op = sc.next();
                    changeColorViaRelativeValue(ColorCode.GREEN, op);

                    System.out.print("Do you want to increment or decrement blue [+ | -]? To cancel, type anything else: ");
                    op = sc.next();
                    changeColorViaRelativeValue(ColorCode.BLUE, op);

                    System.out.println(modelToString());
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

    public static String modelToString() {
        return "State: [" +
                "red=" + red.getValue() +
                ", green=" + green.getValue() +
                ", blue=" + blue.getValue() +
                ']';
    }

    //========================================= additional methods for psvm ==========================================//
    public static void printMenu() {
        System.out.print("\n\n-------------------- Menu: ----------------------\n" +
                           "\ta - changeColorViaAbsoluteValue \n" +
                           "\tr - changeColorViaRelativeValue \n" +
                           "\t? - view all accessors \n" +
                           "\tq - quit\n" +
                           "-------------------------------------------------\n" +
                           "Enter a command: ");
    }

    public static void printCurrentValues() {
        System.out.println("\tgetRed \t = " + getRed() + "\n" +
                           "\tgetGreen = " + getGreen() + "\n" +
                           "\tgetBlue  = " + getBlue() + "\n" +
                           "\tgetHex \t = #" + getHex());
    }
}
