package com.example.galaga.galaga.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Bullet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer posX;
    private Integer posY;
    private Integer bulletDamage;
    private Boolean active;
    @ManyToOne
    private Hero hero;
    @ManyToOne
    private Enemy enemy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPosX() {
        return posX;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    public Integer getBulletDamage() {
        return bulletDamage;
    }

    public void setBulletDamage(Integer bulletDamage) {
        this.bulletDamage = bulletDamage;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public String toString() {
        return "Bullet{" +
                "id=" + id +
                ", posX=" + posX +
                ", posY=" + posY +
                ", bulletDamage=" + bulletDamage +
                ", active=" + active +
                ", hero=" + hero +
                ", enemy=" + enemy +
                '}';
    }
}