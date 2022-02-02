package customPlayer.Panels;
package customPlayer;

import customPlayer.Panels.Panel_Names;

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
// Panel which holds a button to access the menu 
//==============================================================
public class Panel_Header extends JPanel
    {  
        private JButton menu, done;
    
    public Panel_Header()
        {
            menu = new JButton("Menu");
            done = new JButton("Complete?");
            
            //set button style
            menu.setFont(new Font("Minecraft", Font.TRUETYPE_FONT,10));
            done.setFont(new Font("Minecraft", Font.TRUETYPE_FONT,10));
            
            //add buttons and make visible
            add(menu);
            add(done);
            setVisible(true);
        }

    }
