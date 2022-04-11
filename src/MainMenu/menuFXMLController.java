package MainMenu;
import GameFunctions.Main;
import CustomPlayer.CustomCharacter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author domin
 */
public class menuFXMLController implements Initializable {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    public void switchToMainMenu (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().
                getResource("mainMenuFXML.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        root.getStylesheets().add(getClass().
                getResource("MainMenuFXML.css").toExternalForm());
        stage.setTitle("Pirate Pillagers");
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToCredits (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().
                getResource("creditsFXML.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        root.getStylesheets().add(getClass().
                getResource("MainMenuFXML.css").toExternalForm());
        stage.setTitle("Pirate Pillagers");
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToHelp (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().
                getResource("helpFXML.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        root.getStylesheets().add(getClass().
                getResource("MainMenuFXML.css").toExternalForm());
        stage.setTitle("Pirate Pillagers");
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLeaderboard (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().
                getResource("leaderboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        root.getStylesheets().add(getClass().
                getResource("MainMenuFXML.css").toExternalForm());
        stage.setTitle("Pirate Pillagers");
        stage.setScene(scene);
        stage.show();
    }
    
    public void startGame (ActionEvent event) throws IOException {
        ((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
        new CustomCharacter();
        //new Main(); //Uncomment this line and comment new CustomCharacter to take out customization
    }

    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
