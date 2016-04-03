package ie.gmit.sw.gameassets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ie.gmit.sw.ai.DepthFirstMovementStrategy;
import ie.gmit.sw.maze.Cell;

public class BlastEndedSkrewt extends Enemy {
	private DepthFirstMovementStrategy strategy;
	
	public BlastEndedSkrewt(Cell initial) {
		strategy = new DepthFirstMovementStrategy(initial, this);
		try {
			BufferedImage image = ImageIO.read(new File("resources/skrewt.png"));
			setImage(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void move() {
		this.strategy.move();
	}

	@Override
	public BufferedImage getImage() {
		return super.getImage();
	}
	
	public void setImage(BufferedImage image){
		super.setImage(image);
	}
}