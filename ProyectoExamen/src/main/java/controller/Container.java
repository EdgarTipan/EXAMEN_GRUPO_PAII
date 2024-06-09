package controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Bullet;
import model.Enemy;
import model.Hero;
import model.interfaces.Level;
import util.FontUtil;
import util.StarBackground;

import javax.swing.*;

public class Container implements Level {

    private final Hero hero;
    private final List<Enemy> enemies;
    private final StarBackground starBackground;
    private final Font upperPixelFont;
    private final Font pausePixelFont;
    private final Font startScreenFont;
    private final Font startScreenMiniFont;

    private int currentLevel;
    private int y = 700;
    private final int[][] levelConfigurations;
    private boolean isGameOver = false;
    private Timer enemyMovementTimer;
    private Timer enemyShootingTimer;

    public Container() {
        hero = new Hero(3,0,100);
        enemies = new ArrayList<>();

        upperPixelFont = FontUtil.getPixelFont(36f);
        pausePixelFont = FontUtil.getPixelFont(50f);
        startScreenFont = FontUtil.getPixelFont(100f);
        startScreenMiniFont = FontUtil.getPixelFont(20f);

        currentLevel = 1;
        levelConfigurations = new int[][]{
                {5, 20},
                {5, 60},
                {1, 100}
        };

        setup();
        starBackground = new StarBackground(800, 600, 100); // Inicializar el fondo de estrellas
    }

    @Override
    public void setup() {
        enemies.clear();
        int[] config = levelConfigurations[currentLevel - 1];
        int numEnemies = config[0];
        int enemyHealth = config[1];
        int initPosX = 160;
        int initPosY = 100;
        int scale;

        if(currentLevel <3){
            scale = 1;
        }else{
            scale = 2;
        }

        for (int i = 0; i < numEnemies; i++) {
            enemies.add(new Enemy(5, initPosX, initPosY, scale, enemyHealth));
            initPosX += 50 * (scale * 2);
        }
    }

    public void startEnemyMovementTimer() {
        if (enemyMovementTimer == null) {
            enemyMovementTimer = new Timer(1500, e -> moveEnemiesDown());
        }
        enemyMovementTimer.start();
    }

    public void stopEnemyMovementTimer() {
        if (enemyMovementTimer != null) {
            enemyMovementTimer.stop();
        }
    }

    private void moveEnemiesDown() {
        for (Enemy enemy : enemies) {
            enemy.move("DOWN", 20);
        }
    }

    @Override
    public void draw(Graphics g, int width, int height) {
        if (starBackground != null) {
            starBackground.draw(g, null);
        }

        g.setColor(Color.RED.brighter());
        y = (int) (height * 0.66);
        g.drawLine(0, y, width, y);

        for (Bullet bullet : hero.getBullets()) {
            bullet.draw(g, hero);
        }

        g.setColor(Color.RED);
        if (upperPixelFont != null) {
            g.setFont(upperPixelFont);
            g.drawString("HIGH SCORE", (int) (width / 2.7), 30);
            g.drawString("LEVEL " + currentLevel, (width / 11), 30);
            g.drawString("SCORE", (int) (width / 1.35), 30);

            g.setColor(Color.WHITE);
            g.drawString("0", (int) (width / 2.15), 60);
            g.drawString(String.valueOf(hero.getScore()), (int) (width / 1.26), 60);

            for (Enemy enemy : enemies) {
                enemy.draw(g, enemy);
            }

            hero.draw(g, hero);

        }

        g.setColor(Color.CYAN.darker());
        g.drawRect((int) (width / 11.6), 40, 130, 20);

        g.setColor(Color.CYAN.brighter());
        g.fillRect((int) (width / 11.3), 42, ((hero.getHealth() * 126) / 100), 16);

        if (isGameOver) {
            drawGameOverScreen(g, width, height);
        }
    }

    @Override
    public void update() {
        if (!isGameOver) {
            updateBullets();
            checkCollisions();
            checkEnemyPassedLine();
            checkGameOver();
        }
    }

    private void checkEnemyPassedLine() {
        for (Enemy enemy : enemies) {
            if (enemy.getCoordY(1)-10 > y) {
                isGameOver = true;
                stopGame();
                break;
            }
        }
    }

    private void checkGameOver() {
        if (hero.isDead()) {
            isGameOver = true;
            stopGame();
        }
    }

    private void stopGame() {
        stopEnemyMovementTimer();
        if (enemyShootingTimer != null) {
            enemyShootingTimer.stop();
        }
        // Detener cualquier otro temporizador o lógica del juego que necesite detenerse
    }

    public void startEnemyShootingTimer() {
        enemyShootingTimer = new Timer(3000, e -> {
            for (Enemy enemy : enemies) {
                enemy.shoot(currentLevel, 10); // Nivel actual y daño de bala
            }
        });
        enemyShootingTimer.start();
    }

    private void updateBullets() {
        // Actualizar y eliminar las balas del héroe desactivadas
        hero.getBullets().removeIf(bullet -> {
            bullet.move("UP", 15);
            return !bullet.isActive();
        });

        // Actualizar y eliminar las balas de los enemigos desactivadas
        for (Enemy enemy : enemies) {
            enemy.getBullets().removeIf(bullet -> {
                bullet.move("DOWN", 10);
                return !bullet.isActive();
            });
        }
    }


    private void checkCollisions() {
        // Obtener las balas del héroe y los enemigos
        List<Bullet> heroBullets = hero.getBullets();
        List<Bullet> enemyBullets = new ArrayList<>();
        for (Enemy enemy : enemies) {
            enemyBullets.addAll(enemy.getBullets());
        }

        // Verificar colisiones entre balas del héroe y enemigos
        Iterator<Bullet> heroBulletIterator = heroBullets.iterator();
        while (heroBulletIterator.hasNext()) {
            Bullet heroBullet = heroBulletIterator.next();
            Iterator<Enemy> enemyIterator = enemies.iterator();
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();
                if (heroBullet.getBounds().intersects(enemy.getBounds())) {
                    heroBullet.onCollision(enemy);
                    enemy.onCollision(heroBullet);
                    if (!heroBullet.isActive()) {
                        heroBulletIterator.remove();
                    }
                    if (!enemy.isActive()) {
                        enemyIterator.remove();
                        hero.setScore(hero.getScore() + 5);
                    }
                }
            }
        }

        // Verificar colisiones entre balas de los enemigos y el héroe
        Iterator<Bullet> enemyBulletIterator = enemyBullets.iterator();
        while (enemyBulletIterator.hasNext()) {
            Bullet enemyBullet = enemyBulletIterator.next();
            if (enemyBullet.getBounds().intersects(hero.getBounds())) {
                enemyBullet.onCollision(hero);
                hero.onCollision(enemyBullet);
                if (!enemyBullet.isActive()) {
                    enemyBulletIterator.remove();
                }
            }
        }
    }


    @Override
    public boolean isLevelCompleted() {
        return enemies.isEmpty();
    }

    @Override
    public void advanceToNextLevel() {
        if (currentLevel < levelConfigurations.length) {
            currentLevel++;
            setup();
        } else {
            System.out.println("All levels completed!");
        }
    }

    public void heroMove(String direction, int var) {
        hero.move(direction, var);
    }

    public void heroShootBullet() {
        hero.shoot(1,25);
    }

    public void updateBackground() {
        if (starBackground != null) {
            starBackground.update();
        }
    }

    public void drawGameOverScreen(Graphics g, int width, int height) {
        Color overlayColor = new Color(0, 0, 0, 200);
        g.setColor(overlayColor);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.RED);
        g.setFont(pausePixelFont);
        g.drawString("GAME OVER", (int) (width / 3.5), (height / 2));
    }

    public void drawPauseScreen(Graphics g, int width, int height) {
        Color overlayColor = new Color(0, 0, 0, 150);
        g.setColor(overlayColor);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.WHITE);
        g.setFont(pausePixelFont);
        g.drawString("PAUSE", (int) (width / 2.5), (height / 2));
    }

    public void drawStartScreen(Graphics g, int width, int height) {
        if (starBackground != null) {
            starBackground.draw(g, hero);
        }

        g.setColor(Color.GREEN);
        g.setFont(startScreenFont);
        g.drawString("GALAGA", (int) (width / 3.5), (int) (height / 2.3));

        g.setColor(Color.WHITE);
        g.setFont(startScreenMiniFont);
        g.drawString("Presione cualquier tecla para iniciar", (int) (width / 4.25), (int) (height / 1.8));
    }


    public void stopEnemyShootingTimer() {
        if (enemyShootingTimer != null) {
            enemyShootingTimer.stop();
        }
    }

}
