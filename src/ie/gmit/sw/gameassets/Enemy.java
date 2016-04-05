package ie.gmit.sw.gameassets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ie.gmit.sw.maze.Cell;

public abstract class Enemy implements Sprite {
	private BufferedImage image;
	private int strength;
	private boolean alive = true;
	public Enemy(){
		try {
			image = ImageIO.read(new File("resources/spider_up.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Cell move() {
		return null;
		
	}

	public BufferedImage getImage() {
		return this.image;
	}
	
	public void setImage(BufferedImage image){
		this.image = image;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}
