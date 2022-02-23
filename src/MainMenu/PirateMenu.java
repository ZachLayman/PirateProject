package mainmenu;

import static com.sun.javafx.scene.control.skin.Utils.getResource;
import java.io.File;
import java.io.FileInputStream;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PirateMenu extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Font.loadFont(getClass().getResourceAsStream("Ponderosa.ttf"), 20);
        Parent root = FXMLLoader.load(getClass().
            getResource("mainMenuFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        scene.getStylesheets().add(getClass().
            getResource("MainMenuFXML.css").toExternalForm());
        
        stage.setScene(scene);
        stage.show();
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}