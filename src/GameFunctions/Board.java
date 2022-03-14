package GameFunctions;

import static GameFunctions.Variables.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import SquareBlockers.Guard;
import CharacterSprites.Enemy;
import CharacterSprites.EnemyMovement;
import CharacterSprites.PlayerCharacter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Board extends JPanel implements Runnable {

    private List<Guard> guards;
    private String message;     //message for the end of a game
    private boolean inTheGame;
    private Integer lives;
    private PlayerCharacter player;
    private EnemyMovement enemyWave;
    private int gameScore = 0;
    
Board() {

        inTheGame = true;
        lives = 3; //can make it 5 lives if it gets too hard 

        player = new PlayerCharacter(START_X, START_Y);
        enemyWave = new EnemyMovement();

        guards = new ArrayList<>();
        for(int i = 0; i < 4 ; i++) {
            guards.add(new Guard(GUARD_POSX + i * 135, GUARD_POSY)); //position of the guard squares
    }

        addKeyListener(new Key());     //for Key events
        setFocusable(true);
        setBackground(Color.CYAN); //in place of a water background 
       
}



// public void start(Stage primaryStage) //TRYING TO PUT CUSTOM BACKGROUND 
//    {
//        StackPane root = new StackPane();
//        Scene scene = new Scene(root, 500, 500);
//        Image img = new Image("water.png");
//        BackgroundImage bImg = new BackgroundImage(img,
//                                                   BackgroundRepeat.NO_REPEAT,
//                                                   BackgroundRepeat.NO_REPEAT,
//                                                   BackgroundPosition.DEFAULT,
//                                                   BackgroundSize.DEFAULT);
//        Background bGround = new Background(bImg);
//        root.setBackground(bGround);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        
//    }



    @Override
    public void addNotify() {
        
        super.addNotify();
        Thread animator = new Thread(this);
        animator.start();
}

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while(inTheGame) {
            repaint();
            animationCycle();       //mechanics of a game

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAYTIME - timeDiff;

            if(sleep < 0) {
                sleep = 2;
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            beforeTime = System.currentTimeMillis();
        }
        goodGame();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Font font = new Font("Helvetica", Font.BOLD, 15);
        g.setColor(Color.WHITE);
        g.setFont(font);
        
        g.drawString("Enemies Remaining: " + enemyWave.getNumberOfEnemies().toString(), 20, 20);

        g.drawString("PIRATE PILLAGERS                                Lives: " + lives.toString(), BOARD_WIDTH - 370, 20);
        g.drawString("                                                Score: " + gameScore, BOARD_WIDTH - 295, 40);
        

        g.setColor(Color.WHITE);
        g.drawLine(0, GROUND, BOARD_WIDTH, GROUND);

        player.draw(g, this);
        if (player.getM().isVisible())
            player.getM().draw(g, this);

        enemyWave.draw(g, this);

        for (Guard guard : guards) {
            guard.draw(g);
        }
    }

    private void animationCycle() {
        if(enemyWave.getNumberOfEnemies() == 0) {
            
            inTheGame = false;
            message = "You Won. Man you got so lucky.";
        }

        if(player.isDying()) { 
            
            lives--; //decrement the lives shown
            
            if(lives != 0) player.revive(); //when you dont lose, you respawn 
            
            else {
                inTheGame = false;
                message = "Game Over. Man you suck, get better."; //idk why i said this
            }
        }

        if(enemyWave.reachedTheGround()) {
            inTheGame=false;
            message = "Game Over. Man you suck, get better.";
        }

        player.move();
        player.cannonMove();
        enemyWaveMove();
        
        collisionMissileEnemies();
        collisionBombPlayer();
        collisionWithGuards();
    }


    private void enemyWaveMove() {
        
        enemyWave.fixStatus();
        enemyWave.bombMove();
        enemyWave.shooting();
        enemyWave.accelerateIfNeeded();
        enemyWave.turnAroundIfHitTheWall();
    }

    private void collisionMissileEnemies() {
        if(player.getM().isVisible()) {
            for (Enemy enemy : enemyWave.getEnemies())
                if(enemy.isVisible() && player.getM().collisionWith(enemy)) {
                    enemy.explosion();
                    enemyWave.decreaseNumberOfEnemies();
                    player.getM().die();
                    gameScore = gameScore + 10;
                }
        }
    }

    private void collisionBombPlayer() {
        for(Enemy enemy : enemyWave.getEnemies()) {
            if (enemy.getBomb().isVisible() && enemy.getBomb().collisionWith(player)) {
                player.explosion();
                enemy.getBomb().die();
            }
        }
    }

    private void collisionWithGuards() {
        for(Guard guard : guards) {
            guard.collisionWith(player.getM());
            for (Enemy enemy : enemyWave.getEnemies()) {
                guard.collisionWith(enemy.getBomb());
            }
        }
    }

    private void goodGame() {
        Graphics g = this.getGraphics();
        super.paintComponent(g);

        Font font = new Font("Helvetica", Font.BOLD, 18);
        FontMetrics fonts = this.getFontMetrics(font);

        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString(message, (BOARD_WIDTH - fonts.stringWidth(message))/2, BOARD_HEIGHT/2);
        g.drawString("Your score: " + gameScore, (BOARD_WIDTH - fonts.stringWidth(message))/2, BOARD_HEIGHT/2 + 40);
    }

    private class Key extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

    }
}
