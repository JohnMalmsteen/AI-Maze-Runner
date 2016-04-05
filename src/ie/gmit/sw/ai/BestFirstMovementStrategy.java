package ie.gmit.sw.ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import ie.gmit.sw.game.GameRunner;
import ie.gmit.sw.gameassets.Sprite;
import ie.gmit.sw.maze.Cell;
import ie.gmit.sw.maze.ConnectionType;
import ie.gmit.sw.maze.Node;

public class BestFirstMovementStrategy implements MovementStrategy{
	private int goalRow;
	private int goalCol;
	private Queue<Node> queue;
	private Set<Cell> visited = new HashSet<>();
	private Sprite rep;
	private Cell holder;
	private List<Cell> path;
	private boolean first = true;
	EnemyHeuristicCellComparator comparator = new EnemyHeuristicCellComparator();
	
	public BestFirstMovementStrategy(Cell initial, Sprite rep){
		holder = initial;
		holder.addSprite(rep);
		this.rep = rep;
		queue = new LinkedList<>();
	}
	
	@Override
	public void move() {
		if(first)
			path = getPath(holder);
		first = false;
		Cell target = path.remove(0);
		holder.removeSprite(rep);
		target.addSprite(rep);
		holder = target;
	}

	private List<Cell> getPath(Cell initial){
		goalRow = GameRunner.getCurrentRow();
		goalCol = GameRunner.getCurrentCol();
		queue.clear();
		Set<Cell> visited = new HashSet<>();
		Node current = new Node(initial);
		current.setParent(null);
		
		visited.add(current.getCell());
		queue.offer(current);
		
		while(!queue.isEmpty()){
			current = queue.poll();
			visited.add(current.getCell());
			if(current.getCell().getRow() == goalRow && current.getCell().getCol() == goalCol){
				System.out.println("found " + current.getCell().getRow() + " " + current.getCell().getCol());
				List<Cell> retList = new ArrayList<>();
				retList.add(current.getCell());
				while(current.getParent() != null){
					current = current.getParent();
					retList.add(current.getCell());
				}
				Collections.reverse(retList);
				return retList;
			}else{
				List<Node> children = current.getChildren();
				for(Node kid : children){
					if(!visited.contains(kid.getCell())){
						queue.add(kid);
					}
				}
				
				
			}
		
		}
		
		return null;
	}
}