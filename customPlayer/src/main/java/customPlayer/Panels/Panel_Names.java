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
// Panel which labels and two text fields which asks the 
// user for their Captain Name and Name of their ship
//==============================================================
public class Panel_Names extends JPanel
{
    //variables
    private JLabel lUserName, lUserShip;
    private JTextField tUserName, tUserShip;
        
    public Panel_Names()
    {          
        //layout
        BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxlayout);
        
        //declare labels
        lUserName = new JLabel("Captain Name");
        lUserShip = new JLabel("Ship Name");
            
        //set Font
        lUserName.setFont(new Font("Minecraft", Font.TRUETYPE_FONT,20)); 
        lUserShip.setFont(new Font("Minecraft", Font.TRUETYPE_FONT,20)); 
            
        //declare text fields
        tUserName = new JTextField ("");
        tUserShip = new JTextField ("");
        
        //text field style
        tUserName.setMaximumSize(new Dimension(300,30));
        tUserShip.setMaximumSize(new Dimension(300,30));
                    
        //make visible
        add(lUserName);
        add(tUserName);
        add(lUserShip);
        add(tUserShip);
        setVisible(true);
    }
}