package ie.gmit.sw.threads;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import ie.gmit.sw.maze.Cell;
import ie.gmit.sw.maze.Node;

public class KillerSpell implements Runnable {
	private Set<Node> visited = new HashSet<>();
	private int maxDepth = 6;
	private Cell start;
	private List<Cell> on = new ArrayList<Cell>();
	
	public KillerSpell(Cell start) {
		this.start = start;
	}
	@Override
	public void run() {
		System.out.println("running");
		kill(new Node(start), 0);
		cleanup();
	}

	private void kill(Node current, int depth){
		visited.add(current);
		current.getCell().setKilling(true);
		on.add(current.getCell());
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(depth++ < maxDepth){
			for(Node kid : current.getChildren()){
				kid.getCell().killSprites();
				if(!visited.contains(kid)){
					kill(kid, depth);
				}
			}
		}
	}
	private void cleanup(){
		for(Cell cell : on){
			cell.setKilling(false);
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
