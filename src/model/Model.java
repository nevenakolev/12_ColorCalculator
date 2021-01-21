package model;

import classes.ColorCode;
import classes.ModularCounter;

public class Model {
    //Variables
    private ModularCounter red = new ModularCounter(256);
    private ModularCounter green = new ModularCounter(256);
    private ModularCounter blue = new ModularCounter(256);

    //Constructor
    Model() {

    }

    //Methods
    public static void main(String[] args) {

    }

    public void changeColorViaAbsoluteValue(ColorCode cc, String value) {
        try {
            int v = Integer.parseInt(value);
            changeColorViaAbsoluteValue(cc, v);
        } catch(Exception ex) {
            System.out.println("Invalid code value!");
        }
    }

    public void changeColorViaAbsoluteValue(ColorCode cc, int value) {
        if (value > 0 && value <= 255) {
            if (cc == ColorCode.RED)
                this.red = new ModularCounter(value, 256);
            else if (cc == ColorCode.GREEN)
                this.green = new ModularCounter(value, 256);
            else
                this.blue = new ModularCounter(value, 256);
        } else {
            System.out.println("Value out of range! (0-255)");
        }
    }

    public void changeColorViaRelativeValue(ColorCode cc, String value) {
        try {
            int v = Integer.parseInt(value);
            changeColorViaRelativeValue(cc, v);
        } catch (Exception ex) {
            System.out.println("Invalid code value!");
        }
    }

    public void changeColorViaRelativeValue(ColorCode cc, int value) {
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

    public int getRed() {
        return red.getValue();
    }

    public int getGreen() {
        return green.getValue();
    }

    public int getBlue() {
        return blue.getValue();
    }

    public int getHex() {
        StringBuilder sb = new StringBuilder();
        sb.append("#");

        //red value
        if (red.getValue() >= 0 && red.getValue() <= 9) {
            sb.append("0" + Integer.toHexString(red.getValue()));
        } else {
            sb.append(Integer.toHexString(red.getValue()));
        }

        //green value
        if (green.getValue() >= 0 && green.getValue() <= 9) {
            sb.append("0" + Integer.toHexString(green.getValue()));
        } else {
            sb.append(Integer.toHexString(green.getValue()));
        }

        //blue value
        if (blue.getValue() >= 0 && blue.getValue() <= 9)
            sb.append("0" + Integer.toHexString(blue.getValue()));
        else {
            sb.append(Integer.toHexString(blue.getValue()));
        }

        return Integer.parseInt(sb.toString());
    }

    @Override public String toString() {
        return "State: [" +
                "red=" + red.getValue() +
                ", green=" + green.getValue() +
                ", blue=" + blue.getValue() +
                ']';
    }
}
