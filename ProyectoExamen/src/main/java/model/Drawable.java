package model;

import java.awt.Graphics;

public interface Drawable {
	
	public void draw(Graphics graphics);
	public void draw(Graphics graphics, Drawable d);

}
