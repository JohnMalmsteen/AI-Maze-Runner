package ie.gmit.sw.gameassets;

import java.util.*;
import java.awt.image.BufferedImage;

import ie.gmit.sw.ai.NavigatorHeuristicComparator;
import ie.gmit.sw.game.GameRunner;
import ie.gmit.sw.maze.Cell;
import ie.gmit.sw.maze.Direction;

public class Navigator implements Item {
	private BufferedImage image;
	private NavigatorHeuristicComparator sorter = new NavigatorHeuristicComparator();
	private PriorityQueue<Cell> open = new PriorityQueue<Cell>(20, sorter);
	private List<Cell> closed = new ArrayList<Cell>();
	private Map<Cell, Cell> cameFrom = new HashMap<>();
	private Map<Cell, Double> gscores = new HashMap<>();
	private Map<Cell, Double> fscores =  new HashMap<>();
	
	private Cell initial;
	
	public Navigator(Cell initial) {
		this.initial = initial;
	}
	
	@Override
	public BufferedImage getImage() {
		return image;
	}

	@Override
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public List<Cell> findPath(Cell start){
		//start cell has 0 abs cost
		gscores.put(start, 0.0);
		
		//fscore is entirely heuristic
		fscores.put(start, getHeuristicCost(start));
		
		while(!open.isEmpty()){
			Cell current = open.poll();
			if(current == GameRunner.getTriwizardCup()){
				return reconstructPath();
			}
			
			
			
			List<Cell> neighbours = new ArrayList<>();
			List<Direction> available = current.getNeighbours();
			if(available.contains(Direction.EAST)) neighbours.add(current.getEast());
			if(available.contains(Direction.WEST)) neighbours.add(current.getWest());
			if(available.contains(Direction.NORTH)) neighbours.add(current.getNorth());
			if(available.contains(Direction.SOUTH)) neighbours.add(current.getSouth());
			
			open.remove(current);
			closed.add(current);
			
			for(Cell neighbour : neighbours){
				double score = gscores.get(current) + 1.0;
				
				if(!open.contains(neighbour)){
					open.add(neighbour));
				}
				
				
				if(gscores.containsKey(neighbour) && score >= gscores.get(neighbour)){
					continue;
				}
				else{
					cameFrom.put(neighbour, current);
					gscores.put(neighbour, score);
					fscores.put(neighbour, score + getHeuristicCost(neighbour));
				}
			}
			
		}
		
		return null;
	}
	
	public List<Cell> reconstructPath(){
		return null;
	}
	
	public double getHeuristicCost(Cell from){
		return from.getDistanceToCell(GameRunner.getTriwizardCup().getRow(), GameRunner.getTriwizardCup().getCol());
	}

}
