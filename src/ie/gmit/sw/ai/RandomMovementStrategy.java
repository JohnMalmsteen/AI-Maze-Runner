package ie.gmit.sw.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ie.gmit.sw.gameassets.Sprite;
import ie.gmit.sw.maze.Cell;
import ie.gmit.sw.maze.ConnectionType;

public class RandomMovementStrategy implements MovementStrategy {
	private Cell current;
	private Sprite rep;
	private Random rand = new Random();
	public RandomMovementStrategy(Cell initial, Sprite self) {
		current = initial;
		rep = self;
	}
	
	@Override
	public Cell move() {
		List<Cell> options = new ArrayList<>();
		if(current.getNorthConnection().getType() == ConnectionType.PASSAGE){
			options.add(current.getNorth());
		}
		
		if(current.getSouthConnection().getType() == ConnectionType.PASSAGE){
			options.add(current.getSouth());
		}
		
		if(current.getWestConnection().getType() == ConnectionType.PASSAGE){
			options.add(current.getWest());
		}
		
		if(current.getEastConnection().getType() == ConnectionType.PASSAGE){
			options.add(current.getEast());
		}
		
		current.removeSprite(rep);
		current = options.get(rand.nextInt(options.size()));
		current.addSprite(rep);
		return current;
	}

}
