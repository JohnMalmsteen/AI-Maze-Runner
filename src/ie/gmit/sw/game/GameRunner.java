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
import ie.gmit.sw.ai.FightResolver;
import ie.gmit.sw.ai.StrategyType;
import ie.gmit.sw.gameassets.AvadaKedavra;
import ie.gmit.sw.gameassets.Enemy;
import ie.gmit.sw.gameassets.EnemyType;
import ie.gmit.sw.gameassets.Item;
import ie.gmit.sw.gameassets.Navigator;
import ie.gmit.sw.gameassets.Player;
import ie.gmit.sw.maze.Cell;
import ie.gmit.sw.maze.ConnectionType;
import ie.gmit.sw.maze.MazeGenerator;
import ie.gmit.sw.maze.Node;
import ie.gmit.sw.threads.EntityFactory;
import ie.gmit.sw.threads.MazeChanger;
import ie.gmit.sw.threads.PathIllimunator;

public class GameRunner implements KeyListener{
	private static final int MAZE_DIMENSION = 100;
	private Cell[][] model;
	private GameView view;
	private static int currentRow;
	private static int currentCol;
	private Random rand = new Random();
	private static Cell triwizardCup;
	private static ExecutorService pool;
	private static EntityFactory factory;
	private static int skrewtCount;
	private static FightResolver fightResolver;
	
	public GameRunner() throws Exception{
		MazeGenerator maze = new MazeGenerator(MAZE_DIMENSION, MAZE_DIMENSION);
		model = maze.getMaze();
		triwizardCup = model[MAZE_DIMENSION/2][MAZE_DIMENSION/2];
    	view = new GameView(model);
    	fightResolver = FightResolver.getInstance();
    	skrewtCount = (MAZE_DIMENSION * MAZE_DIMENSION)/60;
    	pool = Executors.newCachedThreadPool();
    	factory = EntityFactory.getInstance();
   		Runnable mazechanger = new MazeChanger(maze);
   		pool.submit(mazechanger);
    	placePlayer();
    	placeItems();
    	placeMana();
    	placeWeapons();
    	placeSpells();
    	
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
        
        IntStream.range(0, skrewtCount).forEach(enem ->{
    		int skrewtRow = rand.nextInt(MAZE_DIMENSION);
        	int skrewtCol = rand.nextInt(MAZE_DIMENSION);
    		Runnable entity = factory.getEntity(model[skrewtRow][skrewtCol], EnemyType.SKREWT, StrategyType.RANDOM);
    		pool.submit(entity);
    		
    		if(enem%2 == 0){
    			int derow = rand.nextInt(MAZE_DIMENSION);
    			int decol = rand.nextInt(MAZE_DIMENSION);
    			Runnable dentity = factory.getEntity(model[derow][decol], EnemyType.DEATHEATER, StrategyType.DEPTH_FIRST);
    			pool.submit(dentity);
    		}
    		
    		if(enem%20 == 0){
    			int vRow = rand.nextInt(MAZE_DIMENSION);
    	    	int vCol = rand.nextInt(MAZE_DIMENSION);
    			Runnable ventity = factory.getEntity(model[vRow][vCol], EnemyType.VOLDEMORT, StrategyType.BEST_FIRST);
    			pool.submit(ventity);
    		}
    		
    	});
    	
	}
	
	private void placePlayer(){   	
    	currentRow = (int) (MAZE_DIMENSION * Math.random());
    	currentCol = (int) (MAZE_DIMENSION * Math.random());
    	EnemyHeuristicCellComparator.setTargetColumn(currentCol);
    	EnemyHeuristicCellComparator.setTargetRow(currentRow);
		model[currentRow][currentCol].setPlayer(new Player());
    	System.out.println("currentRow = " + currentRow + " currentCol = " + currentCol);
    	updateView(); 		
	}
	
	private void placeMana(){
		int manaCount = (MAZE_DIMENSION*MAZE_DIMENSION)/50;
		IntStream.range(0, manaCount).forEach(i -> {
			int manarow = rand.nextInt(MAZE_DIMENSION);
			int manacol = rand.nextInt(MAZE_DIMENSION);
			while(model[manarow][manacol].getItem() != null || model[manarow][manacol].hasManaBottle()){
				manarow = rand.nextInt(MAZE_DIMENSION);
				manacol = rand.nextInt(MAZE_DIMENSION);
			}

			model[manarow][manacol].setManaBottle(true);
		});
	}
	
	private void placeWeapons(){
		int weaponCount = (MAZE_DIMENSION*MAZE_DIMENSION)/70;
		IntStream.range(0, weaponCount).forEach(i -> {
			int wrow = rand.nextInt(MAZE_DIMENSION);
			int wcol = rand.nextInt(MAZE_DIMENSION);
			while(model[wrow][wcol].getItem() != null || model[wrow][wcol].hasManaBottle() || model[wrow][wcol].hasWeapon()){
				wrow = rand.nextInt(MAZE_DIMENSION);
				wcol = rand.nextInt(MAZE_DIMENSION);
			}

			model[wrow][wcol].setWeapon(true);
		});
	}
	
	private void placeSpells(){
		int spellCount = (MAZE_DIMENSION*MAZE_DIMENSION)/80;
		
		IntStream.range(0, spellCount).forEach(i -> {
			int wrow = rand.nextInt(MAZE_DIMENSION);
			int wcol = rand.nextInt(MAZE_DIMENSION);
			while(model[wrow][wcol].getItem() != null || model[wrow][wcol].hasManaBottle() || model[wrow][wcol].hasWeapon() || model[wrow][wcol].getSpell() != null){
				wrow = rand.nextInt(MAZE_DIMENSION);
				wcol = rand.nextInt(MAZE_DIMENSION);
			}

			model[wrow][wcol].setSpell(new AvadaKedavra());
		});
	}
	
	private void placeItems(){
		int navCount = (MAZE_DIMENSION*MAZE_DIMENSION)/50;
		IntStream.range(0, navCount).forEach(i -> {
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
        }else if(e.getKeyCode() == KeyEvent.VK_A && Player.getSpell() != null){
        	Player.getSpell().use(new Node(model[currentRow][currentCol]));
        	Player.setSpell(null);
        }
        else{
        	return;
        }
        updateView();  
        if(model[currentRow][currentCol] == triwizardCup){
        	view.setPlayerWins(true);
        }else{
	        if(model[currentRow][currentCol].getSprite() != null){
	        	Enemy enemy = (Enemy)model[currentRow][currentCol].getSprite();
	        	if(!Player.getWeapon()){
	        		int winchance = (int)fightResolver.resolveFight(enemy.getStrength(), Player.getMana());
		        	if(rand.nextInt(100) < winchance){
		        		enemy.setAlive(false);
		        		model[currentRow][currentCol].removeSprite(enemy);
		        		Player.decrementMana();
		        	}else{
		        		GameView.setGameOver(true);
		        	}
	        	}
	        	else{
	        		enemy.setAlive(false);
	        		model[currentRow][currentCol].removeSprite(enemy);
	        		Player.setWeapon(false);
	        	}
	        }
	        
	        
	        if(model[currentRow][currentCol].getSpell() != null){
	        	if(Player.getSpell() == null){
		        	Player.setSpell(model[currentRow][currentCol].getSpell());
		        	model[currentRow][currentCol].setSpell(null);
	        	}
	        }
	        
	        if(model[currentRow][currentCol].hasWeapon()){
	        	if(!Player.getWeapon()){
	        		Player.setWeapon(true);
	        		model[currentRow][currentCol].setWeapon(false);
	        	}
	        }
	        if(model[currentRow][currentCol].hasManaBottle()){
	        	Player.incrementMana();
	        	model[currentRow][currentCol].setManaBottle(false);
	        }
	        
	        if(model[currentRow][currentCol].getItem() != null){
	        	Item theItem = model[currentRow][currentCol].getItem();
	        	model[currentRow][currentCol].setItem(null);
	    		List<Cell> path = ((Navigator)theItem).findPath(model[currentRow][currentCol]);
	    		Runnable pathIlluminator = new PathIllimunator(path);
	    		pool.submit(pathIlluminator);
	        }
	        EnemyHeuristicCellComparator.setTargetColumn(currentCol);
	        EnemyHeuristicCellComparator.setTargetRow(currentRow);
        }
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
	
	public static int getCurrentRow(){
		return currentRow;
	}
	
	public static int getCurrentCol(){
		return currentCol;
	}
}