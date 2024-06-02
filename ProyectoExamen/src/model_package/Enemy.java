package model_package;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends Personajes {
	
	int[] coord_X;
	int[] coord_Y;

	public Enemy(int randomX, int randomY, int value) {
		super(value);
		coord_X = new int [value];
		coord_Y = new int [value];
		coord_X[0] = randomX;
		coord_X[1] = randomX + 100;
		coord_X[2] = randomX + 100;
		coord_X[3] = randomX + 50;
		coord_X[4] = randomX;
		
		coord_Y[0] = randomY;
		coord_Y[1] = randomY;
		coord_Y[2] = randomY + 50;
		coord_Y[3] = randomY + 25;
		coord_Y[4] = randomY + 50;
	}

	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.fillPolygon(coord_X, coord_Y, 5);
	}

	@Override
	public void moveRight(int var) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveLeft(int var) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveUp(int var) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveDown(int var) {
		for (int i = 0; i < coord_Y.length; i++) {
			coord_Y[i] = coord_Y[i] + var;
		}
	}

	@Override
	public void draw(Graphics graphics, Drawable d) {
		// TODO Auto-generated method stub
		
	}

}
