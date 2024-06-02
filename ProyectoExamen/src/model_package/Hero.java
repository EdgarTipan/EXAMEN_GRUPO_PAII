package model_package;

import java.awt.Color;
import java.awt.Graphics;

public class Hero extends Personajes{

	public Hero(int value) {
		super(value);
		coord_X = new int[]{400, 450, 500};
        coord_Y = new int[]{400, 350, 400};
	}

	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(Color.BLUE);
		graphics.fillPolygon(coord_X, coord_Y, 3);
	}


	@Override
	public void moveRight(int var) {
		for (int i = 0; i < coord_X.length; i++) {
			coord_X[i] = coord_X[i] + var;
		}
	}

	@Override
	public void moveLeft(int var) {
		for (int i = 0; i < coord_X.length; i++) {
			coord_X[i] = coord_X[i] - var;
		}
	}

	@Override
	public void moveUp(int var) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveDown(int var) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void draw(Graphics graphics, Drawable d) {
		// TODO Auto-generated method stub
		
	}
	
	

}
