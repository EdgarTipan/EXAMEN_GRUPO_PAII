package com.example.galaga.galaga.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer currentLevel;
    @OneToOne(cascade = CascadeType.ALL)
    private Hero hero;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Enemy> enemies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", currentLevel=" + currentLevel +
                ", hero=" + hero +
                ", enemies=" + enemies +
                '}';
    }
}
