package controllerview;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    //Variables
    //Buttons
    @FXML Button btn_redInc = new Button();
    @FXML Button btn_redDec = new Button();
    @FXML Button btn_greenInc = new Button();
    @FXML Button btn_greenDec = new Button();
    @FXML Button btn_blueInc = new Button();
    @FXML Button btn_blueDec = new Button();
    @FXML Button btn_colorView = new Button();
    //Textfields
    @FXML TextField txtf_rgbValueRed = new TextField();
    @FXML TextField txtf_rgbValueGreen = new TextField();
    @FXML TextField txtf_rgbValueBlue = new TextField();
    @FXML TextField txtf_hexValue = new TextField();


}
