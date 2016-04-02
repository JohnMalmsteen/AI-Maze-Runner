package ie.gmit.sw.maze;

public class EdgeConnector {
	private ConnectionType type;
	
	public EdgeConnector(){
		this.type = ConnectionType.WALL;
	}
	
	public ConnectionType getType(){
		return this.type;
	}
	public void setType(ConnectionType type){
		this.type = type;
	}
}
