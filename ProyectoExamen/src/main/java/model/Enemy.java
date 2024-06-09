package model;

import model.interfaces.Collidable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Enemy extends Role {

    private final List<Bullet> bullets = new ArrayList<>();

    private int enemyHealth;
    private boolean active = true;

    public Enemy(int value, int initPosX, int initPosY, int scale, int enemyHealth) {
        super(value);
        this.enemyHealth = enemyHealth;
        coord_X = new int[value];
        coord_Y = new int[value];

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
        if (direction.equals("DOWN")) {
            for (int i = 0; i < coord_Y.length; i++) {
                coord_Y[i] = coord_Y[i] + movSpeed;
            }
        }
    }

    public boolean isActive() {
        return active;
    }

    private void deactivate() {
        this.active = false;
    }

    @Override
    public void draw(Graphics graphics, Role p) {
        graphics.setColor(Color.GREEN);
        graphics.fillPolygon(p.coord_X, p.coord_Y, 5);
        for (Bullet bullet : bullets) {
            bullet.draw(graphics, this);
        }
    }

    @Override
    public void shoot(int shootingCoord, int bulletDamageValue) {
        switch (shootingCoord) {
            case 1:
                bullets.add(new Bullet(this.coord_X[2], this.coord_Y[2], bulletDamageValue)); // Dispara desde el centro
                break;
            case 2:
                bullets.add(new Bullet(this.coord_X[0], this.coord_Y[0], bulletDamageValue)); // Dispara desde la izquierda
                bullets.add(new Bullet(this.coord_X[4], this.coord_Y[4], bulletDamageValue)); // Dispara desde la derecha
                break;
            case 3:
                bullets.add(new Bullet(this.coord_X[2], this.coord_Y[2], bulletDamageValue)); // Dispara desde el centro
                bullets.add(new Bullet(this.coord_X[0], this.coord_Y[0], bulletDamageValue)); // Dispara desde la izquierda
                bullets.add(new Bullet(this.coord_X[4], this.coord_Y[4], bulletDamageValue)); // Dispara desde la derecha
                break;
        }
    }


    public List<Bullet> getBullets() {
        return bullets;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(coord_X[0], coord_Y[0], coord_X[4] - coord_X[0], coord_Y[2] - coord_Y[0]);
    }

    @Override
    public void onCollision(Collidable other) {
        if (other instanceof Bullet bullet) {
            this.enemyHealth -= bullet.getBulletDamage();
            bullet.onCollision(this); // Desactivar la bala
            if (this.enemyHealth <= 0) {
                this.deactivate();
            }
        }
    }

}
