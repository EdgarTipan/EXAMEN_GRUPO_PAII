package com.example.galaga.galaga.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long lastShootTime;
    private Integer score;
    private Integer health;
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

    public Long getLastShootTime() {
        return lastShootTime;
    }

    public void setLastShootTime(Long lastShootTime) {
        this.lastShootTime = lastShootTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
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
        return "Hero{" +
                "id=" + id +
                ", lastShootTime=" + lastShootTime +
                ", score=" + score +
                ", health=" + health +
                ", coord_X=" + coord_X +
                ", coord_Y=" + coord_Y +
                ", bullets=" + bullets +
                '}';
    }
}
