package pirate.pillagers;

import java.io.*;
import sun.audio.*;
import javax.swing.*;

public class Sound {
    
    public static void main (String[] args) { 
        Sound("Salty_Ditty.wav");
    }
 
    public static void Sound (String filepath) {
        InputStream music;
        try {
            music = new FileInputStream(new File(filepath));
            AudioStream audios = new AudioStream (music);
            AudioPlayer.player.start(audios);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error");
        }
            
    }
}