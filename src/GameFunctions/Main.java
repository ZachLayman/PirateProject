package GameFunctions;

/************************************************************
* PIRATE PILLAGERS GAME! 
*
* Created By Jennifer, Julia, Chris, Dom, Zachary, and Duy.
*
* A fast, fun, pixelated pirate pillaging game.
* 
* Citations: https://zetcode.com/javagames/spaceinvaders/ 
*            http://www.java2s.com/Tutorials/Java/Swing_How_to/Basic/Add_Background_image_to_JPanel.htm
*            https://www.youtube.com/watch?v=VMSTTg5EEnY
*            Used to assist us in making our program!
************************************************************/

import static GameFunctions.Variables.BOARD_HEIGHT;
import static GameFunctions.Variables.BOARD_WIDTH;
import javax.swing.*;
import java.io.*;

public class Main extends JFrame{

    public Main() {
        Gameplay();
    }

    private void Gameplay() {
        this.add(new Board());

        setSize(BOARD_WIDTH, BOARD_HEIGHT);
        setResizable(false);

        setTitle("Pirate Pillagers");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        //Sound sounds = new Sound(); //working on getting the music to play 
        new Main();
    }
}
