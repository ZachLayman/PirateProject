package moving.rectangle;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PirateShip extends Frame implements KeyListener{

    int x = 200, y = 380;
    BufferedImage pirateShip;
    BufferedImage shoot;
    Boolean shot = false;
    
    public PirateShip(){
        
        setSize(500,500);
        setTitle("Moving Ship");
        addKeyListener(this);
        
        addWindowListener(new WindowAdapter() {
            
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        }); 
        
        try{
        pirateShip = ImageIO.read(new File("Pirate Ship.png"));
        } catch(IOException ex){
            System.out.println("The image cannot be loaded.");
        }
        try{
            shoot = ImageIO.read(new File("Pirate ship.gif"));
        }catch(IOException ex){
            System.out.println("Gif cannot be loaded");
        }
    }
    
    public void paint(Graphics g){
        //g.drawString("x=" + x + ", y=" + y, 40,50);
        //g.drawRect(40, 50, 400, 400);
        //g.setColor(Color.getHSBColor(29, 86, 49));
        //g.fillRect(x, y, 50, 30);
        super.paintComponents(g);
        g.drawLine(40, 51, 40, 460); //left
        g.drawLine(430, 51, 430, 460); //right
        g.drawLine(40, 51, 430, 51); //top
        g.drawLine(40, 460, 430, 460); //bottom
        g.drawImage(pirateShip, x, y, 40, 80, this);
        
        if (shot == true){
            g.drawImage(shoot, x, y, 40, 80, this);
        }
        
    }
    public void keyPressed(KeyEvent ke){
        
        int code = ke.getKeyCode();
        
        switch (code){
          /*  case KeyEvent.VK_UP:
                if(y > 51){
                y = y - 1;
                }
                break;
            case KeyEvent.VK_DOWN:
                if(y < 420){
                    y = y + 1;
                }
                break; */
            case KeyEvent.VK_LEFT:
                if (x > 41){
                    x = x - 5;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(x < 390){
                    x = x + 5;
                }
                break;
            case KeyEvent.VK_SPACE: //right now the space bar just switches between images. 
                shot = !(shot);
        }
        
        repaint();
    }
    
    public void keyTyped(KeyEvent ke){
        
    }
    
    public void keyReleased(KeyEvent ke){
        
    }
    
    public static void main(String[] args) {
        PirateShip r = new PirateShip();
       r.setVisible(true);
    }
    
} 
