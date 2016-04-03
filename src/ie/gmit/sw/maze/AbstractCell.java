package ie.gmit.sw.maze;

public abstract class AbstractCell implements Cell{
	public EdgeConnector eastConnection;
	public EdgeConnector westConnection;
	public EdgeConnector northConnection;
	public EdgeConnector southConnection;

	public Cell east;
	public Cell west;
	public Cell south;
	public Cell north;
	
	public int row;
	public int col;
	
	public EdgeConnector getEastConnection() {
		return eastConnection;
	}
	public void setEastConnection(EdgeConnector east) {
		this.eastConnection = east;
	}
	public EdgeConnector getWestConnection() {
		return westConnection;
	}
	public void setWestConnection(EdgeConnector west) {
		this.westConnection = west;
	}
	public EdgeConnector getNorthConnection() {
		return northConnection;
	}
	public void setNorthConnection(EdgeConnector north) {
		this.northConnection = north;
	}
	public EdgeConnector getSouthConnection() {
		return southConnection;
	}
	public void setSouthConnection(EdgeConnector south) {
		this.southConnection = south;
	}
	
	public Cell getEast() {
		return east;
	}
	public void setEast(Cell east) {
		this.east = east;
	}
	public Cell getWest() {
		return west;
	}
	public void setWest(Cell west) {
		this.west = west;
	}
	public Cell getSouth() {
		return south;
	}
	public void setSouth(Cell south) {
		this.south = south;
	}
	public Cell getNorth() {
		return north;
	}
	public void setNorth(Cell north) {
		this.north = north;
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
