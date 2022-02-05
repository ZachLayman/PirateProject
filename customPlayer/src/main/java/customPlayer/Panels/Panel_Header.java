package customPlayer.Panels;
package customPlayer;

import customPlayer.Panels.Panel_Names;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//==========================================================
// Panel which holds a button to access the menu 
//==============================================================
public class Panel_Header extends JPanel implements ActionListener
    {  
        public JButton menu, done;
        
        Font pixel = new Font("Minecraft", Font.TRUETYPE_FONT,12);
        Color background = new Color(244,226,198);
    
    public Panel_Header()
        {         
            //layout
            setSize (500,20);
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
                       
            menu = new JButton("   Menu   ");
            done = new JButton(" Complete? ");
            
            //set button style
            menu.setFont(pixel);
            menu.setBackground(background);
            menu.setFocusPainted(false);
            menu.setBorder(BorderFactory.createRaisedBevelBorder());
            
            done.setFont(pixel);
            done.setBackground(background);
            done.setFocusPainted(false);
            done.setBorder(BorderFactory.createRaisedBevelBorder());
                   
            //add buttons and make visible
            add(menu);
            add(Box.createHorizontalGlue());
            add(done);
            setVisible(true);
            
            //make buttons listen
            done.addActionListener(this);
            menu.addActionListener(this);
        }

    public void actionPerformed (ActionEvent e)
    {      
        //dialog text
        JLabel dialog = new JLabel("Are ye ready t' set sail");
        dialog.setFont(new Font("Minecraft", Font.TRUETYPE_FONT,17));
        
        //custom button options
        Object[] options = {"Aye, Aye", "Nay"};
        
        //add image        
        ImageIcon image = new ImageIcon ("Assests/ship_icon.png");
        
        int input = JOptionPane.showOptionDialog(null, dialog, null,
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, image, options, options[0]);
        
        if (input==0)
        {
           System.out.println("good job");
        }
        
    }
    
    }

