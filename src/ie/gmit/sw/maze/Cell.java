package ie.gmit.sw.maze;

import java.util.List;

public interface Cell {
	public List<EdgeConnector> getNeighbours();
	public EdgeConnector getEast();
	public EdgeConnector getWest();
	public EdgeConnector getNorth();
	public EdgeConnector getSouth();
	public void setEast(EdgeConnector east);
	public void setWest(EdgeConnector west);
	public void setNorth(EdgeConnector north);
	public void setSouth(EdgeConnector south);
	public void setRow(int x);
	public void setCol(int y);
	public int getRow();
	public int getCol();
}
