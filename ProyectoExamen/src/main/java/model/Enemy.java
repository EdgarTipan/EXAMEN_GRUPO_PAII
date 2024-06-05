package model;

import model.interfaces.Collidable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Enemy extends Role {

    private final List<Bullet> bullets = new ArrayList<>();

    public Enemy(int value, int initPosX, int initPosY, int scale) {
        super(value);
        int numPoints = 5;
        coord_X = new int[numPoints];
        coord_Y = new int[numPoints];

        coord_X[0] = initPosX;
        coord_Y[0] = initPosY;

        coord_X[1] = initPosX;
        coord_Y[1] = initPosY + 50 * scale;

        coord_X[2] = initPosX + 25 * scale;
        coord_Y[2] = initPosY + 25 * scale;

        coord_X[3] = initPosX + 50 * scale;
        coord_Y[3] = initPosY + 50 * scale;

        coord_X[4] = initPosX + 50 * scale;
        coord_Y[4] = initPosY;
    }

    @Override
    public void move(String direction, int movSpeed) {
        switch (direction) {
            case "DOWN":
                for (int i = 0; i < coord_Y.length; i++) {
                    coord_Y[i] = coord_Y[i] + movSpeed;
                }
                break;
            case "UP":
                for (int i = 0; i < coord_Y.length; i++) {
                    coord_Y[i] = coord_Y[i] - movSpeed;
                }
                break;

            case "COLLISION":
                for (int i = 0; i < coord_Y.length; i++) {
                    coord_Y[i] = coord_Y[i] - 100;
                }
                break;
        }
    }

    @Override
    public void draw(Graphics graphics, Role p) {
        graphics.setColor(Color.GREEN);
        graphics.fillPolygon(p.coord_X, p.coord_Y, 5);
    }

    @Override
    public void shoot() {
        int bulletX = getCoordX(1); // Coordenada X central de la nave
        int bulletY = getCoordY(1); // Coordenada Y central de la nave
        bullets.add(new Bullet(bulletX, bulletY));
    }

    public List<Bullet> getBullets() {
		return bullets;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(coord_X[0], coord_Y[0], coord_X[4]-coord_X[0], coord_Y[2]-coord_Y[0]);
    }

    @Override
    public void onCollision(Collidable other) {
        if (other instanceof Bullet) {
            // Lógica cuando el Enemy colisiona con una Bullet
            System.out.println("Enemy ha sido golpeado por una Bullet!");
            // Implementa la lógica deseada, como eliminar el Enemy o la Bullet
        }
    }
}
