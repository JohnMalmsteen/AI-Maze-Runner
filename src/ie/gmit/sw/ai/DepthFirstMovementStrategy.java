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
	private Cell[][] model;
	private int currentRow;
	private int currentCol;
	private Stack<Cell> stack = new Stack<>();
	private Set<Cell> visited = new HashSet<>();
	private Sprite rep;
	
	public DepthFirstMovementStrategy(Cell[][] model, int row, int col, Sprite rep){
		this.model = model;
		this.currentCol = col;
		this.currentRow = row;
		stack.push(model[row][col]);
		visited.add(stack.peek());
		this.rep = rep;
	}
	
	public void move(){
		if(!stack.isEmpty()){
			Cell current = stack.peek();
			
			int row = current.getRow();
			int col = current.getCol();
			List<Cell> options = new ArrayList<>();
			if(row > 0 && model[row][col].getNorth().getType() == ConnectionType.PASSAGE && !visited.contains(model[row-1][col])){
				options.add(model[row-1][col]);
			}
			
			if(row + 1 < model.length && model[row][col].getSouth().getType() == ConnectionType.PASSAGE && !visited.contains(model[row+1][col])){
				options.add(model[row+1][col]);
			}
			
			if(col > 0 && model[row][col].getWest().getType() == ConnectionType.PASSAGE && !visited.contains(model[row][col-1])){
				options.add(model[row][col-1]);
			}
			
			if(col+1 < model[0].length && model[row][col].getEast().getType() == ConnectionType.PASSAGE && !visited.contains(model[row][col+1])){
				options.add(model[row][col+1]);
			}
			
			if(!options.isEmpty()){
				stack.push(options.remove(0));
				model[currentRow][currentCol].setSprite(null);
				currentCol = stack.peek().getCol();
				currentRow = stack.peek().getRow();
				model[currentRow][currentCol].setSprite(rep);
				visited.add(stack.peek());
			}else{
				model[currentRow][currentCol].setSprite(null);
				currentCol = stack.peek().getCol();
				currentRow = stack.peek().getRow();
				model[currentRow][currentCol].setSprite(rep);
				stack.pop();
			}
			
		}else{
			System.out.println("stackempty");
			visited.clear();
			stack.push(model[currentRow][currentCol]);
			move();
		}
	}
	
}
