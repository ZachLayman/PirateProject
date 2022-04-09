package GameFunctions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class GameTimer {
    
    private double elaspedTime = 0;
    private int minutes = 0;
    private double seconds = 0.00;
    private String minute_string;
    private String seconds_string;
    Timer timer = new Timer(100, new ActionListener(){
         
        public void actionPerformed(ActionEvent e){
            
            elaspedTime=elaspedTime+100;
            minutes = (int) (elaspedTime/60000) % 60;
            seconds = (elaspedTime/1000) % 60;
            minute_string = String.format("%02d", minutes);
            seconds_string = String.format("%1$, .1f", seconds);
        }
    }); 
        
    public GameTimer(){
        timer.start();
    }
    
    public String getMinutes(){
        return minute_string;
    }
    
    public String getSeconds(){
        return seconds_string;
    }
    
    public void stopTimer(){
        timer.stop();
    }

}
