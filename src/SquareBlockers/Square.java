package SquareBlockers;

import java.awt.*;
import static GameFunctions.Variables.SQUARE_SIZE;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

class Square extends Rectangle  {

    boolean visible;

    Square(int x, int y) {
        super(x, y, SQUARE_SIZE, SQUARE_SIZE);
        setVisible(true);
    }

    void setVisible(boolean visible) {
        this.visible = visible;
    }

    void draw(Graphics g) {
        
        g.setColor(new Color(209, 197, 159));
        g.fillRect(x, y, width, height);
    }
}
