package util;

import java.awt.*;
import java.util.Random;

public class StarBackground {
    private final int numStars;
    private final int[] starX;
    private final int[] starY;
    private final int[] starSize;

    public StarBackground(int width, int height, int numStars) {
        this.numStars = numStars;
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


    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        for (int i = 0; i < numStars; i++) {
            g.fillRect(starX[i], starY[i], starSize[i], starSize[i]);
        }
    }
}
