package ie.gmit.sw.maze;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import ie.gmit.sw.gameassets.Item;
import ie.gmit.sw.gameassets.Sprite;

public class CellImpl extends AbstractCell {
	private Item powerup;
	private BufferedImage image;
	private List<Sprite> sprites = new ArrayList<>();


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
		neighbours.add(eastConnection);
		neighbours.add(westConnection);
		neighbours.add(northConnection);
		neighbours.add(southConnection);
		return neighbours;
	}

	public Sprite getSprite() {
		if(!sprites.isEmpty())
			return sprites.get(sprites.size()-1);
		
		return null;
	}

	@Override
	public double getDistanceToCell(int row, int col) {
		double ans = Math.sqrt((Math.pow(((double)row-(double)this.row), 2) + Math.pow(((double)col-(double)this.col), 2)));
		return ans;
	}

	@Override
	public void addSprite(Sprite sprite) {
		sprites.add(sprite);
	}

	@Override
	public void removeSprite(Sprite sprite) {
		sprites.remove(sprite);
	}
	
	

}
