package ie.gmit.sw.gameassets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Player implements Sprite {
	
	private BufferedImage image;
	private int mana = 0;
	private int health = 3;
	private Weapon weapon = null;
	
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
	

	public int getMana() {
		return mana;
	}
	public void setMana(int mana) {
		this.mana = mana;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public Weapon getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

}
