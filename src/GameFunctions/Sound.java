package GameFunctions;

import MainMenu.menuFXMLController;

import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.*;

public class Sound extends Board { 

    private float audioVolume;
    private menuFXMLController menuController;
    private Clip bgclip, shootClip, dieClip, foeDieClip, gameOverClip;
    private FloatControl bgmusicVolume, shootAudioFX, dieAudioFX, foeDieAudioFX, gameOverAudioFX;
    private File bgfile, shootFX, dieFX, foeDieFX, gameOverFX;
    private static boolean firstTime = true;
    private int numFrames;
    private Timer timer = new Timer();
    
    public Sound () throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        menuController = new menuFXMLController();
        audioVolume = menuController.getVolume();

        if (firstTime) {

            bgfile = new File("Assets//Salty_Ditty.wav"); 

            try {
            AudioInputStream music = AudioSystem.getAudioInputStream(bgfile);
            bgclip = AudioSystem.getClip();
            bgclip.open(music);
            bgmusicVolume = (FloatControl) bgclip.getControl(FloatControl.Type.MASTER_GAIN);
            }
            catch (Exception e){
                e.printStackTrace();
            }     
        
            bgmusicVolume.setValue(20f * (float) Math.log10(audioVolume));
            System.out.println("The volume set: " + bgmusicVolume.getValue());
            bgclip.loop(Clip.LOOP_CONTINUOUSLY); //loops the background music
            firstTime = false;
        } 

        //Initalize sound FX audio files and variables
        //============================================
        //Player Shooting FX
        shootFX = new File("Assets//581597__samsterbirdies__gun-cannon.wav");
        try {
            AudioInputStream shoot = AudioSystem.getAudioInputStream(shootFX);
            shootClip = AudioSystem.getClip();
            shootClip.open(shoot);
            shootAudioFX = (FloatControl) shootClip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception e) {
            System.out.println("Something went wrong with getting the shooting FX.");
        }
        shootAudioFX.setValue(20f * (float) Math.log10(audioVolume));
        numFrames = shootClip.getFrameLength();
        shootClip.setLoopPoints(0, numFrames/20);

        //Enemy Death FX
        foeDieFX = new File("Assets//170144__timgormly__8-bit-explosion2.wav");
        try {
            AudioInputStream foeDie = AudioSystem.getAudioInputStream(foeDieFX);
            foeDieClip = AudioSystem.getClip();
            foeDieClip.open(foeDie);
            foeDieAudioFX = (FloatControl) foeDieClip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception e) {
            System.out.println("Something went wrong with getting the enemy death FX.");
        }
        foeDieAudioFX.setValue(20f * (float) Math.log10(audioVolume));

        //Player Death FX with lives remaining
        dieFX = new File("Assets//414346__bykgames__explosion-far.wav");
        try {
            AudioInputStream playerDie = AudioSystem.getAudioInputStream(dieFX);
            dieClip = AudioSystem.getClip();
            dieClip.open(playerDie);
            dieAudioFX = (FloatControl) dieClip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception e) {
            System.out.println("Something went wrong with getting first player death FX.");
        }
        dieAudioFX.setValue(20f * (float) Math.log10(audioVolume));
        numFrames = dieClip.getFrameLength();
        dieClip.setLoopPoints(0, numFrames/3);

        //Player Death FX with no lives remaining (Game Over)
        gameOverFX = new File("Assets//414345__bykgames__explosion-near.wav");
        try {
            AudioInputStream gameDeath = AudioSystem.getAudioInputStream(gameOverFX);
            gameOverClip = AudioSystem.getClip();
            gameOverClip.open(gameDeath);
            gameOverAudioFX = (FloatControl) gameOverClip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception e) {
            System.out.println("Something went wrong with getting the game over FX.");
        }
        gameOverAudioFX.setValue(20f * (float) Math.log10(audioVolume));
    }

    public void playShootFX(){
        shootClip.loop(Clip.LOOP_CONTINUOUSLY);

        timer.schedule(new StopShootFX(), 200);
    }
    
    class StopShootFX extends TimerTask{ 
        public void run(){
            shootClip.stop();
        }
    }

    public void playEnemyDeathFX(){
        
        foeDieClip.loop(Clip.LOOP_CONTINUOUSLY);

        timer.schedule(new StopEnemyFX(), 492); //The sound FX is .492 seconds long
    }
    //Make it to where the FX stops after one loop rather than continuous loops with no end
    class StopEnemyFX extends TimerTask{
        public void run() {
            foeDieClip.stop();
        }
    }

    public void playPlayerDeathFX(){
        dieClip.loop(Clip.LOOP_CONTINUOUSLY);

        timer.schedule(new StopPlayerDeathFX(), 1800); //The actual sound is only just under 2 seconds long
    }

    class StopPlayerDeathFX extends TimerTask{
        public void run(){
            dieClip.stop();
        }
    }

    public void playGameOverFX(){ //Don't need to loop this one as it's only gonna be played once
        timer.cancel();
        shootClip.close();
        foeDieClip.close();
        dieClip.close();
        gameOverClip.start();
    }
}
