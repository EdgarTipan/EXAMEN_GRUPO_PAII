package model;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends Role {

	public Enemy(int value, int initPosX, int initPosY, int scale) {
		super(value);
		int numPoints = 5;
		coord_X = new int[numPoints];
		coord_Y = new int[numPoints];

		coord_X[0] = initPosX;
		coord_Y[0] = initPosY;

		coord_X[1] = initPosX;
		coord_Y[1] = initPosY + 50 * scale;

		coord_X[2] = initPosX + 25 * scale;
		coord_Y[2] = initPosY + 25 * scale;

		coord_X[3] = initPosX + 50 * scale;
		coord_Y[3] = initPosY + 50 * scale;

		coord_X[4] = initPosX + 50 * scale;
		coord_Y[4] = initPosY;
	}

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
		graphics.setColor(Color.GREEN);
		graphics.fillPolygon(p.coord_X, p.coord_Y, 5);
	}
}
