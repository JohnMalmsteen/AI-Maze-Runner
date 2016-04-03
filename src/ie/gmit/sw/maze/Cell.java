package ie.gmit.sw.maze;

import java.util.List;

import ie.gmit.sw.gameassets.Sprite;

public interface Cell {
	public List<EdgeConnector> getNeighbours();
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
	public Sprite getSprite();
	public void setSprite(Sprite sprite);
}
