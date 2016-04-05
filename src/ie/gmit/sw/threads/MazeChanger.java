package ie.gmit.sw.threads;

import ie.gmit.sw.maze.MazeGenerator;

public class MazeChanger implements Runnable {
	 MazeGenerator mazeGen;
	public MazeChanger(MazeGenerator maze) {
		mazeGen = maze;
	}
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mazeGen.connections();
			mazeGen.generateMaze();
		}
	}

}
