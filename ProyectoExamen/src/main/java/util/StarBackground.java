package util;

import model.Role;
import model.interfaces.Drawable;

import java.awt.*;
import java.util.Random;

public class StarBackground implements Drawable {

    // Variables de la clase y constructor:

    private final int numStars;
    private final int[] starX;
    private final int[] starY;
    private final int[] starSize;
    private final int height;

    public StarBackground(int width, int height, int numStars) {
        this.numStars = numStars;
        this.height = height;
        this.starX = new int[numStars];
        this.starY = new int[numStars];
        this.starSize = new int[numStars];

        // Generamos estrellas de tamaños aleatorios

        Random rand = new Random();
        for (int i = 0; i < numStars; i++) {
            starX[i] = rand.nextInt(width);
            starY[i] = rand.nextInt(height);
            starSize[i] = rand.nextInt(3) + 1;
        }
    }

    // Getters, Setters y Metodos adicionales de la clase:

   // Este metodo mueve el fondo para dar la ilusion de que es infinito.
    // Mueve las estrellas hasta el limite de la pantalla, las reusa otorgandoles una nueva posicion
    // y las vuelve a mover.

    public void update() {
        for (int i = 0; i < numStars; i++) {
            starY[i] -= 1;
            if (starY[i] < 0) {
                starY[i] = height;
                starX[i] = new Random().nextInt(800);
            }
        }
    }

    // Metodos implementados:

    @Override
    public void draw(Graphics g, Role p) {
        g.setColor(Color.GRAY);
        for (int i = 0; i < numStars; i++) {
            g.fillRect(starX[i], starY[i], starSize[i], starSize[i]);
        }
    }
}
