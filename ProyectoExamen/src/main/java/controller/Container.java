package controller;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Bullet;
import model.Enemy;
import model.Hero;

public class Container {

	final int SCREEN_WIDTH = 700;
	final int SCREEN_HEIGHT = 300;
	Hero hero = new Hero(3);
	List<Enemy> enemies = new ArrayList<Enemy>();
	Random r = new Random();

	public Container() {
		for (int i = 0; i < 5; i++) {
			enemies.add(new Enemy(r.nextInt(SCREEN_WIDTH), r.nextInt(200), 5));
		}
	}

	public void draw(Graphics g) {
		hero.draw(g);
		for (Enemy enemy : enemies) {
			enemy.draw(g);
		}

	}

	public void moveRight(int var) {
		hero.moveRight(var);
	}

	public void moveLeft(int var) {
		hero.moveLeft(var);
	}

	public void moveUp(int var) {
		// TODO Auto-generated method stub

	}

	public void moveDown(int var) {
		for (Enemy enemy : enemies) {
			enemy.moveDown(var);
		}
	}

	public void drawShoot(Graphics g) {
		new Bullet().draw(g, hero);
		new Bullet().moveUp(10);;
	}

}
