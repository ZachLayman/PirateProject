package GameFunctions;

import java.io.*;
import sun.audio.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class Sound extends Board { //CANT FIGURE IT OUT YET SORRY
    
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
