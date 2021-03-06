package ie.gmit.sw.game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;

import javax.imageio.*;
import javax.swing.*;

import ie.gmit.sw.gameassets.Player;
import ie.gmit.sw.maze.Cell;
import ie.gmit.sw.maze.ConnectionType;
public class GameView extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_VIEW_SIZE = 800;	
	private int cellspan = 5;	
	private int cellpadding = 2;
	private Cell[][] maze;
	private int enemy_state = 5;
	private Timer timer;
	private int currentRow;
	private int currentCol;
	private boolean zoomOut = false;
	private BufferedImage eastWall;
	private BufferedImage westWall;
	private BufferedImage southWall;
	private BufferedImage northWall;
	private BufferedImage trophy;
	private BufferedImage mana;
	private BufferedImage gameoverimage;
	private BufferedImage congrats;
	private BufferedImage smallmana;
	private BufferedImage sword;
	private BufferedImage spell;
	private static boolean gameOver = false;
	private boolean playerWins = false;
	
	public GameView(Cell[][] maze) throws Exception{
		init();
		this.maze = maze;
		setBackground(Color.LIGHT_GRAY);
		setDoubleBuffered(true);
		timer = new Timer(200, this);
		timer.start();
	}
	
	public void setCurrentRow(int row) {
		if (row < cellpadding){
			currentRow = cellpadding;
		}else if (row > (maze.length - 1) - cellpadding){
			currentRow = (maze.length - 1) - cellpadding;
		}else{
			currentRow = row;
		}
	}

	public void setCurrentCol(int col) {
		if (col < cellpadding){
			currentCol = cellpadding;
		}else if (col > (maze[currentRow].length - 1) - cellpadding){
			currentCol = (maze[currentRow].length - 1) - cellpadding;
		}else{
			currentCol = col;
		}
	}

	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        if(!gameOver && !playerWins){  
	        cellspan = zoomOut ? maze.length : 5;       
	        int size = zoomOut ? (DEFAULT_VIEW_SIZE/cellspan)+1 : (DEFAULT_VIEW_SIZE/cellspan);
	        g2.drawRect(0, 0, GameView.DEFAULT_VIEW_SIZE, GameView.DEFAULT_VIEW_SIZE);
	        
	        for(int row = 0; row < cellspan; row++) {
	        	for (int col = 0; col < cellspan; col++){  
	        		int x1 = col * size;
	        		int y1 = row * size;
	        		
	        		Cell ch = null;
	       		
	        		if (zoomOut){
	        			ch = maze[row][col];
	        			if (row == currentRow && col == currentCol){
	        				g2.setColor(Color.YELLOW);
	        				g2.fillRect(x1, y1, size, size);
	        				continue;
	        			}
	        			if(ch.getSprite() != null){
	        				g2.setColor(Color.RED);
	        				g2.fillRect(x1, y1, size, size);
	        				continue;
	        			}
	        		}else{
	
	        			ch = maze[currentRow - cellpadding + row][currentCol - cellpadding + col];
	        		}
	        		if(ch.getPathIndicator()){
	        			g2.setColor(new Color(30, 30, 130));
	        			g2.fillRect(x1, y1, size, size);
	        		}else if(ch.isKiling()){
	        			g2.setColor(new Color(130, 30, 30));
	        			g2.fillRect(x1, y1, size, size);
	        		}else{
	        			g2.setColor(new Color(30, 130, 30));
	        			g2.fillRect(x1, y1, size, size);
	        		}
	
	        		if(ch.getEastConnection().getType()==ConnectionType.WALL){
	        			g2.drawImage(eastWall, x1, y1, size, size, null);
	        		}
	        		
	        		if(ch.getWestConnection().getType()==ConnectionType.WALL){
	        			g2.drawImage(westWall, x1, y1, size, size, null);
	        		}
	        		
	        		if(ch.getNorthConnection().getType()==ConnectionType.WALL){
	        			g2.drawImage(northWall, x1, y1, size, size, null);
	        		}
	        		
	        		if(ch.getSouthConnection().getType()==ConnectionType.WALL){
	        			g2.drawImage(southWall, x1, y1, size, size, null);
	        		}
	        		if(ch.getItem()!=null){
	        			g2.drawImage(ch.getItem().getImage(), x1, y1, null);
	        		}
	        		if(ch.getSpell() != null){
	        			g2.drawImage(spell, x1, y1, null);
	        		}
	        		
	        		if(ch == GameRunner.getTriwizardCup()){
						g2.drawImage(trophy, x1, y1,size, size, null);
	        		}
	        		
	        		if(ch.hasManaBottle()){
	        			g2.drawImage(mana, x1, y1, size, size, null);
	        		}
	        		
	        		if(ch.hasWeapon()){
	        			g2.drawImage(sword, x1, y1, size, size, null);
	        		}
	        		
	        		if(ch.getSprite() != null){
	        			g2.drawImage(ch.getSprite().getImage(), x1, y1, size, size, null);
	        		}
	        		
	        		if(ch.getPlayer() != null){
	        			g2.drawImage(ch.getPlayer().getImage(), x1, y1, size, size, null);
	        		}
	        		   		
	        	}
	        }
	        for(int i = 0; i < (Player.getMana()/20); i++){
	        	g2.drawImage(smallmana, i*20, 0, null);
	        }
        }else{
        	if(gameOver){
	        	g2.setColor(Color.WHITE);
	        	g2.drawRect(0, 0, GameView.DEFAULT_VIEW_SIZE, GameView.DEFAULT_VIEW_SIZE);
	        	g2.drawImage(gameoverimage, 0, 0, DEFAULT_VIEW_SIZE, DEFAULT_VIEW_SIZE, null);	
        	}else{
        		g2.drawImage(congrats, 0, 0, DEFAULT_VIEW_SIZE, DEFAULT_VIEW_SIZE, null);
        	}
        }
	}
	
	public void toggleZoom(){
		zoomOut = !zoomOut;		
	}

	public void actionPerformed(ActionEvent e) {	
		if (enemy_state < 0 || enemy_state == 5){
			enemy_state = 6;
		}else{
			enemy_state = 5;
		}
		this.repaint();
	}
	
	private void init() throws Exception{
		westWall = ImageIO.read(new File("resources/westwall.png"));
		eastWall = ImageIO.read(new File("resources/eastwall.png"));
		northWall = ImageIO.read(new File("resources/northwall.png"));
		southWall = ImageIO.read(new File("resources/southwall.png"));
		trophy = ImageIO.read(new File("resources/trophy.png"));
		mana = ImageIO.read(new File("resources/mana.png"));
		gameoverimage = ImageIO.read(new File("resources/gameover.png"));
		congrats = ImageIO.read(new File("resources/congrats.png"));
		smallmana = ImageIO.read(new File("resources/smallmana.png"));
		sword = ImageIO.read(new File("resources/sword.png"));
		spell = ImageIO.read(new File("resources/spell.png"));
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public static void setGameOver(boolean over) {
		gameOver = over;
	}

	public boolean isPlayerWins() {
		return playerWins;
	}

	public void setPlayerWins(boolean playerWins) {
		this.playerWins = playerWins;
	}
}