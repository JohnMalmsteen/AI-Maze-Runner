package ie.gmit.sw.threads;

import java.util.Random;

import ie.gmit.sw.gameassets.Enemy;

public class IndependentEntity implements Runnable{
	private Enemy enemy;
	private Random rand =  new Random();
	private long offset = 0;
	public IndependentEntity(Enemy enemy) {
		this.enemy = enemy;
		offset = rand.nextInt(300);
	}
	@Override
	public void run() {
		while(enemy.isAlive()){
			enemy.move();
			try {
				Thread.sleep(400 + offset);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
