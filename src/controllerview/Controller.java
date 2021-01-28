package controllerview;

import classes.ColorCode;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.Model;

import java.io.*;

public class Controller {
    //Variables
    @FXML Button btn_colorView = new Button();
    @FXML TextField txtf_hexValue = new TextField();
    @FXML TextField txtf_rgbValueRed = new TextField();
    @FXML TextField txtf_rgbValueGreen = new TextField();
    @FXML TextField txtf_rgbValueBlue = new TextField();

    final String KEY = "Color File Format 1.0";
    final String FILENAME = "color.dat";


    //=============================================== handlers =======================================================//
    public void hdl_changeAbsolute(KeyEvent keyEvent) {
        String hexCode = Model.getHex();

        //gets the selected textfield
        TextField txtf_selected = (TextField) keyEvent.getSource();

        //checks if the input is a number (ascii code - between 0 and 9)
        if (keyEvent.getCode().getCode() >= 48 && keyEvent.getCode().getCode() <= 57) {
            //gets the input value
            int value = Integer.parseInt(txtf_selected.getText());

            if (txtf_selected.getId().contains("Red")) {
                Model.changeColorViaAbsoluteValue(ColorCode.RED, value);
            } else if (txtf_selected.getId().contains("Green")) {
                Model.changeColorViaAbsoluteValue(ColorCode.GREEN, value);
            } else {
                Model.changeColorViaAbsoluteValue(ColorCode.BLUE, value);
            }

            //gets the hex code
            hexCode = Model.getHex();

            //changes color of the button
            changeColorView(hexCode);
        }
    }

    public void hdl_changeRelative(Event event) {
        //gets the selected button
        Button btn_selected = (Button) event.getSource();
        //checks if inc or dec
        String op = btn_selected.getText();
        //checks which color should be increased / decreased
        String btnID = btn_selected.getId();

        //changes the respective color value
        if (btnID.contains("red")) {
            Model.changeColorViaRelativeValue(ColorCode.RED, op);

        } else if (btnID.contains("green")) {
            Model.changeColorViaRelativeValue(ColorCode.GREEN, op);

        } else {
            Model.changeColorViaRelativeValue(ColorCode.BLUE, op);
        }
        setTxtF();

        //changes color of the button
        changeColorView(Model.getHex());
    }


    //======================================= load and save into files ===============================================//
    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            if (reader.readLine().equals(KEY)) {
                Model.changeColorViaAbsoluteValue(ColorCode.RED, Integer.parseInt(reader.readLine()));
                Model.changeColorViaAbsoluteValue(ColorCode.GREEN, Integer.parseInt(reader.readLine()));
                Model.changeColorViaAbsoluteValue(ColorCode.BLUE, Integer.parseInt(reader.readLine()));

                changeColorView(Model.getHex());
                setTxtF();
            } else {
                System.out.println("Error: wrong file!");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            //clears content
            writer.flush();
            writer.write("Color File Format 1.0");
            writer.newLine();   //linebreak
            writer.write("" + Model.getRed());
            writer.newLine();
            writer.write("" + Model.getGreen());
            writer.newLine();
            writer.write("" + Model.getBlue());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void setTxtF(){
        txtf_rgbValueRed.setText("" + Model.getRed());
        txtf_rgbValueGreen.setText("" + Model.getGreen());
        txtf_rgbValueBlue.setText("" + Model.getBlue());
    }


    //============================================= other methods ====================================================//
    public void changeColorView(String hexCode) {
        //changes button color
        btn_colorView.setStyle("-fx-background-color: #" + hexCode + ";");

        //updates hexcode in textfield
        txtf_hexValue.setText("#" + hexCode);
    }
}
