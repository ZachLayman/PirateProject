package CharacterSprites;

import static GameFunctions.Variables.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class Bomb extends MovingObject {

    Bomb(int x, int y) {
        super(x, y);
        loadImage("Assets//bomb.png");
        width = BOMB_WIDTH;
        height = BOMB_HEIGHT;
        dy = BOMB_SPEED;
    }

    @Override
    public void move() {
        if(y>GROUND-BOMB_HEIGHT)
            this.die();
        super.move();
    }
}
