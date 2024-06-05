package model;

import model.interfaces.Collidable;
import model.interfaces.Drawable;
import model.interfaces.Movable;
import model.interfaces.Shootable;

import java.awt.*;

public class Bullet implements Drawable, Movable, Collidable {

	private final int posX;
    private int posY;
	private final int bulletDamage;
	private boolean active = true;

	public Bullet(int x, int y, int bDamage) {
		this.bulletDamage = bDamage;
		this.posX = x;
		this.posY = y;
	}

	public int getBulletDamage() {
		return bulletDamage;
	}

	public int getY() {
		return posY;
	}

	public boolean isActive() {
		return active;
	}

	private void deactivate() {
		this.active = false;
	}

	@Override
	public void draw(Graphics g, Role p) {
		g.setColor(Color.WHITE);
		g.fillOval(posX-5, posY-5, 10, 15);
	}

	@Override
	public void move(String direction, int movSpeed) {
		switch (direction) {

			case "UP":
				if (posY > -15) {
					posY = getY() - movSpeed;
				}
				break;
			case "DOWN":
				if (posY < 800) {
					posY = getY() + movSpeed;
				}
				break;
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(posX, posY, 5, 10);
	}

	@Override
	public void onCollision(Collidable other) {
		if (other instanceof Enemy) {
			this.deactivate();
		}
	}

}


