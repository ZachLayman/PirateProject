package MainMenu;
import GameFunctions.Main;
import CustomPlayer.CustomCharacter;
import GameFunctions.LeaderboardMap;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

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
    private static Map<String, Integer> leaderMap = new HashMap<String, Integer>();
    static String s = null;

    //printing top 5 lines of file for map
    public static String printLeaderboard() {
        String leaderboard[] = new String[5];
        String tmpArray[] = new String[10];
        int i = 0;
        int y = 0;
        try {
            Scanner fileIn = new Scanner (new File("Saves/playerScore.txt"));
            while (fileIn.hasNext() && i < tmpArray.length) {
                tmpArray[i] = fileIn.nextLine();
                i++;
            }
            for (int x = 0; x < 5; x++) {
                leaderboard[x] = tmpArray[x];
            }
            //make array a single string
            StringBuffer sb = new StringBuffer();
            for (int x = 0; x < leaderboard.length; x++) {
                sb.append(leaderboard[x]);
                sb.append("\n\n");
            }
            s = sb.toString();
        } catch (Exception e) {
            s = "Failed to get scores";
        }
        return s;
    }
    
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
    private Label topScores;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{ 
            topScores.setText(printLeaderboard());
        } catch (Exception e) {
            System.out.println("Failed to import leaderboard");
        }
    }    
    
}
