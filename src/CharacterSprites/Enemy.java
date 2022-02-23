package CharacterSprites;

import java.util.Random;
import static GameFunctions.Variables.ENEMY_HEIGHT;
import static GameFunctions.Variables.ENEMY_WIDTH;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class Enemy extends MovingObject {

    Bomb bomb;
    boolean almostDied;     //for proper projection of explosion
    private Random rand;

    public Bomb getBomb() {
        return bomb;
    }

    Enemy(int x, int y) {
        super(x, y);
        rand = new Random();
        loadImage("Assets//Kraken.gif");
        width=ENEMY_WIDTH;
        height=ENEMY_HEIGHT;
        dx=1;
        almostDied=false;
        bomb=new Bomb(0, 0);
        bomb.die();
    }

    void tryToShoot() {
        int random = rand.nextInt()%400;
        if(random == 1 && !this.bomb.alive && this.alive){
            this.bomb.x = this.x+ENEMY_WIDTH/2;
            this.bomb.y = this.y+ENEMY_HEIGHT;
            this.bomb.alive = true;
        }
    }

    void setAlmostDied(boolean almostDied) {
        this.almostDied = almostDied;
    }

    @Override
    public void move() {
        super.move();
    }

}
