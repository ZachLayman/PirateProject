package CharacterSprites;

import GameFunctions.Board;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class MovingObject {

    private Image image;
    boolean dead;
    boolean alive;
    int x;
    int y;
    int dx;     //velocity OX
    int dy;     //velocity OY
    int width;
    int height;
    

    MovingObject(int x, int y) {
        this.x = x;
        this.y = y;
        alive = true;
        dead = false;
    }

    void loadImage(String imageName) {
        ImageIcon d = new ImageIcon(imageName);
        image = d.getImage();
    }

    public void explosion() {
        loadImage("Assets//Kraken-dead.png");
        setDying(true);
    }

    public Rectangle getBoundary() {
        return new Rectangle(x, y, width, height);
    }

    public boolean collisionWith(MovingObject o) {
        return this.getBoundary().intersects(o.getBoundary());
    }

    public void draw(Graphics g, Board board) {
        g.drawImage(image, x, y, width, height, board);
    }

    public void die() {
        alive = false;
    }

    public boolean isVisible() {
        return alive;
    }

    void setDying(boolean d) {
        dead = d;
    }

    public boolean isDying() {
        return dead;
    }

    public void move() {
        x += dx;
        y += dy;
    }

}
