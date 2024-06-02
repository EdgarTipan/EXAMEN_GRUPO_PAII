package model_package;

public abstract class Personajes implements Drawable, Movable {
	
	public int[] coord_X;
    public int[] coord_Y;
	
	public Personajes(int value) {
		int[] coord_X = new int [value];
		int[] coord_Y = new int [value];
	}

}
