package CharacterSprites;

import java.awt.event.KeyEvent;
import static GameFunctions.Variables.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class PlayerCharacter extends MovingObject {

    private Cannonball m;

    public PlayerCharacter (int x, int y) {
        super(x, y);
        loadImage("Assets//User Ship.png"); //Jenny's PNG 
        width = PLAYER_WIDTH;
        height = PLAYER_HEIGHT;
        m = new Cannonball(0, 0);
        m.die();
    }

    public Cannonball getM() {
        return m;
    }

    public void revive() {
        loadImage("Assets//User Ship.png");
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
