package CustomPlayer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//==========================================================
//   loads all the various images that a user can choice
//   between to design their ship
//==========================================================
public class Art4Boat 
{
    //images for different projectiles
    BufferedImage pirateFire;
    BufferedImage vikingFire;
    BufferedImage fantasyFire;
    BufferedImage modernFire;
    
    // arrays of images
    BufferedImage[] shadowDesigns = new BufferedImage[4];
    BufferedImage shipDesigns[] = new BufferedImage[4];
    BufferedImage sailDesigns[] = new BufferedImage[20];

    public Art4Boat() {
        // load shadows
        try {
            shadowDesigns[0] = ImageIO.read(new File("Assets//Custom_Player//Ships//PirateShadow.png"));
            shadowDesigns[1] = ImageIO.read(new File("Assets//Custom_Player//Ships//VikingShadow.png"));
            shadowDesigns[2] = ImageIO.read(new File("Assets//Custom_Player//Ships//ComingSoon.png"));
            shadowDesigns[3] = ImageIO.read(new File("Assets//Custom_Player//Ships//ModernShadow.png"));
        } catch (IOException e) {
            System.out.println("Could not load shadow image.");
        }

        // load Ship Designs
        try {
            shipDesigns[0] = ImageIO.read(new File("Assets//Custom_Player//Ships//PirateShip.png"));
            shipDesigns[1] = ImageIO.read(new File("Assets//Custom_Player//Ships//VikingBoat.png"));
            shipDesigns[2] = ImageIO.read(new File("Assets//Custom_Player//Ships//ComingSoon.png"));
            shipDesigns[3] = ImageIO.read(new File("Assets//Custom_Player//Ships//ModernBoat.png"));
        } catch (IOException e) {
            System.out.println("Could not load ship image.");
        }

        // load Sail Designs
        try {
            // load Pirate Ship Sail Designs
            sailDesigns[0] = ImageIO.read(new File("Assets//Custom_Player//Sails//PirateSkullSail.png"));
            sailDesigns[1] = ImageIO.read(new File("Assets//Custom_Player//Sails//PirateCrusadeSail.png"));
            sailDesigns[2] = ImageIO.read(new File("Assets//Custom_Player//Sails//PirateFlowerSail.png"));
            sailDesigns[3] = ImageIO.read(new File("Assets//Custom_Player//Sails//PirateCelestialSail.png"));
            sailDesigns[4] = ImageIO.read(new File("Assets//Custom_Player//Sails//PirateSolidSail.png")); 
            

            // load Viking Ship Sail Designs
            sailDesigns[5] = ImageIO.read(new File("Assets//Custom_Player//Sails//VikingDragon.png"));
            sailDesigns[6] = ImageIO.read(new File("Assets//Custom_Player//Sails//VikingStriped.png"));
            sailDesigns[7] = ImageIO.read(new File("Assets//Custom_Player//Sails//VikingTriforce.png"));
            sailDesigns[8] = ImageIO.read(new File("Assets//Custom_Player//Sails//VikingRunes.png"));
            sailDesigns[9] = ImageIO.read(new File("Assets//Custom_Player//Sails//VikingSolid.png"));

            // load Fantasy Ship Sail Designs
            sailDesigns[10] = ImageIO.read(new File("Assets//Custom_Player//Sails//ComingSoon.png"));
            sailDesigns[11] = ImageIO.read(new File("Assets//Custom_Player//Sails//ComingSoon.png"));
            sailDesigns[12] = ImageIO.read(new File("Assets//Custom_Player//Sails//ComingSoon.png"));
            sailDesigns[13] = ImageIO.read(new File("Assets//Custom_Player//Sails//ComingSoon.png"));
            sailDesigns[14] = ImageIO.read(new File("Assets//Custom_Player//Sails//ComingSoon.png"));

            // load Modern Ship Sail Designs
            sailDesigns[15] = ImageIO.read(new File("Assets//Custom_Player//Sails//Modern70s.png"));
            sailDesigns[16] = ImageIO.read(new File("Assets//Custom_Player//Sails//ModernUSA.png"));
            sailDesigns[17] = ImageIO.read(new File("Assets//Custom_Player//Sails//ModernFire.png"));
            sailDesigns[18] = ImageIO.read(new File("Assets//Custom_Player//Sails//ComingSoon.png"));
            sailDesigns[19] = ImageIO.read(new File("Assets//Custom_Player//Sails//ModernSolid.png"));
        } catch (IOException e) {
            System.out.println("Could not load sail image.");
        }

        // load projectiles per ship
        try {
            pirateFire = ImageIO.read(new File("Assets//Custom_Player//Projectiles//pirateCannon.png"));
            vikingFire = ImageIO.read(new File("Assets//Custom_Player//Projectiles//vikingArrow.png"));
            fantasyFire = ImageIO.read(new File("Assets//Custom_Player//Projectiles//fantasyFire.png"));
            modernFire = ImageIO.read(new File("Assets//Custom_Player//Projectiles//modernPerserver.png"));
        } catch (IOException e) {
            System.out.println("Could not load ship weapons.");
        }
    }
}
