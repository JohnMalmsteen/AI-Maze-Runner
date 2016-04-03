package ie.gmit.sw.game;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

import ie.gmit.sw.ai.EnemyHeuristicCellComparator;
import ie.gmit.sw.gameassets.BlastEndedSkrewt;
import ie.gmit.sw.gameassets.Player;
import ie.gmit.sw.gameassets.Sprite;
import ie.gmit.sw.maze.Cell;
import ie.gmit.sw.maze.ConnectionType;
import ie.gmit.sw.maze.MazeGenerator;
public class GameRunner implements KeyListener{
	private static final int MAZE_DIMENSION = 60;
	private Cell[][] model;
	private GameView view;
	private int currentRow;
	private int currentCol;
	private Random rand = new Random();
	private BlastEndedSkrewt skrewt;
	private static Cell triwizardCup;
	
	public GameRunner() throws Exception{

		MazeGenerator maze = new MazeGenerator(MAZE_DIMENSION, MAZE_DIMENSION);
		model = maze.getMaze();
    	view = new GameView(model);
    	
    	placePlayer();
    	
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
    	if(model[currentRow][currentCol].getSprite() == null){
    		model[currentRow][currentCol].addSprite(new Player());
    	}
    	
    	int skrewtRow = rand.nextInt(MAZE_DIMENSION);
    	int skrewtCol = rand.nextInt(MAZE_DIMENSION);
    	
    	skrewt = new BlastEndedSkrewt(model[skrewtRow][skrewtCol]);

		model[skrewtRow][skrewtCol].addSprite(skrewt);
    	
    	
    	updateView(); 		
	}
	
	private void updateView(){
		view.setCurrentRow(currentRow);
		view.setCurrentCol(currentCol);
	}

    public void keyPressed(KeyEvent e) {
    	Sprite player = model[currentRow][currentCol].getSprite();
    	skrewt.move();
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && currentCol < MAZE_DIMENSION - 1) {
        	if (model[currentRow][currentCol].getEastConnection().getType() == ConnectionType.PASSAGE) {
        		model[currentRow][currentCol].removeSprite(player);
        		currentCol++;   	
        		model[currentRow][currentCol].addSprite(player);
        	}
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT && currentCol > 0) {
        	if (model[currentRow][currentCol].getWestConnection().getType() == ConnectionType.PASSAGE){
        		model[currentRow][currentCol].removeSprite(player);
        		currentCol--;
        		model[currentRow][currentCol].addSprite(player);
        	}
        }else if (e.getKeyCode() == KeyEvent.VK_UP && currentRow > 0) {
        	if (model[currentRow][currentCol].getNorthConnection().getType() == ConnectionType.PASSAGE){
        		model[currentRow][currentCol].removeSprite(player);
        		currentRow--;
        		model[currentRow][currentCol].addSprite(player);
        	}
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN && currentRow < MAZE_DIMENSION - 1) {
        	if (model[currentRow][currentCol].getSouthConnection().getType() == ConnectionType.PASSAGE){
        		model[currentRow][currentCol].removeSprite(player);
        		currentRow++;
        		model[currentRow][currentCol].addSprite(player);
        	}
        }else if (e.getKeyCode() == KeyEvent.VK_Z){
        	view.toggleZoom();
        }else{
        	return;
        }
        EnemyHeuristicCellComparator.setTargetColumn(currentCol);
        EnemyHeuristicCellComparator.setTargetRow(currentRow);
        updateView();       
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