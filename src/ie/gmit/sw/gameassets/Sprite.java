package ie.gmit.sw.gameassets;

import java.awt.image.BufferedImage;

import ie.gmit.sw.maze.Cell;

public interface Sprite {
	public Cell move();
	public BufferedImage getImage();
	public void setImage(BufferedImage image);
}
