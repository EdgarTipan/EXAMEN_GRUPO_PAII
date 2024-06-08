package controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Bullet;
import model.Enemy;
import model.Hero;
import model.User;
import model.interfaces.Level;
import util.FontUtil;
import util.StarBackground;

import javax.swing.*;

public class Container implements Level {

    private final Hero hero;
    private final List<Enemy> enemies;
    private StarBackground starBackground;
    private final User user;
    private final Font upperPixelFont;
    private final Font pausePixelFont;
    private final Font startScreenFont;
    private final Font startScreenMiniFont;

    private int currentLevel;
    private final int[][] levelConfigurations;
    private Timer enemyMovementTimer;

    public Container() {
        hero = new Hero(3);
        enemies = new ArrayList<>();
        user = new User(0, 100);

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
        startEnemyMovementTimer();
    }

    @Override
    public void setup() {
        enemies.clear();
        int[] config = levelConfigurations[currentLevel - 1];
        int numEnemies = config[0];
        int enemyHealth = config[1];
        int initPosX = 160;
        int initPosY = 100;
        int scale = 1;

        for (int i = 0; i < numEnemies; i++) {
            enemies.add(new Enemy(5, initPosX, initPosY, scale, enemyHealth));
            initPosX += 50 * (scale * 2);
        }
    }

    private void startEnemyMovementTimer() {
        enemyMovementTimer = new Timer(2000, e -> moveEnemiesDown());
        enemyMovementTimer.start();
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
        int y = (int) (height * 0.66);
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
            g.drawString(String.valueOf(user.getScore()), (int) (width / 1.26), 60);

            hero.draw(g, hero);

            for (Enemy enemy : enemies) {
                enemy.draw(g, enemy);
            }
        }

        g.setColor(Color.CYAN.darker());
        g.drawRect((int) (width / 11.6), 40, 130, 20);

        g.setColor(Color.CYAN.brighter());
        g.fillRect((int) (width / 11.3), 42, ((user.getHealth() * 126) / 100), 16);
    }

    @Override
    public void update() {
        updateBullets();
        checkCollisions();
    }

    private void updateBullets() {
        for (Bullet bullet : hero.getBullets()) {
            bullet.move("UP", 15);
        }
    }

    private void checkCollisions() {
        List<Bullet> bullets = hero.getBullets();
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            Iterator<Enemy> enemyIterator = enemies.iterator();
            while (enemyIterator.hasNext()) {
                Enemy enemy = enemyIterator.next();
                if (bullet.getBounds().intersects(enemy.getBounds())) {
                    bullet.onCollision(enemy);
                    enemy.onCollision(bullet);
                    user.setScore(user.getScore() + 5);
                    if (!bullet.isActive()) {
                        bulletIterator.remove();
                    }
                    if (!enemy.isActive()) {
                        enemyIterator.remove();
                    }
                }
            }
        }

        for (Enemy enemy : enemies) {
            if (hero.getBounds().intersects(enemy.getBounds())) {
                hero.onCollision(enemy);
                enemy.onCollision(hero);
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
        hero.shoot(25);
    }

    public void updateBackground() {
        if (starBackground != null) {
            starBackground.update();
        }
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
        starBackground = new StarBackground(width, height, 100);
        starBackground.draw(g, hero);

        g.setColor(Color.GREEN);
        g.setFont(startScreenFont);
        g.drawString("GALAGA", (int) (width / 3.5), (int) (height / 2.3));

        g.setColor(Color.WHITE);
        g.setFont(startScreenMiniFont);
        g.drawString("Presione cualquier tecla para iniciar", (int) (width / 4.25), (int) (height / 1.8));
    }

    public void stopEnemyMovementTimer() {
        if (enemyMovementTimer != null) {
            enemyMovementTimer.stop();
        }
    }

    public void startEnemyMovementTimerAgain() {
        if (enemyMovementTimer != null) {
            enemyMovementTimer.start();
        }
    }

}
