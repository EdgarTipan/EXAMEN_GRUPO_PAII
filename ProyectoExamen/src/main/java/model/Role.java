package model;

import model.interfaces.Collidable;
import model.interfaces.Drawable;
import model.interfaces.Movable;
import model.interfaces.Shootable;

public abstract class Role implements Drawable, Movable, Shootable, Collidable {
	
	protected int[] coord_X;
	protected int[] coord_Y;

	public Role(int value) {
		coord_X = new int [value];
		coord_Y = new int [value];
	}

	public int getCoordX(int index) {
		return coord_X[index];
	}

	public int getCoordY(int index) {
		return coord_Y[index];
	}

}
