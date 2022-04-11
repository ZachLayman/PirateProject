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
import java.io.File;
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
import javafx.util.Duration;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author domin
 */
public class menuFXMLController implements Initializable {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    private static boolean sliderVisible;
    private static boolean volumeChanged;

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
        sliderVisible = false;
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
        sliderVisible = false;
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
        sliderVisible = true;
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
        sliderVisible = false;
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

    @FXML
    private Slider volumeSlider;

    @FXML
    private Label sliderLabel;

    private File musicFile;
    private static int playCnt = 0; //Counter to make the music play only once. 
    private static int sliderCnt = 0; //Counter to give the slider a default value
    private static Media media;
    private static MediaPlayer mediaPlayer;
    private static double volume;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try{ 
            topScores.setText(printLeaderboard());
        } catch (Exception e) {
            System.out.println("Failed to import leaderboard");
        }
		
                musicFile = new File("Assets//pirate-ship-at-bay.wav"); 

                if (playCnt == 0){ //this makes the media and media player play only once. Without the if, the music plays again on itself
                try { 
                        media = new Media(musicFile.toURI().toString());
                        mediaPlayer = new MediaPlayer(media);
                } catch (Exception e) {
                        e.printStackTrace();
                        }
                mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE); //makes the title screen audio loop forever until main menu is closed/left
                mediaPlayer.play();
                volumeChanged = false; // will only execute once, setting the flag to false on default. The flag is needed 
                playCnt++;
                } 

                if (sliderVisible){ //this makes the slider functions execute only on the help page  

                        if (sliderCnt == 0){ //give the volume slider a default value
                                volumeSlider.setValue(100);
                                sliderCnt++;
                        } else {        //save the changed volume to the slider UI
                                volumeSlider.setValue(volume * 100);
                        }

		volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
                        
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldNumber, Number newNumber) {
				mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
                                volume = mediaPlayer.getVolume();
                                volumeChanged = true;
                                volumeSlider.setValue(volume * 100);
                                //System.out.println("Volume in main menu: " + volume);
			}			
		});	
                
                }
	}
        //Function used to get the same volume settings over to the main game through the sound class
        public float getVolume(){
                float newVolume;

                if (volumeChanged){
                        newVolume = (float) volume;
                }
                else {
                        newVolume = 1; //the default on the slider is full volume but the original volume itself would be null here, making the game music mute if this is not here
                }
                System.out.println("New volume being returned: " + newVolume);
                return newVolume;
        }

        public MediaPlayer getMediaPlayer(){
                return mediaPlayer;
        }
    
    }    
