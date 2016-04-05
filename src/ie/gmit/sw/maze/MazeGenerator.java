package ie.gmit.sw.maze;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.stream.IntStream;

public class MazeGenerator {
	private Cell[][] maze;
	private Stack<Cell> stack = new Stack<>();
	private Random rand = new Random();
	private int width;
	private int height;

	public MazeGenerator(int width, int height){
		this.width = width;
		this.height = height;
		maze = new Cell[width][height];
		init();
		generateMaze();
	}
	
	public void init(){
		//non synchronized loops allow parallelization 
		for(int row = 0; row < width; row++) {
			for(int col = 0; col < height; col++){
				maze[row][col] = new CellImpl();
				maze[row][col].setCol(col);
				maze[row][col].setRow(row);
			}
		}		
		//connect the model together
		connections();
	}
	
	public void connections(){
		IntStream.range(0, width).forEach(row -> {
			IntStream.range(0, height).forEach(col -> {
			
				maze[row][col].setEastConnection(new EdgeConnector());
				if(col+1 < width){
					maze[row][col+1].setWestConnection(maze[row][col].getEastConnection());
					maze[row][col+1].setWest(maze[row][col]);
				}
			
				maze[row][col].setWestConnection(new EdgeConnector());
				if(col > 0){
					maze[row][col-1].setEastConnection(maze[row][col].getWestConnection());
					maze[row][col-1].setEast(maze[row][col]);
				}
			
				maze[row][col].setNorthConnection(new EdgeConnector());
				if(row > 0){
					maze[row-1][col].setSouthConnection(maze[row][col].getNorthConnection());
					maze[row-1][col].setSouth(maze[row][col]);
				}
			
				maze[row][col].setSouthConnection(new EdgeConnector());
				if(row + 1 < height){
					maze[row+1][col].setNorthConnection(maze[row][col].getSouthConnection());
					maze[row+1][col].setNorth(maze[row][col]);
				}
				
				if(row > 0 && row < height-1 && col > 0 && col < width-1 && rand.nextInt()%4 == 0){
					int test = rand.nextInt(4);
					
					if(test == 0){
						maze[row][col].getEastConnection().setType(ConnectionType.PASSAGE);
						maze[row][col+1].getWestConnection().setType(ConnectionType.PASSAGE);
					}else if(test == 1){
						maze[row][col].getWestConnection().setType(ConnectionType.PASSAGE);
						maze[row][col-1].getEastConnection().setType(ConnectionType.PASSAGE);
					}else if(test == 2){
						maze[row][col].getNorthConnection().setType(ConnectionType.PASSAGE);
						maze[row-1][col].getSouthConnection().setType(ConnectionType.PASSAGE);
					}else{
						maze[row][col].getSouthConnection().setType(ConnectionType.PASSAGE);
						maze[row+1][col].getNorthConnection().setType(ConnectionType.PASSAGE);
					}
				}
			});
		});
	}
	
	public void generateMaze(){
		Cell current = maze[rand.nextInt(width)][rand.nextInt(height)];
		Set<Cell> unvisited = new HashSet<>();
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				unvisited.add(maze[i][j]);
			}
		}
		
		unvisited.remove(current);
		
		while(!unvisited.isEmpty()){
			List<Cell> options = new ArrayList<>();
			int row = current.getRow();
			int col = current.getCol();
			
			if(row > 0 && unvisited.contains(maze[row-1][col])){
				options.add(maze[row-1][col]);
			}
			
			if(row + 1 < height && unvisited.contains(maze[row+1][col])){
				options.add(maze[row+1][col]);
			}
			
			if(col > 0 && unvisited.contains(maze[row][col-1])){
				options.add(maze[row][col-1]);
			}
			
			if(col+1 < width && unvisited.contains(maze[row][col+1])){
				options.add(maze[row][col+1]);
			}
			
			
			if(!options.isEmpty()){
				Cell choice = options.get(rand.nextInt(options.size()));
				stack.push(current);
				if(current.getRow() > choice.getRow()){
					current.getNorthConnection().setType(ConnectionType.PASSAGE);
				}else if(current.getRow() < choice.getRow()){
					current.getSouthConnection().setType(ConnectionType.PASSAGE);
				}
				
				if(choice.getCol() > current.getCol()){
					current.getEastConnection().setType(ConnectionType.PASSAGE);
				}else if(choice.getCol() < current.getCol()){
					current.getWestConnection().setType(ConnectionType.PASSAGE);
				}
				
				current = choice;
				unvisited.remove(current);
			}else{
				current = stack.pop();
			}
		}
	}
	
	
	public Cell[][] getMaze(){
		return maze;
	}

}
