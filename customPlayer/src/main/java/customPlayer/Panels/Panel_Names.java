package customPlayer.Panels;
package customPlayer;


import java.awt.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Dimension;

//==========================================================
// Panel which labels and two text fields which asks the 
// user for their Captain Name and Name of their ship
//==============================================================
public class Panel_Names extends JPanel
{
    //variables
    public JLabel lUserName, lUserShip;
    public JTextField tUserName, tUserShip;
    
    public String uName;
    
    Font pixel = new Font("Minecraft", Font.TRUETYPE_FONT,15);
        
    public Panel_Names()
    {          
        //layout
        setSize (200,400);
        setVisible(true);
        setLayout(new GridLayout(4,1,0,30));
        
        //declare labels
        lUserName = new JLabel("Captain Name", JLabel.CENTER);
        lUserShip = new JLabel("Ship Name", JLabel.CENTER);
        
        //setstyle
        lUserName.setFont(pixel);
        lUserShip.setFont(pixel);
        
        //declare text fields
        tUserName = new JTextField ("");
        tUserShip = new JTextField ("");
        
        //text field style
        tUserName.setFont(pixel);
        tUserShip.setFont(pixel);
                    
        //make visible
        add(lUserName);
        add(tUserName);
        add(lUserShip);
        add(tUserShip);
              
    }
            
}