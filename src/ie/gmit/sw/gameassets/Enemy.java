package ie.gmit.sw.gameassets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Enemy implements Sprite {
	private BufferedImage image;
	
	public Enemy(){
		try {
			image = ImageIO.read(new File("resources/spider_up.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void move() {

		
	}

	public BufferedImage getImage() {
		return this.image;
	}
	
	public void setImage(BufferedImage image){
		this.image = image;
	}

}