package model;

import model.interfaces.Collidable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Hero extends Role {

    // Variables de la clase y constructor:

    private final List<Bullet> bullets = new ArrayList<>();
    private long lastShootTime = 0;
    private static final int SHOOT_COOLDOWN = 200;
    private int score;
    private int health;

    public Hero(int value, int score, int health) {
        super(value);
        coord_X = new int[]{325, 375, 425};
        coord_Y = new int[]{500, 450, 500};
        this.score = score;
        this.health = health;
    }

    // Getters, Setters y Metodos adicionales de la clase:

    public int getScore() {
        return score;
    }

    public int getHealth() {
        return health;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Bullet> getBullets() {
        return bullets;
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

    public boolean isDead() {
        return this.health <= 0;
    }

    // Metodos implementados:

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

    @Override
    public void draw(Graphics graphics, Role p) {
        graphics.setColor(Color.BLUE);
        graphics.fillPolygon(p.coord_X, p.coord_Y, 3);
    }

    @Override
    public void shoot(int shootingCoord, int bulletDamageValue) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShootTime >= SHOOT_COOLDOWN) {
            int bulletX = getCoordX(shootingCoord); // Coordenada X central de la nave
            int bulletY = getCoordY(shootingCoord); // Coordenada Y central de la nave
            bullets.add(new Bullet(bulletX, bulletY, bulletDamageValue));
            lastShootTime = currentTime;
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((coord_X[0] + 25),
                coord_Y[0] - 25,
                50,
                25);
    }

    @Override
    public void onCollision(Collidable other) {
        if (other instanceof Bullet bullet) {
            this.health -= bullet.getBulletDamage();
            bullet.onCollision(this); // Desactivar la bala
        }
    }
}
