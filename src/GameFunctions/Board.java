package GameFunctions;

import static GameFunctions.Variables.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import SquareBlockers.Guard;
import CharacterSprites.Enemy;
import CharacterSprites.EnemyMovement;
import CharacterSprites.PlayerCharacter;
import CustomPlayer.CustomCharacter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import GameFunctions.LeaderboardMap;

import javax.swing.*;
import java.awt.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Board extends JPanel implements Runnable {

    private List<Guard> guards;
    private String message;     //message for the end of a game
    private boolean inTheGame;
    private Integer lives;
    private PlayerCharacter player;
    private EnemyMovement enemyWave;
    private int gameScore = 0;
    private GameTimer timer; //for timer
    public static Sound bgMusic; //This variable stays public do not change
    String playerName;
    boolean highScore;
    
Board() {

        inTheGame = true;
        lives = 3; //can make it 5 lives if it gets too hard 
        timer = new GameTimer();
        player = new PlayerCharacter(START_X, START_Y);
        enemyWave = new EnemyMovement();

        guards = new ArrayList<>();
        for(int i = 0; i < 4 ; i++) {
            guards.add(new Guard(GUARD_POSX + i * 135, GUARD_POSY)); //position of the guard squares
    }

        addKeyListener(new Key());     //for Key events
        setFocusable(true);
        setBackground(new Color (0,200,214)); //in place of a water background 
       
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

        try {
            bgMusic = new Sound();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }

        player.initializeFX();

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

        g.drawString("                                                Score: " + gameScore, BOARD_WIDTH - 285, 40);
        g.drawString("Timer: " + timer.getMinutes() + ":" + timer.getSeconds(), 200, 20); //Printing the timer on the board

        g.drawString("PIRATE PILLAGERS                        Lives: " + lives.toString(), BOARD_WIDTH - 330, 20); //Originally BW-370
        //had to edit oringinal placement to fit timer 


        //g.setColor(Color.WHITE);
        //g.drawLine(0, GROUND, BOARD_WIDTH, GROUND);

        player.draw(g, this);
        if (player.getM().isVisible())
            player.getM().draw(g, this);

        enemyWave.draw(g, this);

        for (Guard guard : guards) {
            guard.draw(g);
        }
    }

    void addToFrame(Component component, int x, int y, int width, int height) {
        component.setBounds(x, y, width, height);
        add(component);
    }

    private void animationCycle() {
        if(enemyWave.getNumberOfEnemies() == 0) {
            timer.stopTimer();
            inTheGame = false;
            message = "Blow me down Cap'n " + Variables.USERNAME + 
            ", ye're amazin'. Simply a fantastic maiden voyage fer the " +
            Variables.USERSHIP;
        }

        if(player.isDying()) { 
            
            lives--; //decrement the lives shown
            
            if(lives != 0) {
                bgMusic.playPlayerDeathFX(); //for the player death but can still play sound FX
                player.revive();
            } //when you dont lose, you respawn 
            
            else {
                bgMusic.playGameOverFX(); //for the game over sound FX
                timer.stopTimer();
                inTheGame = false;
                message = "Ye suck Cap'n " + Variables.USERNAME +
                ". The " + Variables.USERSHIP + " WAS a beaut.";
            }
        }

        if(enemyWave.reachedTheGround()) {
            timer.stopTimer();
            inTheGame=false;
            message = "Ye suck Cap'n " + Variables.USERNAME +
                ". The " + Variables.USERSHIP + " WAS a beaut.";
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
        g.drawString("Your score: " + gameScore, (BOARD_WIDTH - fonts.stringWidth(message))/2, BOARD_HEIGHT/2 + 50);
        //Display player time at end of game
        g.drawString("Your time: " + timer.getMinutes() + ":" + timer.getSeconds(), 
                (BOARD_WIDTH - fonts.stringWidth(message))/2, (BOARD_HEIGHT/2) + 25);
        saveHighScore();
    }

    private void saveHighScore () {
            try {
                Scanner fileIn = new Scanner(new File("Saves//savefile.txt"));
                playerName = fileIn.nextLine();                
            } catch (IOException e) {
                System.out.println("Error saving name");
            }
            Map<String, Integer> unsortedMap = new HashMap<String, Integer>();
            unsortedMap.put(playerName, gameScore);
            LeaderboardMap.writeToFile(unsortedMap);
            Map<String, Integer> tmpMap = new HashMap<String, Integer>();
            LeaderboardMap.dumpFromFile(tmpMap);
            Map<String, Integer> sortedMap = LeaderboardMap.sortByValue(tmpMap);
            LeaderboardMap.clearFile();
            LeaderboardMap.writeToFile(sortedMap);
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
