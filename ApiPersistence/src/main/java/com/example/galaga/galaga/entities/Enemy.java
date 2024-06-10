package com.example.galaga.galaga.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Enemy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer enemyHealth;
    private Boolean active;
    @ElementCollection
    private List<Integer> coord_X;
    @ElementCollection
    private List<Integer> coord_Y;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Bullet> bullets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEnemyHealth() {
        return enemyHealth;
    }

    public void setEnemyHealth(Integer enemyHealth) {
        this.enemyHealth = enemyHealth;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Integer> getCoord_X() {
        return coord_X;
    }

    public void setCoord_X(List<Integer> coord_X) {
        this.coord_X = coord_X;
    }

    public List<Integer> getCoord_Y() {
        return coord_Y;
    }

    public void setCoord_Y(List<Integer> coord_Y) {
        this.coord_Y = coord_Y;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "id=" + id +
                ", enemyHealth=" + enemyHealth +
                ", active=" + active +
                ", coord_X=" + coord_X +
                ", coord_Y=" + coord_Y +
                ", bullets=" + bullets +
                '}';
    }
}

