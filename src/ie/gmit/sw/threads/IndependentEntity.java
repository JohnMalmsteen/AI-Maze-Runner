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
			enemy.move();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
