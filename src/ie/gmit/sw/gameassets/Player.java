package ie.gmit.sw.gameassets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ie.gmit.sw.maze.Cell;

public class Player implements Sprite {
	
	private BufferedImage image;
	private BufferedImage altImage;
	private static int mana = 0;
	private int health = 3;
	private static boolean weapon = false;
	private static Spell spell = null;
	
	public Player(){
		try {
			image = ImageIO.read(new java.io.File("resources/harry.png"));
			altImage = ImageIO.read(new File("resources/altharry.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Cell move() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BufferedImage getImage() {
		if(weapon)
			return this.altImage;
		else
			return this.image;
	}
	@Override
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public static void incrementMana(){
		mana += 20;
	}
	
	public static void decrementMana(){
		mana = Math.max(mana - 20, 0);
	}
	public static int getMana() {
		return mana;
	}
	
	public static void setMana(int man) {
		mana = man;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}

	public static void setWeapon(boolean hasweapon){
		weapon = hasweapon;
	}
	
	public static boolean getWeapon(){
		return weapon;
	}
	public static Spell getSpell() {
		return spell;
	}
	public static void setSpell(Spell spel) {
		spell = spel;
	}

}
