package model;

import model.interfaces.Collidable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Hero extends Role {

    private final List<Bullet> bullets = new ArrayList<>();
    private long lastShootTime = 0;
    private static final int SHOOT_COOLDOWN = 200;

    public Hero(int value) {
        super(value);
        coord_X = new int[]{350, 400, 450};
        coord_Y = new int[]{500, 450, 500};
    }

    @Override
    public void move(String direction, int movSpeed) {
        switch (direction) {
            case "LEFT":
                if (canMove(-movSpeed)) {
                    for (int i = 0; i < coord_X.length; i++) {
                        coord_X[i] -= movSpeed;
                    }
                }
                break;
            case "RIGHT":
                if (canMove(movSpeed)) {
                    for (int i = 0; i < coord_X.length; i++) {
                        coord_X[i] += movSpeed;
                    }
                }
                break;
        }
    }

    private boolean canMove(int deltaX) {
        for (int coordX : coord_X) {
            int newX = coordX + deltaX;
            if (newX < 0 || newX > 790) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void draw(Graphics graphics, Role p) {
        graphics.setColor(Color.BLUE);
        graphics.fillPolygon(p.coord_X, p.coord_Y, 3);
    }

    @Override
    public void shoot(int bulletDamageValue) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShootTime >= SHOOT_COOLDOWN) {
            int bulletX = getCoordX(1); // Coordenada X central de la nave
            int bulletY = getCoordY(1); // Coordenada Y central de la nave
            bullets.add(new Bullet(bulletX, bulletY, bulletDamageValue));
            lastShootTime = currentTime;
        }
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(coord_X[0], coord_Y[0], coord_X[2] - coord_X[0], coord_Y[1] - coord_Y[0]);
    }

    @Override
    public void onCollision(Collidable other) {
        if (other instanceof Enemy) {
            System.out.println("Hero ha colisionado con un Enemy!");
            // Implementa la lógica deseada, como reducir la salud del Hero o del Enemy
        }
    }

}
