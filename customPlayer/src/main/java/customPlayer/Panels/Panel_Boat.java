package customPlayer.Panels;
package customPlayer;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//==========================================================
// Panel which holds the graphical image of the ship that users 
// can customize using the compentents in Panel_Design
//==============================================================
public class Panel_Boat extends JPanel
{
        //variables
        Color cOcean = new Color(0,84,119);
    
    public Panel_Boat()
        {
            
            setSize (300,400);
        }
    
    public void paint(Graphics g)
    {
        g.setColor(cOcean);
        g.fillRect(0, 0, 300, 400);
    }
}