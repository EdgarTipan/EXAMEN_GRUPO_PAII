package model;

public class User {
	
	private int score;
	private int health;
	
	public User(int score, int health) {
		this.score = score;
		this.health = health;
	}

	public int getScore() {
		return score;
	}

	public int getHealth() {
		return health;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setHealth(int health) {
		this.health = health;
	}

}
