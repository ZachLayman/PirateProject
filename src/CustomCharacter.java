package customPlayer;


import java.awt.*;
import javax.swing.BorderFactory;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter; 
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

//==========================================================
//   Code to allow user to choose ship and sail style
//   and color of them. Also asks user for their name 
//   and name of their ship.
//==========================================================
public class CustomCharacter extends JFrame 
{
//VARIABLES===================================
    //Buttons
    JButton menu, done;
    JButton bColorSail;
    
    //Labels
    JLabel lUserName, lUserShip;
    JLabel lShipDesign;
    JLabel  lSailDesign;
    
    //TextField
    JTextField tUserName, tUserShip;
    
    //Slider
    JSlider sShipStyle;
    JSlider sSailStyle;
 
    int sShip_Min = 0, sShip_Max = 3, sShip_Start = 0;
    int sSail_Min = 0, sSail_Max = 4, sSail_Start = 0;
    
    //JLayered Pane and Boat Design Label (art)
    int shipNum, sailNum;
    
    JLayeredPane boatCanvas;
    
    JLabel topShadows[] = new JLabel[4];
    JLabel shipDesigns[] = new JLabel[4]; 
    JLabel sailDesigns[] = new JLabel[20];
    
    BufferedImage pSolid, vSolid, fSolid, mSolid;
    String pSailPath, vSailPath, fSailPath, mSailPath;
    
    Color userSailColor;
    
    //Layout Variables
    int x , y, width, height;
    
    //Colors
    Color myBlue = new Color (0,200,214);
    Color myWhite = new Color (214,254,252);
    Color myBrown = new Color (93, 71,50);
    
    public CustomCharacter ()
    {
//FRAME=====================================
        //design
        setSize(640,480);
        setTitle("Custom Your Character");  
        this.setBackground(Color.yellow);
        this.setIconImage(new ImageIcon("Assests//ship_icon.png").getImage());
        this.setLayout(null);
        
        //set background image
        this.setContentPane(new JLabel(new ImageIcon("Assests//Custom_Player//Background.png")));

        //make frame not resizeable
        this.setResizable(false);
        //close
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//LABELS================================
        //initialize labels
        lUserName = new JLabel("Captain Name", JLabel.CENTER);
        lUserShip = new JLabel("Ship Name", JLabel.CENTER);
        lShipDesign = new JLabel("Ship Design", JLabel.CENTER);
        lSailDesign = new JLabel("Sail Design", JLabel.CENTER);

        //set font for label style
        lUserName.setFont(new Font("Minecraft", Font.BOLD,15));
        lUserShip.setFont(new Font("Minecraft", Font.BOLD,15));
        lShipDesign.setFont(new Font("Minecraft", Font.BOLD,15));
        lSailDesign.setFont(new Font("Minecraft", Font.BOLD,15));
        
        //set color of text
        lUserName.setForeground(myWhite);
        lUserShip.setForeground(myWhite);
        lShipDesign.setForeground(myWhite);
        lSailDesign.setForeground(myWhite);

        //set size and add labels to frame using addToFrame method
        addToFrame(lUserName, x=42, y=125, width=120, height=30);
        addToFrame(lUserShip, x=42, y=269, width=120, height=30);
        addToFrame(lShipDesign, x=478, y=130, width=105, height=30);
        addToFrame(lSailDesign, x=478, y=274, width=105, height=30);

               
//BUTTONS================================
        //initialize buttons
        menu = new JButton("Menu");
        done = new JButton("Complete?");
        bColorSail = new JButton("Color");    
        
       //set button style
        menu.setBackground(myBlue);      
        menu.setForeground(myWhite);
        menu.setFocusPainted(false);
        menu.setBorder(BorderFactory.createRaisedBevelBorder());
        
        done.setBackground(myBlue);
        done.setForeground(myWhite);
        done.setFocusPainted(false);
        done.setBorder(BorderFactory.createRaisedBevelBorder());
        
        bColorSail.setBackground(myBlue);
        bColorSail.setForeground(myWhite);
        bColorSail.setFocusPainted(false);
        bColorSail.setBorder(BorderFactory.createRaisedBevelBorder());
        

        //set size and add buttons to frame using addToFrame method
        addToFrame(menu, x=0, y=0, width=120, height=40);
        addToFrame(done, x=510, y=0, width=120, height=40);
        addToFrame(bColorSail, x=493, y=360,  width=75, height=40);

        
        //make bColorSail invisble
        bColorSail.setVisible(false);
        

//TEXT FIELDS============================
        //initialize text fields
        tUserName = new JTextField ("type...");
        tUserShip = new JTextField ("...here");
        
        //set Text field style font
        tUserName.setFont(new Font("Minecraft", Font.TRUETYPE_FONT,15));
        tUserShip.setFont(new Font("Minecraft", Font.TRUETYPE_FONT,15));
        
        tUserName.setForeground(myWhite);
        tUserShip.setForeground(myWhite);
        
        //set style
        tUserName.setBackground(myBlue);
        tUserShip.setBackground(myBlue);
        
        //set size and add text fields to frame using addToFrame method
        addToFrame(tUserName, x=39, y=167, width=126, height=30);
        addToFrame(tUserShip, x=39, y=311, width=126, height=30);

//SLIDERS================================
        //initialize sliders
        sShipStyle = new JSlider(JSlider.HORIZONTAL,sShip_Min,sShip_Max,sShip_Start);
        sSailStyle = new JSlider(JSlider.HORIZONTAL,sSail_Min,sSail_Max,sSail_Start);

        //set slider style
//        sShipStyle.setPaintTicks(true);
//        sSailStyle.setPaintTicks(true);
        
        sShipStyle.setMajorTickSpacing(1);
        sSailStyle.setMajorTickSpacing(1);
        
        sShipStyle.setOpaque(false);
        sSailStyle.setOpaque(false);
        
     //set size and add labels to frame using addToFrame method
        addToFrame(sShipStyle, x=470, y=156, width=120, height=60);
        addToFrame(sSailStyle, x=470, y=300, width=120, height=60);

        
//BOAT CANVAS============================
        //set design numbers to the slider vaules
        shipNum = 0;
        sailNum = 0;
        
        //set file paths for solid sail images
        pSailPath = "Assests//Custom_Player//Sails//PirateSolidSail.png";
        vSailPath = "Assests//Custom_Player//Sails//ComingSoon.png";
        fSailPath = "Assests//Custom_Player//Sails//ComingSoon.png";
        mSailPath = "Assests//Custom_Player//Sails//ComingSoon.png";

        //initialize layered pane
        boatCanvas = new JLayeredPane();
        
        //load solid sail images as buffered image files
        try
        {
            pSolid = ImageIO.read(new File(pSailPath));
            vSolid = ImageIO.read(new File(vSailPath));
            fSolid = ImageIO.read(new File(fSailPath));
            mSolid = ImageIO.read(new File(mSailPath));
        } 
        catch(IOException e){System.out.println("Could not load image.");}
        
        //initializelabels     
        //ships
        shipDesigns[0] = new JLabel((new ImageIcon("Assests//Custom_Player//Ships//PirateShip.png")));
        shipDesigns[1] = new JLabel((new ImageIcon("Assests//Custom_Player//Ships//VikingBoat.png")));
        shipDesigns[2] = new JLabel((new ImageIcon("Assests//Custom_Player//Ships//ComingSoon.png")));
        shipDesigns[3] = new JLabel((new ImageIcon("Assests//Custom_Player//Ships//ComingSoon.png")));
        
        //ship top shadows
        topShadows[0] = new JLabel((new ImageIcon("Assests//Custom_Player//Ships//PirateShadow.png")));
        topShadows[1] = new JLabel((new ImageIcon("Assests//Custom_Player//Ships//VikingShadow.png")));
        topShadows[2] = new JLabel((new ImageIcon("Assests//Custom_Player//Ships//ComingSoon.png")));
        topShadows[3] = new JLabel((new ImageIcon("Assests//Custom_Player//Ships//ComingSoon.png")));
        
        //sails
        sailDesigns[0] = new JLabel((new ImageIcon("Assests//Custom_Player//Sails//PirateSkullSail.png")));
        sailDesigns[1] = new JLabel((new ImageIcon("Assests//Custom_Player//Sails//PirateStripedSail.png")));
        sailDesigns[2] = new JLabel((new ImageIcon("Assests//Custom_Player//Sails//PirateFlowerSail.png")));
        sailDesigns[3] = new JLabel((new ImageIcon("Assests//Custom_Player//Sails//PirateCelestial.png")));
        sailDesigns[4] = new JLabel((new ImageIcon(pSolid)));
        
        sailDesigns[5] = new JLabel((new ImageIcon("Assests//Custom_Player//Sails//VikingStriped.png")));
        sailDesigns[6] = new JLabel((new ImageIcon("Assests//Custom_Player//Sails//ComingSoon.png")));
        sailDesigns[7] = new JLabel((new ImageIcon("Assests//Custom_Player//Sails//ComingSoon.png")));
        sailDesigns[8] = new JLabel((new ImageIcon("Assests//Custom_Player//Sails//ComingSoon.png")));
        sailDesigns[9] = new JLabel((new ImageIcon(vSolid)));
        
        sailDesigns[10] = new JLabel((new ImageIcon("Assests//Custom_Player//Sails//ComingSoon.png")));
        sailDesigns[11] = new JLabel((new ImageIcon("Assests//Custom_Player//Sails//ComingSoon.png")));
        sailDesigns[12] = new JLabel((new ImageIcon("Assests//Custom_Player//Sails//ComingSoon.png")));
        sailDesigns[13] = new JLabel((new ImageIcon("Assests//Custom_Player//Sails//ComingSoon.png")));
        sailDesigns[14] = new JLabel((new ImageIcon(fSolid)));
        
        sailDesigns[15] = new JLabel((new ImageIcon("Assests//Custom_Player//Sails//ComingSoon.png"))); 
        sailDesigns[16] = new JLabel((new ImageIcon("Assests//Custom_Player//Sails//ComingSoon.png")));
        sailDesigns[17] = new JLabel((new ImageIcon("Assests//Custom_Player//Sails//ComingSoon.png")));
        sailDesigns[18] = new JLabel((new ImageIcon("Assests//Custom_Player//Sails//ComingSoon.png")));
        sailDesigns[19] = new JLabel((new ImageIcon(mSolid)));

        //add layered pane to frame and set size
        addToFrame(boatCanvas, x=197, y=50, width=230, height=345);

        //add labels to layered pane and set size
        for (int i=0; i<20; i++)
        {
            if (i<4)
            {
                //the shadows top layer = 3
                boatCanvas.add(topShadows[i],Integer.valueOf(3));
                topShadows[i].setBounds(0,0, 230,345);
                
                //the boat third layer = 1
                boatCanvas.add(shipDesigns[i],Integer.valueOf(1));
                shipDesigns[i].setBounds(0,0, 230,345);
            }
            //the sails second layer = 2
            boatCanvas.add(sailDesigns[i],Integer.valueOf(2));
            sailDesigns[i].setBounds(0,0, 230,345);
        }
        
        //call method to make correct layers visible
        visibleLayers();
        
                
//make frame visible============================
        this.setVisible(true);
                
//==========================================================
//   Action Events: 
//        make interative components listen and do things
//==========================================================     
    //leads user back to main menu
        menu.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    System.out.println("Menu Button");
                } 
            });
            
    //prompts user to see if theyre done designing, if yes user starts game and a save file is generated
        done.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed (ActionEvent e)
            {      
                //dialog text
                JLabel dialog = new JLabel("Are ye ready t' set sail");
                dialog.setFont(new Font("Minecraft", Font.TRUETYPE_FONT,17));

                //custom button options
                Object[] options = {"Aye, Aye", "Nay"};

                //add image        
                ImageIcon image = new ImageIcon ("Assests//ship_icon.png");

                int input = JOptionPane.showOptionDialog(null, dialog, null,
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, image, options, options[0]);

                if (input==0) //if true
                {
                    GenerateSave();
                }

            }  
        });
        
    //when user slides slider changes boat design number (1-4)
        sShipStyle.addChangeListener(new ChangeListener()
        {
          public void stateChanged(ChangeEvent e)
          {
                System.out.println("vaule of slider is" + sShipStyle.getValue());
                //set ship design numbers to the slider vaule
                sailNum = (sShipStyle.getValue() * 5) + sSailStyle.getValue();
                shipNum = sShipStyle.getValue();                 
                
                //call method to make correct layers visible
                visibleLayers();
          }
        });
        
    //when user slides slider changes the sail design that corresponds to the boat design (16 options)
        sSailStyle.addChangeListener(new ChangeListener()
        {
          public void stateChanged(ChangeEvent e)
          {
                System.out.println("vaule of slider is" + sSailStyle.getValue());
                //set sail design numbers to the slider vaule
                sailNum = (sShipStyle.getValue() * 5) + sSailStyle.getValue();
                
                                
                //make color button visible if sail is the solid design
                if (sSailStyle.getValue() == 4)
                    bColorSail.setVisible(true);
                else
                    bColorSail.setVisible(false);
                
                //call method to make correct layers visible
                visibleLayers();
           }
        });

    //when clicked brings up color select menu which lets
    //the user pick a color for the solid sail designs
    // should only be visible when solid sails choice appears
        bColorSail.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("color two sail button");
                JColorChooser colorChooser = new JColorChooser();
                Color color = JColorChooser.showDialog(null, "Pick a color for the sail", new Color (242,237,208));
                
                //color the correct sail for the boat
                switch (shipNum)
                {
                    case 0: getPixel(pSolid,color,pSailPath);
                            sailDesigns[4].setIcon(new ImageIcon(pSolid));
                            break;
                    case 1: getPixel(vSolid,color,fSailPath);
                            sailDesigns[9].setIcon(new ImageIcon(vSolid));
                            break;
                    case 2: getPixel(fSolid,color,vSailPath);
                            sailDesigns[14].setIcon(new ImageIcon(fSolid));
                            break;
                    case 3: getPixel(mSolid,color,mSailPath);
                            sailDesigns[19].setIcon(new ImageIcon(mSolid));
                            break;
                }
            } 
        });
    }
//==========================================================
//   addToFrame: 
//        take Compenents and add them to frame. 
//            and make them visible
//========================================================== 
    void addToFrame(Component component, int x, int y, int width, int height)
    {
        component.setBounds(x, y, width, height);
        add(component);
    }
    
//==========================================================
//   VisibleLayers: 
//        take ship design and sail design arrays. 
//        And make the layers visible and invisible 
//        based off the vaule of the sliders.
//========================================================== 
    public void visibleLayers()
    {
        System.out.println("making layers visible and invisible" + shipNum + " "+ sailNum);
        
        //cycle though ship designs
        for (int i = 0; i<4; i++)
        {
            if (i==shipNum)
            {
               topShadows[i].setVisible(true);
               shipDesigns[i].setVisible(true);
            }
            else
            {
                topShadows[i].setVisible(false);
                shipDesigns[i].setVisible(false);
            }
        }
        
        //cycle though sail designs
        for (int i = 0; i<20; i++)
        {
            if (i==sailNum)
               sailDesigns[i].setVisible(true);
            
            else
                sailDesigns[i].setVisible(false);
        }
    }
//==========================================================
//   getPixel: 
//        cycles though sail images to see which ones
//        are not transperent and changes the image to 
//        to the color the use choose.
//========================================================== 
    public void getPixel (BufferedImage sail_IMG, Color userColor, String filePath)
    {
        userSailColor = userColor;

        //loop though all the pixels of the image
        for (int y = 0; y <sail_IMG.getHeight(); y++)
        {
            for (int x = 0; x<sail_IMG.getWidth(); x++)
            {
                //grab pixel alpha vaule
                int pixel = sail_IMG.getRGB(x, y);
                Color pcolor = new Color(pixel, true);
                int pixelalpha = pcolor.getAlpha();

                //if pixel is not transparent change color color to user defined color
                if (pixelalpha != 0)
                    sail_IMG.setRGB(x,y,userColor.getRGB());
            }
        }
        
        //Replace the og image with the updated one
        try
        {
            ImageIO.write(sail_IMG, "png", new File(filePath));
                    System.out.println("Image successfully updated");
        } 
        catch(IOException p){System.out.println("Could not update image.");}
    }
//==========================================================
//   grab all the user prefences and place it in a save
//   file (.txt and .png) for access later on
//==========================================================
    public void GenerateSave ()
   {
        String userColor, saveContents;
        String newLine = System.getProperty("line.separator");

        //check if user choosen color needs to be saved
        //if user did not choose a solid design set color to null
        if (sailNum == 4 || sailNum == 9 || sailNum != 14 || sailNum != 19)
            userColor = String.valueOf(userSailColor);
        else
            userColor = null;
       
       //grab user design choices and compile the save contents
       saveContents =  tUserName.getText() + newLine +
               tUserShip.getText() + newLine +
               String.valueOf(shipNum) + newLine +
               sailNum + newLine +
               userColor;

       //save user prefences to text file
       try
       {
           FileWriter userSave = new FileWriter("Saves//savefile.txt");
           userSave.write(saveContents);
           userSave.close();
           System.out.println("Successfully wrote to the file");
        } 
       catch (IOException e)
       {
           System.out.println("An error occured.");
           System.out.println(e);
       }
       
   }

//    @Override    
//    public void paint (Graphics g)
//    {
//                 
//       //generate canvas
//       Canvas saveShip = new Canvas();
//       saveShip.setSize(230,345);
//       
//       //draw ship
//       
//       
//       //compile canvas into an image
//       BufferedImage userShipIMG = new BufferedImage(canvas.getWidth(),canvas.getHeight(),BufferedImage.TYPE_INT_RGB);
//       
//
//        //close program
//       System.exit(0); 
//    }
}
