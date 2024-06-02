package controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import model.Bullet;
import model.Enemy;
import model.Hero;
import util.FontUtil;
import util.StarBackground;

public class Container {

    Hero hero = new Hero(3);
    Bullet bullet = new Bullet();
    List<Enemy> enemies = new ArrayList<Enemy>();
	StarBackground starBackground;

    public Container() {
        int initPosX = 160;
        int initPosY = 100;
        int scale = 1; // Cambiar el factor de escala según sea necesario

        for (int i = 0; i < 5; i++) {
            enemies.add(new Enemy(5, initPosX, initPosY, scale));
            initPosX += 50 * (scale*2); // Incrementar la posición inicial X teniendo en cuenta el factor de escala
        }
    }

    public void drawScreenElements(Graphics g, int width, int height, int numStars) {
        hero.draw(g, hero);
        for (Enemy enemy : enemies) {
            enemy.draw(g, enemy);
        }

        //Linea Roja
        g.setColor(Color.RED.brighter());
        int y = (int) (height * 0.66);
        g.drawLine(0, y, width, y);

        //Textos en rojo de arriba
        Font pixelFont = FontUtil.getPixelFont(36f);
        if (pixelFont != null) {
            g.setFont(pixelFont);
            g.drawString("HIGH SCORE", (int)(width/2.7), 30);
            g.drawString("1 UP", (width/9), 30);
            g.drawString("SCORE", (int)(width/1.3), 30);

            g.setColor(Color.WHITE); // Cambio de color para los textos en blanco
            g.drawString("0", (int)(width/2.15), 60);
            g.drawString("100", (width/9), 60);
            g.drawString("0", (int)(width/1.22), 60);
        } else {
            System.out.println("No se pudo cargar la fuente");
        }

        starBackground = new StarBackground(width,height,numStars);
        starBackground.draw(g);

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


}
