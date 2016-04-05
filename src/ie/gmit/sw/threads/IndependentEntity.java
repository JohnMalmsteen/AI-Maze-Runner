package ie.gmit.sw.threads;

import ie.gmit.sw.gameassets.Enemy;

public class IndependentEntity implements Runnable{
	private Enemy enemy;
	
	public IndependentEntity(Enemy enemy) {
		this.enemy = enemy;
	}
	@Override
	public void run() {
		while(enemy.isAlive()){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
