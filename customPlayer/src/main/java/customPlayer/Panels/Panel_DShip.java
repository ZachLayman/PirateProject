package customPlayer.Panels;
package customPlayer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;  
import javax.swing.border.Border;

//==========================================================
// Panel which holds buttons that the user can interact with
// This changes how the ship looks in Panel_Boat
//==============================================================
public class Panel_DShip extends JPanel
{
    //variables
    JButton bLeftShip, bRightShip, bcolorShip1, bcolorShip2;
    JLabel lShipDesign, lShipDNum, lShipColor1, lShipColor2;
    
    int designNum=64;
    
    int x , y, width;
    GridBagConstraints gbc = new GridBagConstraints();
    
    public Panel_DShip()
    {
        //layout
        setSize (200,300);
        setVisible(true);
        setLayout(new GridBagLayout());     
        
        //add components        
        lShipDesign = new JLabel("Ship Design", JLabel.CENTER);
        bLeftShip = new JButton("<");
        lShipDNum = new JLabel(String.valueOf(designNum), JLabel.CENTER);
        bRightShip = new JButton(">");
        lShipColor1 = new JLabel("Alternate Color 1", JLabel.CENTER);
        bcolorShip1 = new JButton("Color 1");    
        lShipColor2 = new JLabel("Alternate Color 2", JLabel.CENTER);
        bcolorShip2 = new JButton("Color 2");
                
        //place grid contents
        addGrid(lShipDesign,x=0, y=0, width=3);
        addGrid (bLeftShip, x=0, y=1, width=1);
        addGrid (lShipDNum, x=1, y=1, width=1);
        addGrid (bRightShip, x=2, y=1, width=1);
        addGrid (lShipColor1, x=0, y=2, width=3);
        addGrid (bcolorShip1, x=1, y=3, width=1);
        addGrid (lShipColor2, x=0, y=4, width=3);
        addGrid (bcolorShip2, x=1, y=5, width=1);
        
    }
    
    //add components to grid
    void addGrid (Component component, int x, int y, int width)
    {
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        component.setFont(new Font("Minecraft", Font.TRUETYPE_FONT,10));
        add(component, gbc);               
    }
}