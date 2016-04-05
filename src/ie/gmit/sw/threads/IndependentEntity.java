package ie.gmit.sw.threads;

import java.util.Random;

import ie.gmit.sw.ai.FightResolver;
import ie.gmit.sw.game.GameView;
import ie.gmit.sw.gameassets.Enemy;
import ie.gmit.sw.gameassets.Player;
import ie.gmit.sw.maze.Cell;

public class IndependentEntity implements Runnable{
	private Enemy enemy;
	private Random rand =  new Random();
	private long offset = 0;
	private FightResolver resolver = FightResolver.getInstance();
	
	public IndependentEntity(Enemy enemy) {
		this.enemy = enemy;
		offset = rand.nextInt(300);
	}
	@Override
	public void run() {
		while(enemy.isAlive()){
			Cell result =enemy.move();
			if(result.getPlayer() != null){
				if(!Player.getWeapon()){
					int winchance = (int)resolver.resolveFight(enemy.getStrength(), Player.getMana());
					if(rand.nextInt(100) < winchance){
						enemy.setAlive(false);
		        		result.removeSprite(enemy);
		        		Player.decrementMana();
					}else{
						GameView.setGameOver(true);
					}
				}else{
					enemy.setAlive(false);
	        		result.removeSprite(enemy);
	        		Player.setWeapon(false);
				}
			}
			try {
				Thread.sleep(400 + offset);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
