package ie.gmit.sw.ai;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import ie.gmit.sw.gameassets.Sprite;
import ie.gmit.sw.maze.Cell;
import ie.gmit.sw.maze.ConnectionType;

public class DepthFirstMovementStrategy {
	private int currentRow;
	private int currentCol;
	private Stack<Cell> stack = new Stack<>();
	private Set<Cell> visited = new HashSet<>();
	private Sprite rep;
	private Cell holder;
	
	public DepthFirstMovementStrategy(Cell initial, Sprite rep){
		this.currentCol = initial.getCol();
		this.currentRow = initial.getRow();
		stack.push(initial);
		visited.add(stack.peek());
		holder = initial;
		this.rep = rep;
	}
	
	public void move(){
		if(!stack.isEmpty()){
			Cell current = stack.peek();
			
			List<Cell> options = new ArrayList<>();
			if(current.getNorthConnection().getType() == ConnectionType.PASSAGE && !visited.contains(current.getNorth())){
				options.add(current.getNorth());
			}
			
			if(current.getSouthConnection().getType() == ConnectionType.PASSAGE && !visited.contains(current.getSouth())){
				options.add(current.getSouth());
			}
			
			if(current.getWestConnection().getType() == ConnectionType.PASSAGE && !visited.contains(current.getWest())){
				options.add(current.getWest());
			}
			
			if(current.getEastConnection().getType() == ConnectionType.PASSAGE && !visited.contains(current.getEast())){
				options.add(current.getEast());
			}
			
			if(!options.isEmpty()){
				current.setSprite(null);
				stack.push(options.remove(0));
				stack.peek().setSprite(rep);
				visited.add(stack.peek());
				holder=stack.peek();
			}else{
				current.setSprite(null);
				holder = stack.pop();
				stack.peek().setSprite(rep);
			}
			
		}else{
			System.out.println("stackempty");
			visited.clear();
			stack.push(holder);
			move();
		}
	}
	
}
