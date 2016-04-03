package ie.gmit.sw.ai;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
    	if(model[currentRow][currentCol].getSprite() == null){
    		model[currentRow][currentCol].setSprite(new Player());
    	}
    	
    	updateView(); 		
	}
	
	private void updateView(){
		view.setCurrentRow(currentRow);
		view.setCurrentCol(currentCol);
	}

    public void keyPressed(KeyEvent e) {
    	Sprite player = model[currentRow][currentCol].getSprite();
    	
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && currentCol < MAZE_DIMENSION - 1) {
        	if (model[currentRow][currentCol].getEast().getType() == ConnectionType.PASSAGE) {
        		model[currentRow][currentCol].setSprite(null);
        		currentCol++;   	
        		model[currentRow][currentCol].setSprite(player);
        	}
        }else if (e.getKeyCode() == KeyEvent.VK_LEFT && currentCol > 0) {
        	if (model[currentRow][currentCol].getWest().getType() == ConnectionType.PASSAGE){
        		model[currentRow][currentCol].setSprite(null);
        		currentCol--;
        		model[currentRow][currentCol].setSprite(player);
        	}
        }else if (e.getKeyCode() == KeyEvent.VK_UP && currentRow > 0) {
        	if (model[currentRow][currentCol].getNorth().getType() == ConnectionType.PASSAGE){
        		model[currentRow][currentCol].setSprite(null);
        		currentRow--;
        		model[currentRow][currentCol].setSprite(player);
        	}
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN && currentRow < MAZE_DIMENSION - 1) {
        	if (model[currentRow][currentCol].getSouth().getType() == ConnectionType.PASSAGE){
        		model[currentRow][currentCol].setSprite(null);
        		currentRow++;
        		model[currentRow][currentCol].setSprite(player);
        	}
        }else if (e.getKeyCode() == KeyEvent.VK_Z){
        	view.toggleZoom();
        }else{
        	return;
        }
      
        updateView();       
    }
    public void keyReleased(KeyEvent e) {} //Ignore
	public void keyTyped(KeyEvent e) {} //Ignore
	
	public static void main(String[] args) throws Exception{
		new GameRunner();
	}
}