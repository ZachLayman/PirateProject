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
// This changes how the sail looks in Panel_Boat
//==============================================================
public class Panel_Design_Sail extends JPanel
{
    //variables
    JButton bLeftSail, bRightSail, bColor1Sail, bColor2Sail;
    JLabel  lSailDesign, lSailDNum, lSailColor1, lSailColor2;
        
    public Panel_Design_Sail()
    {
        //stuff
    }
}