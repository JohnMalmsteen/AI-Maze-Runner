package ie.gmit.sw.gameassets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ie.gmit.sw.ai.BestFirstMovementStrategy;
import ie.gmit.sw.ai.DepthFirstMovementStrategy;
import ie.gmit.sw.ai.MovementStrategy;
import ie.gmit.sw.ai.RandomMovementStrategy;
import ie.gmit.sw.ai.StrategyType;
import ie.gmit.sw.maze.Cell;

public class Voldemort extends Enemy {
	private MovementStrategy strategy;
	
	public Voldemort(Cell initial, StrategyType strategytype) {
		if(strategytype == StrategyType.DEPTH_FIRST){
			strategy = new DepthFirstMovementStrategy(initial, this);
		}
		else if(strategytype == StrategyType.BEST_FIRST){
			strategy = new BestFirstMovementStrategy(initial, this);
		}else if(strategytype == StrategyType.RANDOM){
			strategy = new RandomMovementStrategy(initial, this);
		}
		
		this.setStrength(95);
		try {
			BufferedImage image = ImageIO.read(new File("resources/voldemort.png"));
			setImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Cell move() {
		return this.strategy.move();
	}

	@Override
	public BufferedImage getImage() {
		return super.getImage();
	}
	
	public void setImage(BufferedImage image){
		super.setImage(image);
	}
}
