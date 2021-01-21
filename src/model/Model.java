package model;

import classes.ColorCode;
import classes.ModularCounter;

public class Model {
    //Variables
    private ModularCounter red = new ModularCounter();
    private ModularCounter green = new ModularCounter();
    private ModularCounter blue = new ModularCounter();

    //Constructor
    Model() {

    }

    //Methods
    public static void main(String[] args) {

    }

    public  void changeColorViaAbsoluteValue(ColorCode cc, String value) {

    }

    public void changeColorViaAbsoluteValue(ColorCode cc, int value) {

    }

    public void changeColorViaRelativeValue(ColorCode cc, String value) {

    }

    public void changeColorViaRelativeValue(ColorCode cc, int value) {

    }

    public int getRed() {

    }

    public int getGreen() {

    }

    public int getBlue() {

    }

    public int getHex() {

    }

    @Override
    public String toString() {
        return "Model{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }
}
