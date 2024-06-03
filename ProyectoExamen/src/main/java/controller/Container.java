package controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import model.Bullet;
import model.Enemy;
import model.Hero;
import model.User;
import util.FontUtil;
import util.StarBackground;

public class Container {

    Hero hero = new Hero(3);
    Bullet bullet = new Bullet();
    List<Enemy> enemies = new ArrayList<Enemy>();
	StarBackground starBackground;
    int userMaxHealth = 100;
    User user = new User(0,userMaxHealth);

    Font upperPixelFont = FontUtil.getPixelFont(36f);
    Font pausePixelFont = FontUtil.getPixelFont(50f);

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
        starBackground = new StarBackground(width,height,numStars);
        starBackground.draw(g);

        //Linea Roja
        g.setColor(Color.RED.brighter());
        int y = (int) (height * 0.66);
        g.drawLine(0, y, width, y);

        hero.draw(g, hero);
        for (Enemy enemy : enemies) {
            enemy.draw(g, enemy);
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
        } else {
            System.out.println("No se pudo cargar la fuente");
        }

        //Barra de vida
        g.setColor(Color.CYAN.darker());
        g.drawRect((int)(width/11.6), 40, 130, 20);

        g.setColor(Color.CYAN.brighter());
        user.setHealth(50);
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

    public void drawShoot(Graphics g) {
        bullet.draw(g, hero);
        bullet.move("UP", 5);
    }


    public void drawPauseScreen(Graphics g, int width, int height) {
        Color overlayColor = new Color(0, 0, 0, 150); // Color negro semi-transparente
        g.setColor(overlayColor);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.WHITE);
        g.setFont(pausePixelFont);
        g.drawString("PAUSE", (int)(width/2.5), (height/2));
    }


}
