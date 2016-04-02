package ie.gmit.sw.maze;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import ie.gmit.sw.gameassets.Item;

public class CellImpl extends AbstractCell {
	private Item powerup;
	private BufferedImage image;
	public CellImpl() {
		try {
			image = ImageIO.read(new java.io.File("resources/hedge.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Item getPowerup() {
		return powerup;
	}

	public void setPowerup(Item powerup) {
		this.powerup = powerup;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}


	@Override
	public List<EdgeConnector> getNeighbours() {
		List<EdgeConnector> neighbours = new ArrayList<EdgeConnector>();
		neighbours.add(east);
		neighbours.add(west);
		neighbours.add(north);
		neighbours.add(south);
		return neighbours;
	}


}
