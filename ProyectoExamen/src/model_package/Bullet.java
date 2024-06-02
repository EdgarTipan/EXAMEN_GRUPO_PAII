package model_package;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet implements Drawable, Movable{
	int[] coord_Y = {0,1,2,3,4};
	 
	@Override
	public void draw(Graphics graphics, Drawable d) {
		graphics.setColor(Color.WHITE);
		graphics.fillOval(((Personajes)d).coord_X[1]-4, ((Personajes)d).coord_Y[1]-10,10,10);
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
		for (int i = 0; i < coord_Y.length; i++) {
			coord_Y[i] = coord_Y[i] - var;
		}
		
	}

	@Override
	public void moveDown(int var) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void draw(Graphics graphics) {
		// TODO Auto-generated method stub
		
	}

}
