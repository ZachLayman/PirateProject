//==========================================================
// Description:
//   Framework for the user customzation
//==========================================================
package customPlayer;
package customPlayer.Panels;

import customPlayer.Panels.Panel_Boat;
import customPlayer.Panels.Panel_Design;
import customPlayer.Panels.Panel_Header;
import customPlayer.Panels.Panel_Names;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;

//==========================================================
//   Framework for the user customzation menu
//   Frame displays all the panels from the panel folder
//==========================================================
public class CustomUI extends JFrame //implements ActionListener
{
    //variables
    private Panel_Boat panelBoat;
    private Panel_Design panelDesign;
    private Panel_Header panelHeader;
    private Panel_Names panelNames;
      
    public CustomUI ()
    {
        //frame design===================================      
        //CHANGE VARAIBLES TO GAME STANDARDS!!!!
        setSize(500,500);
        setTitle("Custom Your Character");           
        this.setIconImage(new ImageIcon("Assests/Kara.png").getImage());
        
        //layout
        setLayout(new BorderLayout());
       
        //action of frame exit
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
        //add Panels and place them in the frame
        panelHeader = new Panel_Header();
        add(panelHeader, BorderLayout.NORTH);
        
        panelBoat = new Panel_Boat();
        add(panelBoat, BorderLayout.WEST);
        
        panelNames = new Panel_Names();
        add(panelNames, BorderLayout.EAST);
        
        panelDesign = new Panel_Design();
        add(panelDesign, BorderLayout.SOUTH);
            
        //make frame  and compentents visible
        this.setVisible(true);
        
        //make frame not resizeable
        this.setResizable(false);
    }
    
}