package model;

import model.interfaces.Drawable;
import model.interfaces.Movable;

public abstract class Role implements Drawable, Movable {
	
	protected int[] coord_X;
	protected int[] coord_Y;

	public Role(int value) {
		coord_X = new int [value];
		coord_Y = new int [value];
	}

}
