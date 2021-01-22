import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**************************
 * @author Nevena Kolev
 * @version 1.0, 21-01-21
 **************************/
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("controllerview/sample.fxml"));
        primaryStage.setTitle("12 - Color Calculator");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        //launches the JavaFX Application
        launch(args);
    }
}
