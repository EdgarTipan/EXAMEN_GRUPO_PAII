package util;

import model.Role;
import model.interfaces.Drawable;

import java.awt.*;
import java.util.Random;

public class StarBackground implements Drawable {
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

        Random rand = new Random();
        for (int i = 0; i < numStars; i++) {
            starX[i] = rand.nextInt(width);
            starY[i] = rand.nextInt(height);
            starSize[i] = rand.nextInt(3) + 1; // Tamaño de estrella entre 1 y 3
        }
    }

    @Override
    public void draw(Graphics g, Role p) {
        g.setColor(Color.GRAY);
        for (int i = 0; i < numStars; i++) {
            g.fillRect(starX[i], starY[i], starSize[i], starSize[i]);
        }
    }

    public void update() {
        for (int i = 0; i < numStars; i++) {
            starY[i] -= 1; // Mover hacia arriba
            if (starY[i] < 0) {
                starY[i] = height; // Reciclar la estrella hacia la parte inferior
                starX[i] = new Random().nextInt(800); // Nueva posición horizontal aleatoria
            }
        }
    }
}
