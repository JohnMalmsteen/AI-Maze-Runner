package ie.gmit.sw.maze;

public abstract class AbstractCell implements Cell{
	public EdgeConnector east;
	public EdgeConnector west;
	public EdgeConnector north;
	public EdgeConnector south;
	public int row;
	public int col;
	
	public EdgeConnector getEast() {
		return east;
	}
	public void setEast(EdgeConnector east) {
		this.east = east;
	}
	public EdgeConnector getWest() {
		return west;
	}
	public void setWest(EdgeConnector west) {
		this.west = west;
	}
	public EdgeConnector getNorth() {
		return north;
	}
	public void setNorth(EdgeConnector north) {
		this.north = north;
	}
	public EdgeConnector getSouth() {
		return south;
	}
	public void setSouth(EdgeConnector south) {
		this.south = south;
	}
	
	public void setRow(int row){
		this.row = row;
	}
	public void setCol(int col){
		this.col = col;
	}
	public int getRow(){
		return this.row;
	}
	public int getCol(){
		return this.col;
	}
	
}
