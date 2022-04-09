package CharacterSprites;

import GameFunctions.Sound;
import GameFunctions.Board;
import static GameFunctions.Variables.*;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class PlayerCharacter extends MovingObject {

    private Cannonball m;
    private Sound shootFX;

    public PlayerCharacter (int x, int y) {
        super(x, y);
        //loadImage("Assets//User Ship.png"); 
        loadImage("Saves//userShip.png"); //if user customization works
        width = PLAYER_WIDTH;
        height = PLAYER_HEIGHT;
        m = new Cannonball(0, 0);
        m.die();
    }

    public void initializeFX(){
        shootFX = Board.bgMusic; //do not have this in the constructor, or else you get a null pointer error
    } 
    
    public Cannonball getM() {
        return m;
    }

    public void revive() {
        //loadImage("Assets//User Ship.png");
        loadImage("Saves//userShip.png"); //if user customization works
        setDying(false);
        x=BOARD_WIDTH/2;
    }

    public void cannonMove() {
        if(m.isVisible()) {
            m.move();
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -PLAYER_SPEED;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = PLAYER_SPEED;
        }
        if (key == KeyEvent.VK_SPACE) {
            if(!m.alive) {
                m.alive = true;
                m.x = this.x + PLAYER_WIDTH/2;
                m.y = this.y;
                shootFX.playShootFX(); //for the shooting sound FX
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if(key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    @Override
    public void move() {
        if(x > BOARD_WIDTH-PLAYER_WIDTH)
            x = BOARD_WIDTH-PLAYER_WIDTH;
        else if(x < 0)
            x = 0;
        else
            super.move();
    }

}
