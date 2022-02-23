package CharacterSprites;

import static GameFunctions.Variables.CANNON_HEIGHT;
import static GameFunctions.Variables.CANNON_SPEED;
import static GameFunctions.Variables.CANNON_WIDTH;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class Cannonball extends MovingObject {

    Cannonball(int x, int y) {
        super(x, y);
        loadImage("Assets//cannonball.png");
        width = CANNON_WIDTH;
        height = CANNON_HEIGHT;
        dy =- CANNON_SPEED;
    }

    @Override
    public void move() {
        if(y <= 0)
            this.die();
            super.move();
    }

}
