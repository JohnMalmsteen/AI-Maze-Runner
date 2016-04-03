package ie.gmit.sw.gameassets;

import java.awt.image.BufferedImage;

import ie.gmit.sw.ai.DepthFirstMovementStrategy;
import ie.gmit.sw.maze.Cell;

public class BlastEndedSkrewt extends Enemy {
	private DepthFirstMovementStrategy strategy;
	
	public BlastEndedSkrewt(int row, int col, Cell[][] model) {
		strategy = new DepthFirstMovementStrategy(model, row, col, this);
	}
	@Override
	public void move() {
		this.strategy.move();
	}

	@Override
	public BufferedImage getImage() {
		return super.getImage();
	}
}