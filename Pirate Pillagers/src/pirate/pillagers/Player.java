package pirate.pillagers;

public class Player extends Character {
    
    boolean moveRight;
    boolean moveLeft;
    boolean shoot;
    //int bulletLocationX;
    int bulletLocationY;
    
    public Player (int x, int y, int s) {
        super (x,y,s);
        moveLeft = false;
        moveRight = false;
        shoot = false;
        //bulletLocationX = x + 5;
        bulletLocationY = y + 5;
    }
}
