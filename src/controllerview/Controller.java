package controllerview;

import classes.ColorCode;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.Model;

public class Controller {
    //Variables
    @FXML Button btn_colorView = new Button();
    @FXML TextField txtf_hexValue = new TextField();
    @FXML TextField txtf_rgbValueRed = new TextField();
    @FXML TextField txtf_rgbValueGreen = new TextField();
    @FXML TextField txtf_rgbValueBlue = new TextField();


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
            txtf_rgbValueRed.setText("" + Model.getRed());
        } else if (btnID.contains("green")) {
            Model.changeColorViaRelativeValue(ColorCode.GREEN, op);
            txtf_rgbValueGreen.setText("" + Model.getGreen());
        } else {
            Model.changeColorViaRelativeValue(ColorCode.BLUE, op);
            txtf_rgbValueBlue.setText("" + Model.getBlue());
        }

        //changes color of the button
        changeColorView(Model.getHex());
    }


    //============================================= other methods ====================================================//
    public void changeColorView(String hexCode) {
        //changes button color
        btn_colorView.setStyle("-fx-background-color: #" + hexCode + ";");

        //updates hexcode in textfield
        txtf_hexValue.setText("#" + hexCode);
    }
}
