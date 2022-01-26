package pirate.ship;

import java.awt.*;
import javax.swing.JFrame;

public class PirateShip extends Canvas {
    
    public void paint(Graphics g){
        Toolkit tool = Toolkit.getDefaultToolkit();
        Image image = tool.getImage("Pirate Ship.png");
        g.drawImage(image, 150, 190, this);
    }

   
    public static void main(String[] args) {
        PirateShip ps = new PirateShip();
        JFrame frame = new JFrame();
        frame.add(ps);
        frame.setSize(300,300);
        frame.setVisible(true);
    }
    
}
