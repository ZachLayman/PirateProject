//==========================================================
//
// Title:      Pirate Pillager- User Customzation
// Author:     Jennifer Orlando
// Date:       1/30/22
// Description:
//   Displays an UI that allows the player to change the user character to
//   prefences. Colorpicker, custom name.
//
//==========================================================
package customPlayer; 

//====MAIN====

import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;

public class main 
{
    public static void main(String[] args)
    {
        
        /*set style for everything
        UIManager.put("OptionPane.background", Color.cyan);
        UIManager.put("Panel.background", Color.cyan);
        UIManager.put("Button.background", Color.orange);*/
        Font pixel = new Font("Minecraft", Font.TRUETYPE_FONT,12);
        UIManager.put("Button.font", pixel);
        
        //launch function in frame.java
        new CustomUI();
        
        
    }
}