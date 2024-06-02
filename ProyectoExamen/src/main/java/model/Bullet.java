package model;

import model.interfaces.Drawable;
import model.interfaces.Movable;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet implements Drawable, Movable {
	int[] coord_Y = {0,1,2,3,4};

	@Override
	public void move(String direction, int movSpeed) {
		switch (direction) {
			case "DOWN":
				for (int i = 0; i < coord_Y.length; i++) {
					coord_Y[i] = coord_Y[i] + movSpeed;
				}
				break;
			case "UP":
				for (int i = 0; i < coord_Y.length; i++) {
					coord_Y[i] = coord_Y[i] - movSpeed;
				}
				break;
		}
	}

	@Override
	public void draw(Graphics graphics, Role p) {
		graphics.setColor(Color.WHITE);
		graphics.fillOval(p.coord_X[1]-4, p.coord_Y[1]-10,10,10);
	}
}
