package CharacterSprites;

import GameFunctions.Board;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import static GameFunctions.Variables.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class EnemyMovement {

    private List<Enemy> enemies;
    private Integer numberOfEnemies;
    private int enemySpeed;

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Integer getNumberOfEnemies() {
        return numberOfEnemies;
    }

    public void decreaseNumberOfEnemies() {
        numberOfEnemies--;
    }

    public EnemyMovement() {
        enemies = new ArrayList<>();
        for(int i=0; i<4; i++) {
            for (int j = 0; j < 8; j++) {
                enemies.add(new Enemy(ENEMY_X + 32 * j, ENEMY_Y + 32 * i));
            }
        }
        numberOfEnemies=32;
        enemySpeed=1;
    }

    public void draw(Graphics g, Board board) {
        for (Enemy enemy : enemies) {
            if (enemy.alive)
                enemy.draw(g, board);
            if (enemy.bomb.alive)
                enemy.bomb.draw(g, board);
        }
    }

    public boolean reachedTheGround() {
        for(Enemy enemy: enemies) {
            if (enemy.alive && enemy.y + enemy.height > GUARD_POSY) {
                return true;
            }
        }
        return false;
    }

    public void fixStatus() {
        for(Enemy enemy : enemies) {
            if(enemy.dead) {
                enemy.setAlmostDied(true);
                enemy.setDying(false);
            }
            else if(enemy.almostDied) {
                enemy.die();
                enemy.setAlmostDied(false);
            }
            else if(enemy.alive)
                enemy.move();
        }
    }

    public void bombMove() {
        for(Enemy enemy: enemies) {
            if(enemy.bomb.alive) {
                enemy.bomb.move();
            }
        }
    }

    public void shooting() {
        for(Enemy enemy: enemies) {
            enemy.tryToShoot();
        }
    }

    public void accelerateIfNeeded() {
        boolean a = false;

        if(numberOfEnemies == 24) { //increases speed as the enemies lower
            enemySpeed = 4;
            a = true;
        }

        if(numberOfEnemies==12) {
            enemySpeed = 5;
            a = true;
        }

        if(a) {
            for (Enemy enemy : enemies) {
                if (enemy.dx > 0) enemy.dx = enemySpeed;
                else enemy.dx = -enemySpeed;
            }
        }
    }

    public void turnAroundIfHitTheWall() {
        for(Enemy enemy: enemies) {
            if(enemy.x > BOARD_WIDTH-ENEMY_WIDTH) {
                for(Enemy enemyReversed : enemies) {
                    enemyReversed.dx = -enemySpeed;
                    enemyReversed.y += 15;
                }
                return;
            }

            if(enemy.x < 0) {
                for(Enemy enemyReversed : enemies) {
                    enemyReversed.dx = enemySpeed;
                    enemyReversed.y += 15;
                }
                return;
            }
        }
    }
}
