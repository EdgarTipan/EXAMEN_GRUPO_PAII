package controller_package;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model_package.Bullet;
import model_package.Enemy;
import model_package.Hero;

public class Container {

	final int SCREEN_WIDTH = 700;
	final int SCREEN_HEIGHT = 200;
	Hero hero = new Hero(3);
	List<Enemy> enemies = new ArrayList<Enemy>();
	Random r = new Random();

	public Container() {
		enemies.add(new Enemy(r.nextInt(SCREEN_WIDTH), r.nextInt(200),5));
		enemies.add(new Enemy(r.nextInt(SCREEN_WIDTH), r.nextInt(200),5));
		enemies.add(new Enemy(r.nextInt(SCREEN_WIDTH), r.nextInt(200),5));
		enemies.add(new Enemy(r.nextInt(SCREEN_WIDTH), r.nextInt(200),5));
		enemies.add(new Enemy(r.nextInt(SCREEN_WIDTH), r.nextInt(200),5));
	}

	public void draw(Graphics g) {
		hero.draw(g);
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
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
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).moveDown(var);
		}
	}

	public void drawShoot(Graphics g) {
		new Bullet().draw(g, hero);
		new Bullet().moveUp(10);;
	}

}
