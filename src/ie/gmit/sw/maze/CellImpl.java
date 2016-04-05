package ie.gmit.sw.maze;

import java.util.ArrayList;
import java.util.List;

import ie.gmit.sw.gameassets.Enemy;
import ie.gmit.sw.gameassets.Item;
import ie.gmit.sw.gameassets.Player;
import ie.gmit.sw.gameassets.Spell;
import ie.gmit.sw.gameassets.Sprite;

public class CellImpl extends AbstractCell {
	private Item item;
	private boolean weapon =  false;
	private Player player;
	private List<Sprite> sprites = new ArrayList<>();
	private boolean pathIndicator = false;;
	private boolean manabottle = false; 
	private Spell spell;
	private boolean killing = false;
	public CellImpl() {
	
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}


	@Override
	public List<Direction> getNeighbours() {
		List<Direction> neighbours = new ArrayList<>();
		if(eastConnection.getType() == ConnectionType.PASSAGE){
			neighbours.add(Direction.EAST);
		}
		
		if(westConnection.getType() == ConnectionType.PASSAGE){
			neighbours.add(Direction.WEST);
		}
		
		if(northConnection.getType() == ConnectionType.PASSAGE){
			neighbours.add(Direction.NORTH);
		}
		
		if(southConnection.getType() == ConnectionType.PASSAGE){
			neighbours.add(Direction.SOUTH);
		}
		
		return neighbours;
	}

	public Sprite getSprite() {
		if(!sprites.isEmpty())
			return sprites.get(sprites.size()-1);
		
		return null;
	}

	@Override
	public double getDistanceToCell(int row, int col) {
		double ans = Math.abs(Math.abs((double)row-(double)this.row) - Math.abs((double)col-(double)this.col));
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

	@Override
	public boolean getPathIndicator() {
		return pathIndicator;
	}

	@Override
	public void setPathIndicator(boolean inpath) {
		this.pathIndicator = inpath;
	}

	@Override
	public Player getPlayer() {
		return this.player;
	}

	@Override
	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public void setManaBottle(boolean hasMana) {
		this.manabottle = hasMana;
	}

	@Override
	public boolean hasManaBottle() {
		return manabottle;
	}
	
	public boolean hasWeapon(){
		return weapon;
	}
	
	public void setWeapon(boolean hasweapon){
		this.weapon = hasweapon;
	}
	
	public void killSprites(){
		for(Sprite sprite : sprites){
			((Enemy)sprite).setAlive(false);
		}
		sprites.clear();
	}

	public Spell getSpell() {
		return spell;
	}

	public void setSpell(Spell spell) {
		this.spell = spell;
	}

	@Override
	public boolean isKiling() {
		return killing;
	}

	@Override
	public void setKilling(boolean kill) {
		killing = kill;
	}
}
