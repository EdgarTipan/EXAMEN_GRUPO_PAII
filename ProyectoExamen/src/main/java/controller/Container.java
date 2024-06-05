package controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Bullet;
import model.Enemy;
import model.Hero;
import model.User;
import util.FontUtil;
import util.StarBackground;

public class Container {

    Hero hero = new Hero(3);
    List<Enemy> enemies = new ArrayList<Enemy>();
	StarBackground starBackground;
    int userMaxHealth = 100;
    User user = new User(0,userMaxHealth);

    Font upperPixelFont = FontUtil.getPixelFont(36f);
    Font pausePixelFont = FontUtil.getPixelFont(50f);
    Font startScreenFont = FontUtil.getPixelFont(100f);
    Font startScreenMiniFont = FontUtil.getPixelFont(20f);

    public Container() {
        int initPosX = 160;
        int initPosY = 100;
        int scale = 1; // Factor de escala

        for (int i = 0; i < 5; i++) {
            enemies.add(new Enemy(5, initPosX, initPosY, scale));
            initPosX += 50 * (scale*2);
        }
    }

    public void drawScreenElements(Graphics g, int width, int height, int numStars) {
        starBackground.draw(g, null);
        //Linea Roja
        g.setColor(Color.RED.brighter());
        int y = (int) (height * 0.66);
        g.drawLine(0, y, width, y);

        for (Bullet bullet : hero.getBullets()) {
            bullet.draw(g,hero);
        }

        //Textos en rojo de arriba
        g.setColor(Color.RED);
        if (upperPixelFont != null) {
            g.setFont(upperPixelFont);
            g.drawString("HIGH SCORE", (int)(width/2.7), 30);
            g.drawString("LEVEL 1", (width/11), 30);
            g.drawString("SCORE", (int)(width/1.35), 30);

            g.setColor(Color.WHITE); // Cambio de color para los textos en blanco
            g.drawString("0", (int)(width/2.15), 60); //Contador del highscore
            g.drawString(String.valueOf(user.getScore()), (int)(width/1.26), 60); //Contador del score actual

            hero.draw(g, hero);

            for (Enemy enemy : enemies) {
                enemy.draw(g, enemy);
            }
        } else {
            System.out.println("No se pudo cargar la fuente");
        }

        //Barra de vida
        g.setColor(Color.CYAN.darker());
        g.drawRect((int)(width/11.6), 40, 130, 20);

        g.setColor(Color.CYAN.brighter());
        g.fillRect((int)(width/11.3), 42, ((user.getHealth() * 126) / userMaxHealth), 16);

    }

    public void heroMove(String direction, int var) {
        hero.move(direction, var);
    }

    public void enemyMove(String direction, int var) {
        for (Enemy enemy : enemies) {
            enemy.move(direction, var);
        }
    }

    public void heroShootBullet(){
        hero.shoot();
    }

    private void updateBullets() {
        for (Bullet bullet : hero.getBullets()) {
            bullet.move("UP", 15);
        }
    }

    public void updateBackground() {
        if (starBackground != null) {
            starBackground.update();
        }
    }

    public void drawPauseScreen(Graphics g, int width, int height) {
        Color overlayColor = new Color(0, 0, 0, 150); // Color negro semi-transparente
        g.setColor(overlayColor);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.WHITE);
        g.setFont(pausePixelFont);
        g.drawString("PAUSE", (int)(width/2.5), (height/2));
    }

    public void drawStartScreen(Graphics g, int width, int height) {

        starBackground = new StarBackground(width,height,100);
        starBackground.draw(g,hero);

        g.setColor(Color.GREEN);
        g.setFont(startScreenFont);
        g.drawString("GALAGA", (int)(width/3.5), (int)(height/2.3));

        g.setColor(Color.WHITE);
        g.setFont(startScreenMiniFont);
        g.drawString("Presione    cualquier    tecla    para   iniciar", (int)(width/4.25), (int)(height/1.8));
    }

    public void updateGame() {
        updateBullets();
        checkCollisions();
    }

    private void checkCollisions() {
        List<Bullet> bullets = hero.getBullets();

        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            for (Enemy enemy : enemies) {
                if (bullet.getBounds().intersects(enemy.getBounds())) {
                    bullet.onCollision(enemy);
                    enemy.onCollision(bullet);
                    user.setScore(user.getScore()+5);

                    if (!bullet.isActive()) {
                        bulletIterator.remove();
                    }
/*                    if (!((Enemy) enemy).isActive()) {
                        enemyIterator.remove();
                    }*/
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

}
