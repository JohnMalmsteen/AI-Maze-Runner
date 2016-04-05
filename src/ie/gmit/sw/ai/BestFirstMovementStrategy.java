package ie.gmit.sw.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import ie.gmit.sw.game.GameRunner;
import ie.gmit.sw.gameassets.Sprite;
import ie.gmit.sw.maze.Cell;
import ie.gmit.sw.maze.Node;

public class BestFirstMovementStrategy implements MovementStrategy{
	private int goalRow;
	private int goalCol;
	private Queue<Node> queue;
	private Sprite rep;
	private Cell holder;
	private List<Cell> path;
	private boolean first = true;
	private int i = 0;
	EnemyHeuristicCellComparator comparator = new EnemyHeuristicCellComparator();
	
	public BestFirstMovementStrategy(Cell initial, Sprite rep){
		holder = initial;
		holder.addSprite(rep);
		this.rep = rep;
		queue = new PriorityQueue<Node>(20, (Node cell1, Node cell2) -> (int)(cell1.getCell().getDistanceToCell(goalRow, goalCol)) - (int)(cell2.getCell().getDistanceToCell(goalRow, goalCol)));
	}
	
	@Override
	public Cell move() {
		//I want to calculate the route every 5 turns, or if its the first turn or if I'm close enough to the target
		if(first || Math.abs(holder.getDistanceToCell(GameRunner.getCurrentRow(), GameRunner.getCurrentCol())) < 5.0 || i++ % 5 == 0)
			path = getPath(holder);
		first = false;
		Cell target = holder;
		if(holder != path.get(0)){
			target = path.remove(0);
		}
		else{
			new RandomMovementStrategy(holder, rep).move();
		}
		holder.removeSprite(rep);
		target.addSprite(rep);
		holder = target;
		return holder;
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
				List<Cell> retList = new ArrayList<>();
				retList.add(current.getCell());
				if(current.getParent() == null){
					return retList;
				}
				while(current.getParent().getParent() != null){
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
		System.out.println("failed");
		return null;
	}
}