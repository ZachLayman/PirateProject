//==========================================================
// Description:
//   Framework for the user customzation
//==========================================================
package customPlayer;
package customPlayer.Panels;

import customPlayer.Panels.Panel_Boat;
import customPlayer.Panels.Panel_DSail;
import customPlayer.Panels.Panel_DShip;
import customPlayer.Panels.Panel_Header;
import customPlayer.Panels.Panel_Names;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;  

//==========================================================
//   Framework for the user customzation menu
//   Frame displays all the panels from the panel folder
//==========================================================
public class CustomUI extends JFrame //implements ActionListener
{
    //variables
    int x , y, width;
    GridBagConstraints gbc = new GridBagConstraints();
         
    public CustomUI ()
    {
        //frame design===================================      
        //CHANGE VARAIBLES TO GAME STANDARDS!!!!
        setSize(500,720);
        setTitle("Custom Your Character");           
        this.setIconImage(new ImageIcon("Assests/Kara.png").getImage());
        
        //define panels
        setLayout(new GridBagLayout());
        
        addPanelGrid(new Panel_Header(), x=0, y=0, width=5);
        addPanelGrid(new Panel_Boat(), x=0, y=1, width=3);
        addPanelGrid(new Panel_Names(), x=3, y=1, width=2);
        addPanelGrid(new Panel_DShip(), x=0, y=2, width=2);
        addPanelGrid(new JPanel(),x=2,y=2,width=1); //empty
        addPanelGrid(new Panel_DSail(), x=3, y=2, width=2);
                
        //make frame  and compentents visible
        this.setVisible(true);
        //make frame not resizeable
        this.setResizable(false);
        //close
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //add panels to grid
    void addPanelGrid (Component component, int x, int y, int width)
    {
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        add(component, gbc);        
    }
    
}