package ie.gmit.sw.maze;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private Node parent;
	private Cell cell;
	private List<Node> children;

	public Node(Cell cell) {
		this.cell = cell;

		children = new ArrayList<Node>();
	}

	public List<Node> getChildren(){
		if(cell.getNorthConnection().getType() == ConnectionType.PASSAGE ){
			Node temp = new Node(cell.getNorth());
			temp.setParent(this);
			children.add(temp);
		}
		
		if(cell.getSouthConnection().getType() == ConnectionType.PASSAGE){
			Node temp = new Node(cell.getSouth());
			temp.setParent(this);
			children.add(temp);
		}
		
		if(cell.getWestConnection().getType() == ConnectionType.PASSAGE ){
			Node temp = new Node(cell.getWest());
			temp.setParent(this);
			children.add(temp);
		}
		
		if(cell.getEastConnection().getType() == ConnectionType.PASSAGE ){
			Node temp = new Node(cell.getEast());
			temp.setParent(this);
			children.add(temp);
		}
		return children;
	}
	
	public void addChild(){
		
	}
	
	public Node getParent(){
		return this.parent;
	}
	
	public void setParent(Node parent){
		this.parent = parent;
	}
	
	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}
}
