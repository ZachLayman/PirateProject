package customPlayer.Panels;
package customPlayer;

import java.awt.BorderLayout;
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
// Panel which holds buttons that the user can interact with
// This changes how the ship looks in Panel_Boat
//==============================================================
public class Panel_Design extends JPanel
{
    //variables
    JPanel panelShipD, panelSailD;
    
    public Panel_Design()
    {
        //stuff
        panelShipD = new Panel_Design_Ship();
        add(panelShipD, BorderLayout.WEST);
        
        panelSailD = new Panel_Design_Sail();
        add(panelSailD, BorderLayout.EAST);
    }
}