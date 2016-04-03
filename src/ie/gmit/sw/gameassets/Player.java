package ie.gmit.sw.gameassets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Player implements Sprite {
	
	private BufferedImage image;
	private List<Item> Items = new ArrayList<Item>();
	
	public Player(){
		try {
			image = ImageIO.read(new java.io.File("resources/harry.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	public BufferedImage getImage() {
		return this.image;
	}
	@Override
	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
