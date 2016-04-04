package ie.gmit.sw.game;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import java.util.*;
import javax.swing.*;

import ie.gmit.sw.ai.EnemyHeuristicCellComparator;
import ie.gmit.sw.ai.StrategyType;
import ie.gmit.sw.gameassets.EnemyType;
import ie.gmit.sw.gameassets.Item;
import ie.gmit.sw.gameassets.Navigator;
import ie.gmit.sw.gameassets.Player;
import ie.gmit.sw.maze.Cell;
import ie.gmit.sw.maze.ConnectionType;
import ie.gmit.sw.maze.MazeGenerator;
import ie.gmit.sw.threads.EntityFactory;

public class GameRunner implements KeyListener{
	private static final int MAZE_DIMENSION = 100;
	private Cell[][] model;
	private GameView view;
	private int currentRow;
	private int currentCol;
	private Random rand = new Random();
	private static Cell triwizardCup;
	private static ExecutorService pool;
	private static EntityFactory factory;
	private static int enemyCount;
	
	public GameRunner() throws Exception{
		MazeGenerator maze = new MazeGenerator(MAZE_DIMENSION, MAZE_DIMENSION);
		model = maze.getMaze();
		triwizardCup = model[MAZE_DIMENSION/2][MAZE_DIMENSION/2];
    	view = new GameView(model);
    	
    	enemyCount = (MAZE_DIMENSION * MAZE_DIMENSION)/40;
    	pool = Executors.newFixedThreadPool(enemyCount);
    	factory = EntityFactory.getInstance();
    	
    	IntStream.range(0, enemyCount).forEach(enem ->{
    		int skrewtRow = rand.nextInt(MAZE_DIMENSION);
        	int skrewtCol = rand.nextInt(MAZE_DIMENSION);
    		Runnable entity = factory.getEntity(model[skrewtRow][skrewtCol], EnemyType.SKREWT, StrategyType.RANDOM);
    		pool.submit(entity);
    	});
    	
    	placePlayer();
    	placeItems();
    	Dimension d = new Dimension(GameView.DEFAULT_VIEW_SIZE, GameView.DEFAULT_VIEW_SIZE);
    	view.setPreferredSize(d);
    	view.setMinimumSize(d);
    	view.setMaximumSize(d);
    	
    	JFrame f = new JFrame("GMIT - B.Sc. in Computing (Software Development)");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addKeyListener(this);
        f.getContentPane().setLayout(new FlowLayout());
        f.add(view);
        f.setSize(1000,1000);
        f.setLocation(100,100);
        f.pack();
        f.setVisible(true);
        
        
	}
	
	private void placePlayer(){   	
    	currentRow = (int) (MAZE_DIMENSION * Math.random());
    	currentCol = (int) (MAZE_DIMENSION * Math.random());
    	EnemyHeuristicCellComparator.setTargetColumn(currentCol);
    	EnemyHeuristicCellComparator.setTargetRow(currentRow);
		model[currentRow][currentCol].setPlayer(new Player());
    	
    	updateView(); 		
	}
	
	private void placeItems(){
		IntStream.range(0, 250).forEach(i -> {
			int navrow = rand.nextInt(MAZE_DIMENSION);
			int navcol = rand.nextInt(MAZE_DIMENSION);
			while(model[navrow][navcol].getItem() != null){
				navrow = rand.nextInt(MAZE_DIMENSION);
				navcol = rand.nextInt(MAZE_DIMENSION);
			}
			Item temp = new Navigator(model[navrow][navcol]);
			model[navrow][navcol].setItem(temp);
		});
	}
	
	private void updateView(){
		view.setCurrentRow(currentRow);
		view.setCurrentCol(currentCol);
	}

    public void keyPressed(KeyEvent e) {
    	Player player = model[currentRow][currentCol].getPlayer();
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && currentCol < MAZE_DIMENSION - 1) {
        	if (model[currentRow][currentCol].getEastConnection().getType() == ConnectionType.PASSAGE) {
        		model[currentRow][currentCol].setPlayer(null);
        		currentCol++;   	
        		model[currentRow][currentCol].setPlayer(player);
        	}
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT && currentCol > 0) {
        	if (model[currentRow][currentCol].getWestConnection().getType() == ConnectionType.PASSAGE){
        		model[currentRow][currentCol].setPlayer(null);
        		currentCol--;
        		model[currentRow][currentCol].setPlayer(player);
        	}
        }else if (e.getKeyCode() == KeyEvent.VK_UP && currentRow > 0) {
        	if (model[currentRow][currentCol].getNorthConnection().getType() == ConnectionType.PASSAGE){
        		model[currentRow][currentCol].setPlayer(null);
        		currentRow--;
        		model[currentRow][currentCol].setPlayer(player);
        	}
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN && currentRow < MAZE_DIMENSION - 1) {
        	if (model[currentRow][currentCol].getSouthConnection().getType() == ConnectionType.PASSAGE){
        		model[currentRow][currentCol].setPlayer(null);
        		currentRow++;
        		model[currentRow][currentCol].setPlayer(player);
        	}
        }else if (e.getKeyCode() == KeyEvent.VK_Z){
        	view.toggleZoom();
        }else{
        	return;
        }
        updateView();    
        
        if(model[currentRow][currentCol].getItem() != null){
        	Item theItem = model[currentRow][currentCol].getItem();
        	model[currentRow][currentCol].setItem(null);
    		List<Cell> path = ((Navigator)theItem).findPath(model[currentRow][currentCol]);
    		for(Cell elem : path){
    			elem.setPathIndicator(true);
    		}
        	
        }
        EnemyHeuristicCellComparator.setTargetColumn(currentCol);
        EnemyHeuristicCellComparator.setTargetRow(currentRow);
           
    }
    public void keyReleased(KeyEvent e) {} //Ignore
	public void keyTyped(KeyEvent e) {} //Ignore
	
	public static Cell getTriwizardCup(){
		return triwizardCup;
	}
	
	public static void setTrizardCup(Cell cup){
		triwizardCup = cup;
	}
	
	public static void main(String[] args) throws Exception{
		new GameRunner();
	}
}