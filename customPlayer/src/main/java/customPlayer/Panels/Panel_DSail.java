package customPlayer.Panels;
package customPlayer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;  

//==========================================================
// Panel which holds buttons that the user can interact with
// This changes how the sail looks in Panel_Boat
//==============================================================
public class Panel_DSail extends JPanel
{
    //variables
    JButton bLeftSail, bRightSail, bcolorSail1, bcolorSail2;
    JLabel  lSailDesign, lSailDNum, lSailColor1, lSailColor2;
    int designNum=64;
    
    int x , y, width;
    GridBagConstraints gbc = new GridBagConstraints();
    
    public Panel_DSail()
    {
        //layout
        setSize (200,300);
        setVisible(true);
        setLayout(new GridBagLayout());     
        
        //add components        
        lSailDesign = new JLabel("Sail Design", JLabel.CENTER);
        bLeftSail = new JButton("<");
        lSailDNum = new JLabel(String.valueOf(designNum), JLabel.CENTER);
        bRightSail = new JButton(">");
        lSailColor1 = new JLabel("Alternate Color 1", JLabel.CENTER);
        bcolorSail1 = new JButton("Color 1");    
        lSailColor2 = new JLabel("Alternate Color 2", JLabel.CENTER);
        bcolorSail2 = new JButton("Color 2");
                
        //place grid contents
        addGrid(lSailDesign,x=0, y=0, width=3);
        addGrid (bLeftSail, x=0, y=1, width=1);
        addGrid (lSailDNum, x=1, y=1, width=1);
        addGrid (bRightSail, x=2, y=1, width=1);
        addGrid (lSailColor1, x=0, y=2, width=3);
        addGrid (bcolorSail1, x=1, y=3, width=1);
        addGrid (lSailColor2, x=0, y=4, width=3);
        addGrid (bcolorSail2, x=1, y=5, width=1);
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