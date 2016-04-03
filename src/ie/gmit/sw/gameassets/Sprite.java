package ie.gmit.sw.gameassets;

import java.awt.image.BufferedImage;

public interface Sprite {
	public void move();
	public BufferedImage getImage();
	public void setImage(BufferedImage image);
}
