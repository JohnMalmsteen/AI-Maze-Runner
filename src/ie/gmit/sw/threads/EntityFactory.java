package ie.gmit.sw.threads;

import ie.gmit.sw.ai.StrategyType;
import ie.gmit.sw.gameassets.BlastEndedSkrewt;
import ie.gmit.sw.gameassets.DeathEater;
import ie.gmit.sw.gameassets.Enemy;
import ie.gmit.sw.gameassets.EnemyType;
import ie.gmit.sw.gameassets.Voldemort;
import ie.gmit.sw.maze.Cell;

public class EntityFactory {
	private static EntityFactory instance = new EntityFactory();
	
	public EntityFactory() {}
	
	public static  EntityFactory getInstance(){
		return instance;
	}
	
	public Runnable getEntity(Cell initial, EnemyType enemyType, StrategyType strat){
		Enemy ret = null;
		if(enemyType == EnemyType.SKREWT){
			ret = new BlastEndedSkrewt(initial, strat);
		}else if(enemyType == EnemyType.DEATHEATER){
			ret = new DeathEater(initial, strat);
		}else if(enemyType == EnemyType.VOLDEMORT){
			ret = new Voldemort(initial, strat);
		}
		return new IndependentEntity(ret);
	}
}
