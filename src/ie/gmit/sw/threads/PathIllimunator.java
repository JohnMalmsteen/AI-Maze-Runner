package ie.gmit.sw.threads;

import java.util.List;

import ie.gmit.sw.maze.Cell;

public class PathIllimunator implements Runnable {
	private List<Cell> path = null;
	
	public PathIllimunator(List<Cell> path) {
		this.path = path;
	}
	@Override
	public void run() {
		System.out.println("running");
		for(Cell kid : path){
			kid.setPathIndicator(true);
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Cell kid : path){
			kid.setPathIndicator(false);
		}
	}

}
