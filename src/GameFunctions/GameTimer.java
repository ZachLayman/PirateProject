package GameFunctions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class GameTimer {
    
    private double elaspedTime = 0;
    private int minutes = 0;
    private double seconds = 0.00;
    Timer timer = new Timer(100, new ActionListener(){
         
        public void actionPerformed(ActionEvent e){
            
            elaspedTime=elaspedTime+100;
            minutes = (int) (elaspedTime/60000) % 60;
            seconds = (elaspedTime/1000) % 60;
        }
    }); 
        
    public GameTimer(){
        timer.start();
    }
    
    public int getMinutes(){
        return minutes;
    }
    
    public double getSeconds(){
        return seconds;
    }
    
    public void stopTimer(){
        timer.stop();
    }

}
