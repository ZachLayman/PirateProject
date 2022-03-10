package GameFunctions;

import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.sound.sampled.*;

public class Sound extends Board { ////Right now music plays during the main game and game over screen.
    
    public Sound () throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("Assets//Salty_Ditty.wav"); 
        //in NetBeans, the filepath was just "Salty_Ditty.wav" to get it to work because of how the files were placed in that version of the project.

        try {
        AudioInputStream music = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(music);
        clip.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }      
    }
}
