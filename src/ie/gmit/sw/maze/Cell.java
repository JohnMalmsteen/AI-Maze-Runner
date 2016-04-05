package ie.gmit.sw.maze;

import java.util.List;

import ie.gmit.sw.gameassets.Item;
import ie.gmit.sw.gameassets.Player;
import ie.gmit.sw.gameassets.Spell;
import ie.gmit.sw.gameassets.Sprite;

public interface Cell {
	public List<Direction> getNeighbours();
	public EdgeConnector getEastConnection();
	public EdgeConnector getWestConnection();
	public EdgeConnector getNorthConnection();
	public EdgeConnector getSouthConnection();
	public void setEastConnection(EdgeConnector east);
	public void setWestConnection(EdgeConnector west);
	public void setNorthConnection(EdgeConnector north);
	public void setSouthConnection(EdgeConnector south);
	public Cell getEast();
	public Cell getWest();
	public Cell getSouth();
	public Cell getNorth();
	public void setEast(Cell east);
	public void setWest(Cell west);
	public void setNorth(Cell north);
	public void setSouth(Cell south);
	public void setRow(int x);
	public void setCol(int y);
	public int getRow();
	public int getCol();
	public void addSprite(Sprite sprite);
	public void removeSprite(Sprite sprite);
	public Sprite getSprite();
	public double getDistanceToCell(int row, int col);
	public boolean getPathIndicator();
	public void setPathIndicator(boolean inpath);
	public Item getItem();
	public void setItem(Item item);
	public Player getPlayer();
	public void setPlayer(Player player);
	public void setManaBottle(boolean hasMana);
	public boolean hasManaBottle();
	public void setWeapon(boolean hasweapon);
	public boolean hasWeapon();
	public void killSprites();
	public Spell getSpell();
	public void setSpell(Spell spell);
	public boolean isKiling();
	public void setKilling(boolean kill);
}
